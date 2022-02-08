package com.catail.bes_vision.function.general

import android.view.View
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseActivity
import kotlinx.android.synthetic.main.title_bar.*

class ProjectGeneralListActivity : BaseActivity(), View.OnClickListener {
    private var function_type = "" // Safety ,HCS,Quality


    override fun layoutResId(): Int {
        return R.layout.activity_general_list
    }

    override fun initView() {
        title_bar_left_menu.setOnClickListener(this)
        title_bar_left_menu.visibility=View.VISIBLE


    }

    override fun initData() {
        function_type = intent.getStringExtra("function_type").toString();
        // Safety ,HCS,Quality
        when (function_type) {
            "Safety" -> {
                tv_title.text=resources.getText(R.string.preject_function2)
            }
            "HCS" -> {
                tv_title.text=resources.getText(R.string.preject_function4)
            }
            "Quality" -> {
                tv_title.text=resources.getText(R.string.preject_function7)
            }


        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.title_bar_left_menu-> this@ProjectGeneralListActivity.finish()
        }
    }
}