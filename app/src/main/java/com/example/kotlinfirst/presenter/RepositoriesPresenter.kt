package com.example.kotlinfirst.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.kotlinfirst.contract.RepositoriesContract
import com.example.kotlinfirst.model.RepositoriesModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class RepositoriesPresenter : BaseMvpPresenter<RepositoriesContract.IView, RepositoriesContract.IModel>(), RepositoriesContract.IPresenter{

    override fun registerModel() = RepositoriesModel::class.java

}
