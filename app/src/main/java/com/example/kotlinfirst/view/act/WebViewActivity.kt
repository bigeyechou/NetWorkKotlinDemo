package com.example.kotlinfirst.view.act

import com.example.kotlinfirst.contract.WebViewContract
import com.example.kotlinfirst.presenter.WebViewPresenter
import mvp.ljb.kt.act.BaseMvpActivity

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class WebViewActivity : BaseMvpActivity<WebViewContract.IPresenter>() , WebViewContract.IView {

    override fun registerPresenter() = WebViewPresenter::class.java

    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
