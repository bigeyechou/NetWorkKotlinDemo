package com.example.kotlinfirst.view.fragment

import com.example.kotlinfirst.R
import com.example.kotlinfirst.contract.FollowingFragmentContract
import com.example.kotlinfirst.presenter.FollowingFragmentPresenter
import mvp.ljb.kt.fragment.BaseMvpFragment

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/09/06
 * @Description input description
 **/
class FollowingFragmentFragment : BaseMvpFragment<FollowingFragmentContract.IPresenter>(), FollowingFragmentContract.IView {

    override fun registerPresenter() = FollowingFragmentPresenter::class.java

    override fun getLayoutId(): Int = R.layout.fragment_following_layout
}
