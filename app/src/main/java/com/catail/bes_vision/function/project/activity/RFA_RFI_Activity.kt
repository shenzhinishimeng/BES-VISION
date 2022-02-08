package com.catail.bes_vision.function.project.activity

import android.view.View
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseActivity
import kotlinx.android.synthetic.main.title_bar.*

class RFA_RFI_Activity : BaseActivity(), View.OnClickListener {


    override fun layoutResId(): Int {
        return R.layout.activity_rfa_rfi
    }

    override fun initView() {
        title_bar_left_menu.setOnClickListener(this)
        title_bar_left_menu.visibility=View.VISIBLE
        tv_title.text = resources.getText(R.string.preject_function6)

    }

    override fun initData() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.title_bar_left_menu -> this@RFA_RFI_Activity.finish()
        }
    }
}