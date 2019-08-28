package com.example.kotlinfirst.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.kotlinfirst.R
import com.example.kotlinfirst.databean.NavigationTabBean
import com.example.kotlinfirst.widget.TabGroupView

class MainTabAdapter(private val mContext: Context, val mData: List<NavigationTabBean>) : TabGroupView.TabAdapter {

  private val  mLayoutInflater = LayoutInflater.from(mContext)

  override fun getCount() = mData.size

  override fun getTabView(position: Int, parent: ViewGroup?): View {
    val itemBean = mData[position]
    val view = mLayoutInflater.inflate(R.layout.bottom_tab_layout,parent,false)
    view.findViewById<AppCompatImageView>(R.id.iv_tab_icon).setImageResource(itemBean.iconResID)
    view.findViewById<AppCompatTextView>(R.id.tv_tab_text).setText(itemBean.textStr)
    return view
  }
}