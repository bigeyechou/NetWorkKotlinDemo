package com.example.kotlinfirst.app

import android.app.Activity
import android.app.Application

class App : Application() {

  companion object {
    var activityList : ArrayList<Activity>?=null
    lateinit var instance: App
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
    activityList = ArrayList()
  }

  /**
   * 增加Activity
   */
  fun addActivity(act: Activity) {
    if (activityList == null) {
      activityList = ArrayList()
    } else {
      activityList?.add(act)
    }
  }

  /**
   * 移除Activity
   */
  fun removeActivity(act: Activity) {
    activityList?.remove(act)
  }

  /**
   * 退出，杀掉进程
   */
  @Synchronized
  fun exitApp() {
    activityList?.let {
      it.forEach { activity ->
        activity.finish()
      }
    }
    android.os.Process.killProcess(android.os.Process.myPid())
    System.exit(0)
  }

}