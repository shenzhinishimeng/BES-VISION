package com.catail.bes_vision.utils

import android.content.pm.PackageInfo
import android.os.Environment
import com.catail.bes_vision.BESVisionApplication

object Config {

    val LOGIN_BEAN = "login_bean" // 用户登录成功后基本信息
    val THEME_BEAN = "theme_bean" // 主题颜色
    val USERINFO = "userInfo" // 用户信息
    val COMMITMENT_SHOW = "commitment_show" //用户第一次打开APP.要显示,

    val UPDATE_URL = "https://shell.cmstech.sg/update/version_cms_health_code.xml" //更新文件的xml
    var YunPianApikey = "f579becad5fb0555e182e35fcbc8d98e" // 云片短信的apikey


    //    public static final String PHOTO_SRC = Environment.getExternalStorageDirectory().toString() + "/CMS_SHELL/";// 图片存储路径
    val PHOTO_SRC: String = BESVisionApplication.getContext()?.getExternalCacheDir().toString()
        .toString() + "/PIC/" // 图片存储路径


    val SDStorage = Environment.getExternalStorageDirectory().toString() //SD卡的路径


    val SDStorageCache: String =
        BESVisionApplication.getContext()?.getExternalCacheDir().toString() + ""

    val BDLocation_INFO = "bdlocation_info" //百度定位基本参数信息


    val img = arrayOf("bmp", "jpg", "png", "jpeg", "gif")

    @JvmStatic
    fun getPackName(): String? {
        return try {
            val packageInfo: PackageInfo = BESVisionApplication.getContext()?.let {
                BESVisionApplication
                    .getContext()
                    ?.getPackageManager()
                    ?.getPackageInfo(it.getPackageName(), 0)
            }!!
            packageInfo.packageName
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    var ThambStr = "thumb_" //插入的缩略图的前缀


    var CMS_Privacy_Policy = "https://www.beehives.sg/policy/bes.html" //版本介绍的界面

}