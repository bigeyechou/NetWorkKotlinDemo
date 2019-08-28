package com.example.kotlinfirst.view.fragment

import com.example.kotlinfirst.R
import com.example.kotlinfirst.contract.HomeContract
import com.example.kotlinfirst.presenter.HomePresenter
import mvp.ljb.kt.fragment.BaseMvpFragment

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class HomeFragment : BaseMvpFragment<HomeContract.IPresenter>(), HomeContract.IView {

    override fun registerPresenter() = HomePresenter::class.java

    override fun getLayoutId(): Int = R.layout.fragment_home_layout
}
