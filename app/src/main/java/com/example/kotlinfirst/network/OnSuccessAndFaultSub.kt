package com.example.kotlinfirst.network

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import com.example.kotlinfirst.utils.CompressUtils
import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

class OnSuccessAndFaultSub constructor(mOnSuccessAndFaultListener: OnSuccessAndFaultListener): DisposableObserver<ResponseBody>(), ProgressCancelListener {

  private var showProgress = true
  private var mOnSuccessAndFaultListener: OnSuccessAndFaultListener
  private var mProgressDialog : ProgressDialog? =null

  init {
    this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
  }

  constructor(mOnSuccessAndFaultListener: OnSuccessAndFaultListener,context: Context):this(mOnSuccessAndFaultListener){
    this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
    mProgressDialog= ProgressDialog(context)
    mProgressDialog!!.setMessage("正在加载请稍后~")
  }
  constructor(mOnSuccessAndFaultListener: OnSuccessAndFaultListener,context: Context,showProgress:Boolean):this(mOnSuccessAndFaultListener){
    this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
    this.showProgress = showProgress
    mProgressDialog= ProgressDialog(context)
  }

  private fun showProgressDialog(){
    if (showProgress && null != mProgressDialog) {
      mProgressDialog!!.show()
    }
  }
  private fun dismissProgressDialog() {
    if (showProgress && null != mProgressDialog) {
      mProgressDialog!!.dismiss()
    }
  }

  /**
   * 订阅开始时调用
   * 显示ProgressDialog
   */
  override fun onStart() {
    super.onStart()
    showProgressDialog()
  }

  /**
   * 完成，隐藏ProgressDialog
   */
  override fun onComplete() {
    dismissProgressDialog()
    mProgressDialog = null
  }


  /**
   * 当result等于1回调给调用者，否则自动显示错误信息，若错误信息为401跳转登录页面。
   * ResponseBody  body = response.body();//获取响应体
   * InputStream inputStream = body.byteStream();//获取输入流
   * byte[] bytes = body.bytes();//获取字节数组
   * String str = body.string();//获取字符串数据
   */
  override fun onNext(body: ResponseBody) {
    try {
      val result = CompressUtils.decompress(body.byteStream())
      Log.e("body", result)
      mOnSuccessAndFaultListener.onSuccess(result)
      //TODO 天气接口返回数据格式没有resultCode等公共信息，onNext方法请根据自己公司接口返回的数据来调整
      //            JSONObject jsonObject = new JSONObject(result);
      //            int resultCode = jsonObject.getInt("ErrorCode");
      //            if (resultCode == 1) {
      //                mOnSuccessAndFaultListener.onSuccess(result);
      //            } else {
      //                String errorMsg = jsonObject.getString("ErrorMessage");
      //                mOnSuccessAndFaultListener.onFault(errorMsg);
      //                Log.e("OnSuccessAndFaultSub", "errorMsg: " + errorMsg);
      //            }
    } catch (e: Exception) {
      e.printStackTrace()
    }

  }

  /**
   * 对错误进行统一处理
   * 隐藏ProgressDialog
   */
  override fun onError(e: Throwable) {
    try {

      if (e is SocketTimeoutException) {//请求超时
      } else if (e is ConnectException) {//网络连接超时
        //                ToastManager.showShortToast("网络连接超时");
        mOnSuccessAndFaultListener.onFault("网络连接超时")
      } else if (e is SSLHandshakeException) {//安全证书异常
        //                ToastManager.showShortToast("安全证书异常");
        mOnSuccessAndFaultListener.onFault("安全证书异常")
      } else if (e is HttpException) {//请求的地址不存在
        val code = (e as HttpException).code()
        if (code == 504) {
          //                    ToastManager.showShortToast("网络异常，请检查您的网络状态");
          mOnSuccessAndFaultListener.onFault("网络异常，请检查您的网络状态")
        } else if (code == 404) {
          //                    ToastManager.showShortToast("请求的地址不存在");
          mOnSuccessAndFaultListener.onFault("请求的地址不存在")
        } else {
          //                    ToastManager.showShortToast("请求失败");
          mOnSuccessAndFaultListener.onFault("请求失败")
        }
      } else if (e is UnknownHostException) {//域名解析失败
        //                ToastManager.showShortToast("域名解析失败");
        mOnSuccessAndFaultListener.onFault("域名解析失败")
      } else {
        //                ToastManager.showShortToast("error:" + e.getMessage());
        mOnSuccessAndFaultListener.onFault("error:" + e.message)
      }
    } catch (e2: Exception) {
      e2.printStackTrace()
    } finally {
      Log.e("OnSuccessAndFaultSub", "error:" + e.message)
      //            mOnSuccessAndFaultListener.onFault("error:" + e.getMessage());
      dismissProgressDialog()
      mProgressDialog = null

    }
  }

  /**
   * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
   */
  override fun onCancelProgress() {
    if (!this.isDisposed) {
      this.dispose()
    }
  }
}