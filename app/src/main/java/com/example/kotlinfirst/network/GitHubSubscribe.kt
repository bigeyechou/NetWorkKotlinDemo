package com.example.kotlinfirst.network

import com.example.kotlinfirst.databean.BaseRequestBean
import com.example.kotlinfirst.databean.WeatherRequestBean
import com.zhenggzh.dream.retrofitandrxjavademo.netutils.RetrofitFactory
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import java.util.HashMap
import okhttp3.ResponseBody

/**
 * Created by 眼神 on 2018/3/27.
 * 建议：把功能模块来分别存放不同的请求方法，比如登录注册类LoginSubscribe、电影类MovieSubscribe、天气类WeatherSubscribe
 */

object GitHubSubscribe {

  /**
   * 登录接口
   */
  fun login(userName: String, subscriber: DisposableObserver<ResponseBody>) {
    val observable = RetrofitFactory.instance.httpApi!!.getUserInfoByName(userName)
    RetrofitFactory.instance.toSubscribe(observable, subscriber)
  }

}
