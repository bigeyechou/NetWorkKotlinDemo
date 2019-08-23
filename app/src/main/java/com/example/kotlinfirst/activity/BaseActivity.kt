package com.example.kotlinfirst.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kotlinfirst.app.App

abstract class BaseActivity : AppCompatActivity() {

  open var mContext: Context? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initBundle(intent.extras);
    setContentView(initLayout())
    mContext = this
    App.instance.addActivity(this)
    initWidget();
    initData();
  }

  open fun initBundle(bundle: Bundle?) {}

  abstract fun initLayout(): Int

  open fun initWidget() {}

  open fun initData() {}

  override fun onDestroy() {
    super.onDestroy()
    App.instance.removeActivity(this)
  }
}