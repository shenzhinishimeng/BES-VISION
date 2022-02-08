package com.catail.bes_vision.home.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityCompat.recreate
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseFragment
import com.catail.bes_vision.home.activity.AboutActivity
import com.catail.bes_vision.home.activity.MainActivity
import com.catail.bes_vision.home.activity.SecurityManagerActivity
import com.catail.bes_vision.home.bean.ThemeBean
import com.catail.bes_vision.home.bean.UserInfoBean
import com.catail.bes_vision.utils.*
import com.catail.bes_vision.utils.Common.Companion.stringToObject
import com.catail.bes_vision.utils.Logger.e
import com.catail.bes_vision.utils.Preference.getSysparamFromSp
import kotlinx.android.synthetic.main.fragment_my.*

class HomeMyFragment : BaseFragment(), View.OnClickListener {

    override fun getContentViewLayoutId(): Int {
        return R.layout.fragment_my
    }

    override fun initView(view: View?) {
        val security_manager = view?.findViewById<TextView>(R.id.security_manager)
        val about_us = view?.findViewById<TextView>(R.id.about_us)
        val sign_out = view?.findViewById<TextView>(R.id.sign_out)


        security_manager?.setOnClickListener(this)
        about_us?.setOnClickListener(this)
        sign_out?.setOnClickListener(this)
    }

    override fun initData() {
        try {
            val userInfoBean = stringToObject(getSysparamFromSp(Config.USERINFO)) as UserInfoBean
            tv_name.text = userInfoBean.name
            tv_role_name.text = userInfoBean.roleNameEn
            GlideUtils.loadCirclePic(NetApi.IMG_SHOW_URL + userInfoBean.face_img, iv_face)
            e("userInfoBean", userInfoBean.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.security_manager -> {
                startActivityForResult(
                    Intent(getActivity(), SecurityManagerActivity::class.java),
                    1
                )
            }
            R.id.about_us -> {
//                startActivity(Intent(getActivity(), AboutActivity::class.java));
                //暂时用来切换主题
                try {
                    val themeBean: ThemeBean =
                        stringToObject(getSysparamFromSp(Config.THEME_BEAN)) as ThemeBean

                    if (themeBean.themeFlag == 0) {
                        themeBean.themeFlag = 1
                    } else if (themeBean.themeFlag == 1) {
                        themeBean.themeFlag = 0
                    }

                    Logger.e("save==" + themeBean.themeFlag)
                    val objectVal: String? = Utils.objectToString(themeBean)
                    val saveSysparamsToSp =
                        Preference.saveSysparamsToSp(Config.THEME_BEAN, objectVal)

                    Logger.e("saveSysparamsToSp===" + saveSysparamsToSp)

                    val themeBean1: ThemeBean =
                        Common.stringToObject(Preference.getSysparamFromSp(Config.THEME_BEAN)) as ThemeBean
                    Logger.e("themeBean1===" + themeBean1.themeFlag)

//                    ActivityCompat.recreate(this); //重启画面
                    startActivity(Intent(activity, MainActivity::class.java));
                    activity?.finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }


            }


        }
    }

    fun Intent2GooglePlay(activity: Activity) {
        //perform your task here like show alert dialogue "Need to upgrade app"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://details?id=" + activity.packageName)
        if (intent.resolveActivity(activity.packageManager) != null) { //可以接收
            activity.startActivity(intent)
        } else { //没有应用市场，我们通过浏览器跳转到Google Play
            intent.data =
                Uri.parse("https://play.google.com/store/apps/details?id=" + activity.packageName)
            activity.startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 2) {
            activity?.finish()
        }
    }
}