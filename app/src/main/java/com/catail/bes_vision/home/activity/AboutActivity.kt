package com.catail.bes_vision.home.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseActivity
import com.catail.bes_vision.home.bean.ThemeBean
import com.catail.bes_vision.utils.*
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.title_bar.*

/**
 * 关于我们
 */
class AboutActivity : BaseActivity(), View.OnClickListener {

    override fun layoutResId(): Int {

        return R.layout.activity_about
    }


    override fun initView() {
        // 回退按钮
        val leftBtn = findViewById<ImageView>(R.id.title_bar_left_menu)
        // 应用标志图片
        val imgView = findViewById<ImageView>(R.id.img)
        tv_title.text = resources.getString(R.string.setting_about_us)
        leftBtn.visibility = View.VISIBLE // 显示回退按钮
        leftBtn.setOnClickListener(this)


    }

    override fun initData() {
        basicMsg
    }

    /**
     * 获取基本应用信息： 应用图片 版本信息
     */
    private val basicMsg: Unit
        private get() {
            val packageManager = packageManager
            try {
                val packageInfo = packageManager.getPackageInfo(packageName, 0)
                version?.text = "V_" + packageInfo.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
        }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.title_bar_left_menu -> finish()
        }
    }
}