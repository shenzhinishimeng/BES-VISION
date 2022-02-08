package com.catail.bes_vision.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.catail.bes_vision.base.BaseFragment
import java.util.*

/**
 * Created by D on 2018/4/10.
 */
class MainPagerAdapter(
    fragmentManager: FragmentManager?,
    private val pagerList: ArrayList<BaseFragment>
) : FragmentPagerAdapter(
    fragmentManager!!
) {
    override fun getCount(): Int {
        return pagerList.size
    }

    override fun getItem(position: Int): Fragment {
        return pagerList[position]
    }
}