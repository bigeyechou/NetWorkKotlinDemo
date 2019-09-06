package com.example.kotlinfirst.view.fragment

import com.example.kotlinfirst.R
import com.example.kotlinfirst.contract.FollowersFragmentContract
import com.example.kotlinfirst.presenter.FollowersFragmentPresenter
import mvp.ljb.kt.fragment.BaseMvpFragment

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/09/06
 * @Description input description
 **/
class FollowersFragmentFragment : BaseMvpFragment<FollowersFragmentContract.IPresenter>(), FollowersFragmentContract.IView {

    override fun registerPresenter() = FollowersFragmentPresenter::class.java

    override fun getLayoutId(): Int = R.layout.fragment_follower_layout
}
