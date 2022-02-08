package com.catail.bes_vision.interfaces

import android.view.View

interface UiOpration {
    fun getContentViewLayoutId(): Int

    fun initView(view: View?)

    fun initData()
}