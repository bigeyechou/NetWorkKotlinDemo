package com.example.kotlinfirst.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerFragmentAdapter(fm:FragmentManager,private val fragments:List<Fragment>) :FragmentStatePagerAdapter(fm) {
  override fun getItem(position: Int): Fragment {

    return fragments[position]
  }

  override fun getCount(): Int {
    return fragments.size
  }

  override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    super.destroyItem(container, position, `object`)
  }

  override fun instantiateItem(container: ViewGroup, position: Int): Any {
    return super.instantiateItem(container, position)
  }

}