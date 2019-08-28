package com.example.kotlinfirst.view.act

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kotlinfirst.R
import com.example.kotlinfirst.adapter.MainTabAdapter
import com.example.kotlinfirst.contract.MainContract
import com.example.kotlinfirst.databean.NavigationTabBean
import com.example.kotlinfirst.presenter.MainPresenter
import com.example.kotlinfirst.view.fragment.HomeFragment
import com.example.kotlinfirst.view.fragment.MineFragment
import com.example.kotlinfirst.view.fragment.RepositoriesFragment
import kotlinx.android.synthetic.main.activity_main.*
import mvp.ljb.kt.act.BaseMvpFragmentActivity

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description input description
 **/
class MainActivity : BaseMvpFragmentActivity<MainContract.IPresenter>(), MainContract.IView {

  companion object {
    private const val KEY_INDEX = "index"
    private const val WAIT_TIME: Long = 3000L
  }

  private var mTouchTime: Long = 0L
  private var mCurIndex: Int = 0

  private val mFragments = listOf<Fragment>(
    HomeFragment(),
    MineFragment()
  )

  private val mNavigationTabList = listOf(
    NavigationTabBean(R.drawable.tab_menu_home, "首页"),
    NavigationTabBean(R.drawable.tab_menu_mine, "我的")
  )

  override fun registerPresenter() = MainPresenter::class.java

  override fun getLayoutId(): Int = R.layout.activity_main

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putInt(KEY_INDEX, mCurIndex)
  }

  override fun init(savedInstanceState: Bundle?) {
    super.init(savedInstanceState)
    savedInstanceState?.let {
      supportFragmentManager.popBackStackImmediate(null, 1)
      mCurIndex = it.getInt(KEY_INDEX)
    }
  }

  override fun initView() {
    tgv_group.setOnItemClickListener { selectedTabFragment(it) }
    tgv_group.setAdapter(MainTabAdapter(this, mNavigationTabList))
    selectedTabFragment(mCurIndex)
  }

  private fun selectedTabFragment(position: Int) {
    tgv_group.setSelectedPosition(position)
    val ft = supportFragmentManager.beginTransaction()
    ft.hide(mFragments[mCurIndex])
    var f = supportFragmentManager.findFragmentByTag(mFragments[position].javaClass.simpleName)
    if (f == null) {
      f = mFragments[position]
      ft.add(R.id.fl_content, f, f.javaClass.simpleName)
    }
    ft.show(f).commit()
    mCurIndex = position
  }

  override fun onBackPressed() {
    if (System.currentTimeMillis() - mTouchTime > WAIT_TIME) {
      Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show()
      mTouchTime = System.currentTimeMillis()
      return
    }
    super.onBackPressed()
  }
}

