package com.example.kotlinfirst.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.kotlinfirst.app.BaseApplication
/**
 * Created by 眼神 on 2018/3/27.
 * 网络请求工具类
 */
object NetUtil {

  /**
   * 判断是否有网络连接
   *
   * @return
   */
  val isNetworkConnected: Boolean
    get() {

      val mConnectivityManager = BaseApplication.app!!
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val mNetworkInfo = mConnectivityManager
        .activeNetworkInfo
      return mNetworkInfo?.isAvailable ?: false

    }


  val isWiFiActive: Boolean
    get() {
      val connectivity = BaseApplication.app!!
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      if (connectivity != null) {
        val info = connectivity.allNetworkInfo
        if (info != null) {
          for (i in info.indices) {
            if (info[i].typeName == "WIFI" && info[i].isConnected) {
              return true
            }
          }
        }
      }
      return false
    }

  /**
   * 获取当前网络连接的类型信息
   *
   * @return
   */
  val connectedType: Int
    get() {
      if (BaseApplication.app!! != null) {
        val mConnectivityManager = BaseApplication.app!!
          .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mNetworkInfo = mConnectivityManager.activeNetworkInfo
        if (mNetworkInfo != null && mNetworkInfo.isAvailable) {
          return mNetworkInfo.type
        }
      }
      return -1
    }

  /**
   * 判断WIFI网络是否可用
   *
   * @param context
   * @return
   */
  fun isWifiConnected(context: Context?): Boolean {
    if (context != null) {
      val mConnectivityManager = BaseApplication.app!!
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val mWiFiNetworkInfo = mConnectivityManager
        .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
      if (mWiFiNetworkInfo != null) {
        return mWiFiNetworkInfo.isAvailable
      }
    }
    return false
  }
}
