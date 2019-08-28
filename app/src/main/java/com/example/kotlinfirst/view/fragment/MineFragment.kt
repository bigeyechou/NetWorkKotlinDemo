package com.example.kotlinfirst.view.fragment

import com.example.kotlinfirst.R
import com.example.kotlinfirst.contract.MineContract
import com.example.kotlinfirst.presenter.MinePresenter
import mvp.ljb.kt.fragment.BaseMvpFragment

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class MineFragment : BaseMvpFragment<MineContract.IPresenter>(), MineContract.IView {

    override fun registerPresenter() = MinePresenter::class.java

    override fun getLayoutId(): Int= R.layout.fragment_mine_layout
}
