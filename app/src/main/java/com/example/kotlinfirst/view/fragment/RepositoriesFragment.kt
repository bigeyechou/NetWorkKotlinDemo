package com.example.kotlinfirst.view.fragment

import com.example.kotlinfirst.contract.RepositoriesContract
import com.example.kotlinfirst.presenter.RepositoriesPresenter
import mvp.ljb.kt.fragment.BaseMvpFragment

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class RepositoriesFragment : BaseMvpFragment<RepositoriesContract.IPresenter>(), RepositoriesContract.IView {

    override fun registerPresenter() = RepositoriesPresenter::class.java

    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
