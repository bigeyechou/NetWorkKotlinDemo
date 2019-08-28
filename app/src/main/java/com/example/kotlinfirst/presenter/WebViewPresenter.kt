package com.example.kotlinfirst.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.kotlinfirst.contract.WebViewContract
import com.example.kotlinfirst.model.WebViewModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class WebViewPresenter : BaseMvpPresenter<WebViewContract.IView, WebViewContract.IModel>(), WebViewContract.IPresenter{

    override fun registerModel() = WebViewModel::class.java

}
