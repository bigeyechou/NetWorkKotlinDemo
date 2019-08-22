package com.example.kotlinfirst.app

import android.app.Activity
import android.app.Application
import android.content.Context

class BaseApplication : Application() {

  companion object {
    var activityList : ArrayList<Activity>?=null
    var app: BaseApplication?=null
  }

  override fun onCreate() {
    super.onCreate()
    app = this
    activityList = ArrayList()
  }


  fun removeActivity(){
    for (x in activityList!!.indices){
      activityList!![x].finish()
    }
  }

}