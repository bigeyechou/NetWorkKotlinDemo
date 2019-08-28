package com.example.kotlinfirst.view.act

import android.Manifest
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinfirst.R
import com.example.kotlinfirst.contract.LoginContract
import com.example.kotlinfirst.databean.UserBean
import com.example.kotlinfirst.presenter.LoginPresenter
import com.example.kotlinfirst.utils.PermissionUtils
import kotlinx.android.synthetic.main.activity_login_layout.*
import mvp.ljb.kt.act.BaseMvpActivity
import mvp.ljb.kt.act.BaseMvpFragmentActivity

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class LoginActivity : BaseMvpActivity<LoginContract.IPresenter>(), LoginContract.IView {

  private val mLoadingDialog by lazy {
    ProgressDialog(this)
  }

  override fun registerPresenter() = LoginPresenter::class.java

  override fun getLayoutId(): Int = R.layout.activity_login_layout

  override fun initView() {
    btn_login.setOnClickListener {
      login()
    }
  }

  override fun initData() {
//    val login = getPresenter()
  }

  private fun login() {
    getPresenter().login(et_login_username.text?.trim().toString())
  }

  override fun loginSuccess(userBean: UserBean) {
    startActivity(Intent(this, MainActivity::class.java))
    finish()
  }

  override fun loginError(errorMsg: String?) {
    Toast.makeText(this, "登录失败，" + errorMsg, Toast.LENGTH_SHORT).show()
  }


}
