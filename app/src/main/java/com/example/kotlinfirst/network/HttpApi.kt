package com.example.kotlinfirst.network

import com.example.kotlinfirst.databean.BaseRequestBean
import com.example.kotlinfirst.databean.WeatherRequestBean
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface HttpApi {

  @GET("api")
  fun getWeatherDataForQuery(@Query("version") version: String, @Query("city") city: String): Observable<ResponseBody>

  @GET("api")
  fun getWeatherDataForMap(@QueryMap map: Map<String, String>): Observable<ResponseBody>

  //天气预报接口测试  @GET 不支持@Body类型
  @POST("api")
  fun getWeatherDataForBody(@Body requestBean: BaseRequestBean<WeatherRequestBean>): Observable<ResponseBody>

}