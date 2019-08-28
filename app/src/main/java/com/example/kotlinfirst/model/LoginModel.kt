package  com.example.kotlinfirst.model

import com.example.kotlinfirst.contract.LoginContract
import com.example.kotlinfirst.databean.UserBean
import com.example.kotlinfirst.network.GitHubSubscribe
import com.example.kotlinfirst.network.OnSuccessAndFaultListener
import com.example.kotlinfirst.network.OnSuccessAndFaultSub
import com.example.kotlinfirst.utils.GsonUtils
import com.zhenggzh.dream.retrofitandrxjavademo.netutils.RetrofitFactory
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import mvp.ljb.kt.model.BaseModel
import mvp.ljb.kt.presenter.getContextEx
import okhttp3.ResponseBody

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class LoginModel : BaseModel(), LoginContract.IModel {
  override fun getUserInfo(userName: String, subscriber: DisposableObserver<ResponseBody>): Observable<ResponseBody> {

    val observable = RetrofitFactory.instance.httpApi!!.getUserInfoByName(userName)
    RetrofitFactory.instance.toSubscribe(observable, subscriber)
    return observable
  }
}