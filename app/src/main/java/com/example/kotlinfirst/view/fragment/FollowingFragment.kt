package com.example.kotlinfirst.view.fragment

import com.example.kotlinfirst.contract.FollowingContract
import com.example.kotlinfirst.presenter.FollowingPresenter
import mvp.ljb.kt.fragment.BaseMvpFragment

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class FollowingFragment : BaseMvpFragment<FollowingContract.IPresenter>(), FollowingContract.IView {

    override fun registerPresenter() = FollowingPresenter::class.java

    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
