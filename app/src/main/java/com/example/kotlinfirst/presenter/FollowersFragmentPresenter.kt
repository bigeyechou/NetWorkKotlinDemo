package com.example.kotlinfirst.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.kotlinfirst.contract.FollowersFragmentContract
import com.example.kotlinfirst.model.FollowersFragmentModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/09/06
 * @Description input description
 **/
class FollowersFragmentPresenter : BaseMvpPresenter<FollowersFragmentContract.IView, FollowersFragmentContract.IModel>(), FollowersFragmentContract.IPresenter{

    override fun registerModel() = FollowersFragmentModel::class.java


}
