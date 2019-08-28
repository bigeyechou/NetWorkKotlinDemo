package com.example.kotlinfirst.presenter

import android.widget.Toast
import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.kotlinfirst.contract.LoginContract
import com.example.kotlinfirst.databean.UserBean
import com.example.kotlinfirst.model.LoginModel
import com.example.kotlinfirst.network.GitHubSubscribe
import com.example.kotlinfirst.network.OnSuccessAndFaultListener
import com.example.kotlinfirst.network.OnSuccessAndFaultSub
import com.example.kotlinfirst.utils.GsonUtils
import mvp.ljb.kt.presenter.getContextEx

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class LoginPresenter : BaseMvpPresenter<LoginContract.IView, LoginContract.IModel>(), LoginContract.IPresenter{
    override fun login(userName: String) {
            getModel().getUserInfo(userName, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
                override fun onSuccess(result: String) {
                    //成功
                    val userBean = GsonUtils.fromJson(
                        result,
                        UserBean::class.java
                    )
                    getMvpView().loginSuccess(userBean!!)
                }

                override fun onFault(errorMsg: String) {
                    //失败
                    getMvpView().loginError(errorMsg)
                }

            },getContextEx()))

    }

    override fun registerModel() = LoginModel::class.java

}
