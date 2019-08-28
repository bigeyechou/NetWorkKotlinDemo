package com.example.kotlinfirst.contract

import com.example.kotlinfirst.databean.UserBean
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract
import okhttp3.ResponseBody

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
interface LoginContract {

  interface IView : IViewContract{
    fun loginSuccess(userBean: UserBean)
    fun loginError(errorMsg:String?)
  }

  interface IPresenter : IPresenterContract {
    fun login(userName: String)
  }

  interface IModel : IModelContract{
    fun getUserInfo(userName: String, subscriber: DisposableObserver<ResponseBody>): Observable<ResponseBody>
  }
}
