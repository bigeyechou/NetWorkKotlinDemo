package com.example.kotlinfirst.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initBundle(intent.extras);
    setContentView(initLayout())
    initView();
    initData();
  }

  abstract fun initBundle(bundle: Bundle?)

  abstract fun initLayout(): Int

  abstract fun initView()

  abstract fun initData()
}