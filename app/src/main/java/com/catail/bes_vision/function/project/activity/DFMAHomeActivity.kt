package com.catail.bes_vision.function.project.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseActivity
import kotlinx.android.synthetic.main.title_bar.*

class DFMAHomeActivity : BaseActivity(), View.OnClickListener {
    override fun layoutResId(): Int {
        return R.layout.activity_dfmahome
    }

    override fun initView() {
        title_bar_left_menu.setOnClickListener(this)
        title_bar_left_menu.visibility=View.VISIBLE
        tv_title.text = resources.getText(R.string.preject_function5)
    }

    override fun initData() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.title_bar_left_menu -> this@DFMAHomeActivity.finish()
        }
    }

}