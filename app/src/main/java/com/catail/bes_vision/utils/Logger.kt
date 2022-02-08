package com.catail.bes_vision.utils

import android.util.Log

object Logger {
    var ENABLE = true
    val LEVEL_V = 0
    val LEVEL_D = 1
    val LEVEL_I = 2
    val LEVEL_W = 3
    val LEVEL_E = 4

    var LEVEL = LEVEL_D // 设置日志可打印的最高级别


    @JvmStatic
    fun v(tag: String?, msg: String?) {
        if (ENABLE) {
            if (LEVEL <= LEVEL_V) {
                Log.v(tag, msg!!)
            }
        }
    }

    @JvmStatic
    fun d(msg: String?) {
        val stackTrace = Thread.currentThread().stackTrace
        // com.itheima.mobilesafe92.activity.HomeActivity
        val className = stackTrace[3].className
        val TAG = className.substring(className.lastIndexOf(".") + 1)
        Logger.d(TAG, msg)
    }

    @JvmStatic
    fun d(tag: String?, msg: String?) {
        if (ENABLE) {
            if (LEVEL <= LEVEL_D) {
                Log.d(tag, msg!!)
            }
        }
    }

    @JvmStatic
    fun i(tag: String?, msg: String?) {
        if (ENABLE) {
            if (LEVEL <= LEVEL_I) {
                Log.i(tag, msg!!)
            }
        }
    }

    @JvmStatic
    fun w(tag: String?, msg: String?) {
        if (ENABLE) {
            if (LEVEL <= LEVEL_W) {
                Log.w(tag, msg!!)
            }
        }
    }

    @JvmStatic
    fun e(tag: String?, msg: String?) {
        if (ENABLE) {
            if (LEVEL <= LEVEL_E) {
                Log.e(tag, msg!!)
            }
        }
    }

    @JvmStatic
    fun e(msg: String?) {
        val stackTrace = Thread.currentThread().stackTrace
        // com.itheima.mobilesafe92.activity.HomeActivity
        val className = stackTrace[3].className
        val TAG = className.substring(className.lastIndexOf(".") + 1)
        Logger.e(TAG, msg)
    }

}