package com.example.kotlinfirst.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.kotlinfirst.contract.MineContract
import com.example.kotlinfirst.model.MineModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class MinePresenter : BaseMvpPresenter<MineContract.IView, MineContract.IModel>(), MineContract.IPresenter{

    override fun registerModel() = MineModel::class.java

}
