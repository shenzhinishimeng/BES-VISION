package com.catail.bes_vision.utils

import android.util.Log

object MyLog {
    @JvmStatic
    fun loger(tag: String, message: String) {
        if (message.length > 3000) {
            var i = 0
            while (i < message.length) {
                if (i + 3000 < message.length) {
                    Log.e(tag + i, message.substring(i, i + 3000))
                } else {
                    Log.e(tag + i, message.substring(i))
                }
                i += 3000
            }
        } else {
            Log.e(tag, message)
        }
    }
}