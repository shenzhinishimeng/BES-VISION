package com.catail.bes_vision

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.Utils
import com.catail.bes_vision.utils.Preference
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.https.HttpsUtils
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


class BESVisionApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        activityList = ArrayList()
        mContext = getApplicationContext()
        Preference.createSysparamSp(getApplicationContext())
        initOkHttp() //初始化okhttputils
        Utils.init(this) //初始化各种工具类
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val builder = VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())
        }
    }


    private fun initOkHttp() {
        val sslParams: HttpsUtils.SSLParams = HttpsUtils.getSslSocketFactory(null, null, null)
        val okHttpClient =
            OkHttpClient.Builder() //                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS) //其他配置
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build()
        OkHttpUtils.initClient(okHttpClient)
    }

    companion object {


        @SuppressLint("StaticFieldLeak")
        var mContext: Context? = null

        @JvmField
        var activityList: ArrayList<AppCompatActivity>? = null//所有的activity 集合

        @JvmStatic
        fun getContext(): Context? {
                return mContext
        }
    }

}