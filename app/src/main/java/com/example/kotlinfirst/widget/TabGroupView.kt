package com.example.kotlinfirst.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kotlinfirst.R

class TabGroupView : LinearLayout {

  private var mOnItemClickListener: ((position: Int) -> Unit)? = null

  constructor(context: Context) : super(context)

  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

  fun setOnItemClickListener(listener: (position: Int) -> Unit) {
    mOnItemClickListener = listener
  }

  fun setAdapter(adapter: TabAdapter?) {
    if (adapter !=null && adapter.getCount()>0){
      for (i in 0 until adapter.getCount()){
        val tabView = adapter.getTabView(i,this)
        val params = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT)
        params.weight = 1f
        params.gravity = Gravity.CENTER
        addView(tabView, params)
        tabView.setOnClickListener { mOnItemClickListener?.invoke(i) }
      }
    }
  }

  fun setSelectedPosition(position: Int) {
    initUnSelected()
    getChildAt(position).findViewById<View>(R.id.iv_tab_icon)?.isSelected = true
    getChildAt(position).findViewById<View>(R.id.tv_tab_text)?.isSelected = true

  }

  private fun initUnSelected() {
    (0 until childCount).mapNotNull {
      getChildAt(it).findViewById<View>(R.id.iv_tab_icon)?.isSelected = false
      getChildAt(it).findViewById<View>(R.id.tv_tab_text)?.isSelected = false
    }
  }

  interface TabAdapter {
    fun getCount(): Int
    fun getTabView(position: Int, parent: ViewGroup?): View
  }

}


