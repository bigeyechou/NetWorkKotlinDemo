package com.example.kotlinfirst.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.kotlinfirst.contract.FollowingFragmentContract
import com.example.kotlinfirst.model.FollowingFragmentModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/09/06
 * @Description input description
 **/
class FollowingFragmentPresenter : BaseMvpPresenter<FollowingFragmentContract.IView, FollowingFragmentContract.IModel>(), FollowingFragmentContract.IPresenter{

    override fun registerModel() = FollowingFragmentModel::class.java

}
