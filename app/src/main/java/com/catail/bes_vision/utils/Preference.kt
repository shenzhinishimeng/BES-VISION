package com.catail.bes_vision.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

object Preference {
    private val SAVE_SYSPARAM_NAME = "BES_Dashboard_Config"
    var sysParamSp: SharedPreferences? = null
    var spEdditor: SharedPreferences.Editor? = null

    @JvmStatic
    @SuppressLint("CommitPrefEdits")
    fun createSysparamSp(context: Context) {
        sysParamSp = context.getSharedPreferences(
            SAVE_SYSPARAM_NAME,  //context.MODE_WORLD_READABLE|context.MODE_WORLD_WRITEABLE);
            Context.MODE_PRIVATE
        )
        val sysParamSp1 = sysParamSp
        if (sysParamSp1 != null) {
            spEdditor = sysParamSp1.edit()
        }

    }

    @JvmStatic
    @Throws(Exception::class)
    fun saveSysparamsToSp(key: String?, value: String?): Boolean {
        try {
            if (sysParamSp != null) {
                spEdditor!!.putString(key, value)
                spEdditor!!.commit()
                return true
            }
        } catch (e: Exception) {
            // TODO: handle exception
            Log.e("error", "保存参数:" + e.message)
            throw Exception("参数保存异常")
        }
        return false
    }

    @JvmStatic
    @Throws(Exception::class)
    fun getSysparamFromSp(key: String?): String? {
        try {
            if (sysParamSp != null) {
                return sysParamSp!!.getString(key, "")
            }
        } catch (e: Exception) {
            // TODO: handle exception
            throw Exception("获取参数异常")
        }
        return null
    }

    @JvmStatic
    fun clearSp(context: Context, key: String?) {
        createSysparamSp(context)
        sysParamSp!!.edit().remove(key).commit()
    }

}