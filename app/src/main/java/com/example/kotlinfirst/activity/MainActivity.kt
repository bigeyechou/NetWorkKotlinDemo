package com.example.kotlinfirst.activity

import android.os.Bundle
import com.example.kotlinfirst.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(){

  override fun initLayout()=R.layout.activity_main

  override fun initWidget() {
    tv_title.text=""

  }


  override fun initData() {

  }

}
