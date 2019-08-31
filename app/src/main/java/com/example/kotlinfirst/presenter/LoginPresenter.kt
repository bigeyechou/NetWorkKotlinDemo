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
import com.example.kotlinfirst.utils.PermissionUtils
import com.example.kotlinfirst.utils.PreferencesUtil
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.functions.Consumer
import mvp.ljb.kt.presenter.getContextEx
import java.util.*

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
                    val userBean = GsonUtils.fromJson(result, UserBean::class.java)

                    Observable.just(userBean).subscribe(Consumer {
                        PreferencesUtil.saveValue(PreferencesUtil.USER_ID,userBean?.id)
                        PreferencesUtil.saveValue(PreferencesUtil.USER_NAME,userBean?.name)
                        PreferencesUtil.saveValue(PreferencesUtil.USER_AVATAR,userBean?.avatar_url)
                        PreferencesUtil.saveValue(PreferencesUtil.USER_BLOG,userBean?.blog)
                    }).dispose()

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
