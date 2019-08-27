package com.example.kotlinfirst.activity

import android.widget.Toast
import com.example.kotlinfirst.R
import com.example.kotlinfirst.databean.WeatherResponseBean
import com.example.kotlinfirst.network.OnSuccessAndFaultListener
import com.example.kotlinfirst.network.OnSuccessAndFaultSub
import com.example.kotlinfirst.network.WeatherSubscribe
import com.example.kotlinfirst.utils.GsonUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(){

  private var mCityName = "北京"

  override fun initLayout()=R.layout.activity_main

  override fun initWidget() {
    btnGetWeather.setOnClickListener { getWeatherData() }
    mCityName = etCity.getText().toString()
  }

  /**
   * 请求天气预报的数据
   * OnSuccessAndFaultSub 我只是加了错误处理和请求的loading，可以自己根据项目的业务修改
   * new OnSuccessAndFaultSub（第一个参数:成功or失败的回调，第二个参数:上下文，可以不填，控制dialog的）
   */
  private fun getWeatherData() {
    WeatherSubscribe.getWeatherDataForBody(mCityName, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
      override fun onSuccess(result: String) {
        //成功
        Toast.makeText(mContext, "请求成功~", Toast.LENGTH_SHORT).show()
        val weather = GsonUtils.fromJson(
          result,
          WeatherResponseBean::class.java
        )
        tvCityWeather.setText(weather.toString())
      }

      override fun onFault(errorMsg: String) {
        //失败
        Toast.makeText(mContext, "请求失败：$errorMsg", Toast.LENGTH_SHORT).show()
      }

    }, this@MainActivity))
  }
}
