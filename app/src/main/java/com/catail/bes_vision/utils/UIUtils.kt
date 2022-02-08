package com.catail.bes_vision.utils

import android.content.Context
import android.util.DisplayMetrics

object UIUtils {
    /**
     * dp转px
     */
    @JvmStatic
    fun dip2px(dpValue: Float, mContext: Context): Int {
        val scale = mContext.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 取屏幕宽度
     *
     * @return
     */
    @JvmStatic
    fun getScreenWidth(mContext: Context): Int {
        val dm = mContext.resources.displayMetrics
        return dm.widthPixels
    }

    /**
     * 取屏幕高度
     *
     * @return
     */
    @JvmStatic
    fun getScreenHeight(mContext: Context): Int {
        val dm = mContext.resources.displayMetrics
        return dm.heightPixels
    }

    /**
     * 取状态栏高度
     *
     * @return
     */
    @JvmStatic
    fun getStatusBarHeight(mContext: Context): Int {
        var result = 0
        val resourceId = mContext.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = mContext.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    /**
     * 取导航栏高度
     *
     * @return
     */
    @JvmStatic
    fun getNavigationBarHeight(mContext: Context): Int {
        var result = 0
        val resourceId =
            mContext.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = mContext.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}