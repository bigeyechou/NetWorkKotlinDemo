package com.example.kotlinfirst.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.kotlinfirst.contract.FollowingContract
import com.example.kotlinfirst.model.FollowingModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class FollowingPresenter : BaseMvpPresenter<FollowingContract.IView, FollowingContract.IModel>(), FollowingContract.IPresenter{

    override fun registerModel() = FollowingModel::class.java

}
