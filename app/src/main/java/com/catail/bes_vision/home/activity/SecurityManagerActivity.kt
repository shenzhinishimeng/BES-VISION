package com.catail.bes_vision.home.activity

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseActivity

class SecurityManagerActivity : BaseActivity(), View.OnClickListener {


    override fun layoutResId(): Int {
        return R.layout.activity_security_manager
    }

    override fun initView() {
        val titleText = findViewById<TextView>(R.id.tv_title)
        // 修改密码
        val changePassText = findViewById<TextView>(R.id.change_password)
        val leftBtn = findViewById<ImageView>(R.id.title_bar_left_menu)
        leftBtn.visibility = View.VISIBLE
        changePassText.setOnClickListener(this)
        titleText.text = resources.getString(
            R.string.setting_security_management
        )
        leftBtn.setOnClickListener { finish() }
    }

    override fun initData() {}
    override fun onClick(v: View) {
        when (v.id) {
            R.id.change_password -> startActivityForResult(
                Intent(
                    this@SecurityManagerActivity,
                    ChangePassordActivty::class.java
                ), 1
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 2) {
            setResult(2)
            finish()
        }
    }
}