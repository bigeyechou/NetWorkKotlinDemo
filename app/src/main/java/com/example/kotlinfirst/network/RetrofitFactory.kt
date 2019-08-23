package com.zhenggzh.dream.retrofitandrxjavademo.netutils


import com.example.kotlinfirst.app.App
import com.example.kotlinfirst.app.BaseConstant
import com.example.kotlinfirst.network.HttpApi
import com.example.kotlinfirst.network.NetUtil
import com.example.kotlinfirst.network.URLConstant
import com.orhanobut.logger.Logger

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.util.concurrent.TimeUnit

import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by 眼神 on 2018/3/27.
 * 封装Retrofit配置
 */

class RetrofitFactor constructor() {

  companion object {
    //TODO 填写自己的包名
    val CACHE_NAME = "yourApkName"
    var BASE_URL = URLConstant.BASE_URL
    private val DEFAULT_CONNECT_TIMEOUT = 30
    private val DEFAULT_WRITE_TIMEOUT = 30
    private val DEFAULT_READ_TIMEOUT = 30

    //获取单例
    val instance: RetrofitFactor
      get() = SingletonHolder.INSTANCE
  }

  var TAG = "RetrofitFactory"

  var retrofit: Retrofit? = null
    private set

  var httpApi: HttpApi? = null
  /**
   * 请求失败重连次数
   */
  private val RETRY_COUNT = 0
  private val okHttpBuilder: OkHttpClient.Builder

  init {
    //手动创建一个OkHttpClient并设置超时时间
    okHttpBuilder = OkHttpClient.Builder()
    /**
     * 设置缓存
     */
    val cacheFile = File(App.instance!!.externalCacheDir, CACHE_NAME)
    val cache = Cache(cacheFile, (1024 * 1024 * 50).toLong())
    val cacheInterceptor = Interceptor { chain ->
      var request = chain.request()
      if (!NetUtil.isNetworkConnected) {
        request = request.newBuilder()
          .cacheControl(CacheControl.FORCE_CACHE)
          .build()
      }
      val response = chain.proceed(request)
      if (!NetUtil.isNetworkConnected) {
        val maxAge = 0
        // 有网络时 设置缓存超时时间0个小时
        response.newBuilder()
          .header("Cache-Control", "public, max-age=$maxAge")
          .removeHeader(CACHE_NAME)// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
          .build()
      } else {
        // 无网络时，设置超时为4周
        val maxStale = 60 * 60 * 24 * 28
        response.newBuilder()
          .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
          .removeHeader(CACHE_NAME)
          .build()
      }
      response
    }
    okHttpBuilder.cache(cache).addInterceptor(cacheInterceptor)


    /**
     * 设置头信息
     */
    val headerInterceptor = Interceptor { chain ->
      val originalRequest = chain.request()
      val requestBuilder = originalRequest.newBuilder()
        .addHeader("Accept-Encoding", "gzip")
        .addHeader("Accept", "application/json")
        .addHeader("Content-Type", "application/json; charset=utf-8")
        .method(originalRequest.method(), originalRequest.body())
      requestBuilder.addHeader("Authorization", "Bearer " + BaseConstant.TOKEN)//添加请求头信息，服务器进行token有效性验证
      val request = requestBuilder.build()
      chain.proceed(request)
    }
    okHttpBuilder.addInterceptor(headerInterceptor)


    //        if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Logger.d(message) })
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    //设置 Debug Log 模式
    okHttpBuilder.addInterceptor(loggingInterceptor)
    //        }

    /**
     * 设置超时和重新连接
     */
    okHttpBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
    okHttpBuilder.readTimeout(DEFAULT_WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
    okHttpBuilder.writeTimeout(DEFAULT_READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
    //错误重连
    okHttpBuilder.retryOnConnectionFailure(true)


    retrofit = Retrofit.Builder()
      .client(okHttpBuilder.build())
      .addConverterFactory(GsonConverterFactory.create())//json转换成JavaBean
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .baseUrl(BASE_URL)
      .build()
    httpApi = retrofit!!.create<HttpApi>(HttpApi::class.java!!)
  }

  //在访问HttpMethods时创建单例
  private object SingletonHolder {
    val INSTANCE = RetrofitFactor()

  }

  fun changeBaseUrl(baseUrl: String) {
    retrofit = Retrofit.Builder()
      .client(okHttpBuilder.build())
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .baseUrl(baseUrl)
      .build()
    httpApi = retrofit!!.create<HttpApi>(HttpApi::class.java!!)
  }

  /**
   * 设置订阅 和 所在的线程环境
   */
  fun <T> toSubscribe(o: Observable<T>, s: DisposableObserver<T>) {

    o.subscribeOn(Schedulers.io())
      .unsubscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .retry(RETRY_COUNT.toLong())//请求失败重连次数
      .subscribe(s)

  }

}