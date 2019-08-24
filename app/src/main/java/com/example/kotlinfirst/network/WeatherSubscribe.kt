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

object WeatherSubscribe {

  /**
   * 获取天气数据@Query
   */
  fun getWeatherDataForQuery(cityName: String, subscriber: DisposableObserver<ResponseBody>) {
    val observable = RetrofitFactory.instance.httpApi!!.getWeatherDataForQuery("v1", cityName)
    RetrofitFactory.instance.toSubscribe(observable, subscriber)
  }

  /**
   * 获取天气数据@QueryMap
   */
  fun getWeatherDataForMap(cityName: String, subscriber: DisposableObserver<ResponseBody>) {
    val map = HashMap<String, String>()
    map["version"] = "v1"
    map["city"] = cityName
    val observable = RetrofitFactory.instance.httpApi!!.getWeatherDataForMap(map)
    RetrofitFactory.instance.toSubscribe(observable, subscriber)
  }

  /**
   * 获取天气数据@Body
   */
  fun getWeatherDataForBody(cityName: String, subscriber: DisposableObserver<ResponseBody>) {
    val bean = WeatherRequestBean()
    bean.setCity(cityName)
    var requestBean : BaseRequestBean<WeatherRequestBean> = BaseRequestBean<WeatherRequestBean>()
    requestBean.setObj(bean)
    val observable = RetrofitFactory.instance.httpApi!!.getWeatherDataForBody(requestBean)
    RetrofitFactory.instance.toSubscribe(observable, subscriber)
  }
}
