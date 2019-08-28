package com.example.kotlinfirst.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.kotlinfirst.contract.MainContract
import com.example.kotlinfirst.model.MainModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class MainPresenter : BaseMvpPresenter<MainContract.IView, MainContract.IModel>(), MainContract.IPresenter{

    override fun registerModel() = MainModel::class.java

}
