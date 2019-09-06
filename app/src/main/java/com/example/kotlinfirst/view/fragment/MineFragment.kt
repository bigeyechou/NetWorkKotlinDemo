package com.example.kotlinfirst.view.fragment

import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.kotlinfirst.R
import com.example.kotlinfirst.adapter.ViewPagerFragmentAdapter
import com.example.kotlinfirst.contract.MineContract
import com.example.kotlinfirst.presenter.MinePresenter
import com.example.kotlinfirst.utils.PreferencesUtil
import kotlinx.android.synthetic.main.fragment_mine_layout.*
import mvp.ljb.kt.fragment.BaseMvpFragment



/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/28
 * @Description 我的页面
 **/
class MineFragment : BaseMvpFragment<MineContract.IPresenter>(), MineContract.IView, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

  private var fragmentList = ArrayList<Fragment>()
  lateinit var fragmentAdapter: ViewPagerFragmentAdapter

  override fun registerPresenter() = MinePresenter::class.java

  override fun getLayoutId(): Int = R.layout.fragment_mine_layout

  override fun initData() {
    super.initData()
  }


  override fun initView() {
    super.initView()
    tv_user_name.setText(PreferencesUtil.getString(PreferencesUtil.USER_NAME))
    tv_user_blog.setText(PreferencesUtil.getString(PreferencesUtil.USER_BLOG))

    vp_mine.addOnPageChangeListener(this)

    val followerFragment by lazy { FollowersFragmentFragment() }
    val followingFragment by lazy { FollowingFragmentFragment() }
    fragmentList.add(followerFragment)
    fragmentList.add(followingFragment)
    fragmentAdapter = ViewPagerFragmentAdapter(childFragmentManager, fragmentList)
    vp_mine.adapter = fragmentAdapter

  }

  override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
    when (checkedId) {
      R.id.rb_follower -> vp_mine.setCurrentItem(0)
      R.id.rb_following -> vp_mine.setCurrentItem(1)
    }
  }

  override fun onPageScrollStateChanged(state: Int) {

  }

  override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

  }

  override fun onPageSelected(position: Int) {
    vp_mine.setCurrentItem(position)
    (rg_tab.getChildAt(position) as RadioButton).isChecked = true
  }
}
