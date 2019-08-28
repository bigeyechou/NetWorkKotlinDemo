package com.example.kotlinfirst.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.kotlinfirst.contract.HomeContract
import com.example.kotlinfirst.model.HomeModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class HomePresenter : BaseMvpPresenter<HomeContract.IView, HomeContract.IModel>(), HomeContract.IPresenter{

    override fun registerModel() = HomeModel::class.java

}
