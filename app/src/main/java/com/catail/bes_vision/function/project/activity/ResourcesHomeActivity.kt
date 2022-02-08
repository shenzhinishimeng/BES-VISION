package com.catail.bes_vision.function.project.activity

import android.view.View
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseActivity
import com.catail.bes_vision.base.BaseFragment
import com.catail.bes_vision.function.project.adapter.ResourcesHomePagerAdapter
import com.catail.bes_vision.function.project.fragment.ResourceEquipmentFragment
import com.catail.bes_vision.function.project.fragment.ResourceWorkforceFragment
import kotlinx.android.synthetic.main.activity_resources_home.*
import kotlinx.android.synthetic.main.title_bar.*
import java.util.*

class ResourcesHomeActivity : BaseActivity(), View.OnClickListener {


    override fun layoutResId(): Int {
        return R.layout.activity_resources_home
    }

    override fun initView() {
        title_bar_left_menu.setOnClickListener(this)
        title_bar_left_menu.visibility = View.VISIBLE
        tv_title.text = resources.getText(R.string.preject_function1)


        val mTitleList: MutableList<String> = ArrayList()
        mTitleList.add(resources.getString(R.string.resources_workforce))
        mTitleList.add(resources.getString(R.string.resources_equipment))

        val mFragments: ArrayList<BaseFragment> = ArrayList<BaseFragment>()

        val resourceWorkforceFragment = ResourceWorkforceFragment()
        val resourceEquipmentFragment = ResourceEquipmentFragment()

        mFragments.add(resourceWorkforceFragment)
        mFragments.add(resourceEquipmentFragment)

        //
        val myPagerAdapter = ResourcesHomePagerAdapter(
            supportFragmentManager, mFragments, mTitleList
        )
        view_pager.adapter = myPagerAdapter
        tablayout.setViewPager(view_pager)
    }

    override fun initData() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.title_bar_left_menu -> this@ResourcesHomeActivity.finish()
        }
    }
}