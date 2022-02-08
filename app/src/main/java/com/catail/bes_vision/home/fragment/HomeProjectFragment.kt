package com.catail.bes_vision.home.fragment

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseFragment
import com.catail.bes_vision.function.general.ProjectGeneralListActivity
import com.catail.bes_vision.function.project.activity.DFMAHomeActivity
import com.catail.bes_vision.function.project.activity.RFA_RFI_Activity
import com.catail.bes_vision.function.project.activity.ResourcesHomeActivity
import com.catail.bes_vision.function.project.activity.StatisticsHomeActivity
import com.catail.bes_vision.home.adapter.HomeAdapter
import com.catail.bes_vision.home.bean.HomeDataBean
import com.chad.library.adapter.base.BaseQuickAdapter
import java.util.*

class HomeProjectFragment : BaseFragment(), BaseQuickAdapter.OnItemChildClickListener {
    private var mDataList: ArrayList<HomeDataBean>? = null;
    private var homeAdapter: HomeAdapter? = null;
    private var themeStyleFlag = 0;
    override fun getContentViewLayoutId(): Int {
        return R.layout.fragment_home_project
    }

    override fun initView(view1: View?) {
        val mRvListview = view1!!.findViewById<RecyclerView>(R.id.rv_list);
//        val gm1 = GridLayoutManager(activity, 4)
//        mRvListview.layoutManager = gm1
        val glm = LinearLayoutManager(activity)
        mRvListview.setLayoutManager(glm)
        mDataList = ArrayList<HomeDataBean>()
        homeAdapter = HomeAdapter(mDataList);
        mRvListview.adapter = homeAdapter
        homeAdapter!!.setOnItemChildClickListener(this)
        themeStyleFlag = arguments?.getInt("theme_style")!!
        homeAdapter!!.setThemeStyleFlag(themeStyleFlag)
    }

    override fun initData() {
        initFunction()
    }


    fun initFunction() {
        val bean1 = HomeDataBean().apply {
            function_icon = R.mipmap.ic_launcher
            fuction_name = R.string.preject_function1
            fuction_msg = "0"
            function_item_type = 0
        }
        val bean2 = HomeDataBean().apply {
            function_icon = R.mipmap.ic_launcher
            fuction_name = R.string.preject_function2
            fuction_msg = "0"
            function_item_type = 1
        }
        val bean3 = HomeDataBean().apply {
            function_icon = R.mipmap.ic_launcher
            fuction_name = R.string.preject_function3
            fuction_msg = "0"
            function_item_type = 2
        }
        val bean4 = HomeDataBean().apply {
            function_icon = R.mipmap.ic_launcher
            fuction_name = R.string.preject_function4
            fuction_msg = "0"
            function_item_type = 3
        }
        val bean5 = HomeDataBean().apply {
            function_icon = R.mipmap.ic_launcher
            fuction_name = R.string.preject_function5
            fuction_msg = "0"
            function_item_type = 4
        }
        val bean6 = HomeDataBean().apply {
            function_icon = R.mipmap.ic_launcher
            fuction_name = R.string.preject_function6
            fuction_msg = "0"
            function_item_type = 5
        }
        val bean7 = HomeDataBean().apply {
            function_icon = R.mipmap.ic_launcher
            fuction_name = R.string.preject_function7
            fuction_msg = "0"
            function_item_type = 6
        }
        val bean8 = HomeDataBean().apply {
            function_icon = R.mipmap.ic_launcher
            fuction_name = R.string.preject_function8
            fuction_msg = "0"
            function_item_type = 7
        }
        mDataList!!.add(bean1)
        mDataList!!.add(bean2)
        mDataList!!.add(bean3)
        mDataList!!.add(bean4)
        mDataList!!.add(bean5)
        mDataList!!.add(bean6)
        mDataList!!.add(bean7)
        mDataList!!.add(bean8)
        homeAdapter?.notifyDataSetChanged()

    }


    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        when (mDataList?.get(position)?.fuction_name) {
            R.string.preject_function1 -> {
                val intent = Intent(activity, ResourcesHomeActivity::class.java);
//                val intent = Intent(activity, VerticalChartActivity::class.java);
//                val intent = Intent(activity, PieChartActivity::class.java);
//                val intent = Intent(activity, HorizontalChartActivity::class.java);
                intent.putExtra("", "")
                startActivity(intent);

            }
            R.string.preject_function2 -> {
                val intent = Intent(activity, ProjectGeneralListActivity::class.java);
                intent.putExtra("function_type", "Safety")
                startActivity(intent);
            }
            R.string.preject_function3
            -> {
                val intent = Intent(activity, StatisticsHomeActivity::class.java);
                intent.putExtra("", "")
                startActivity(intent);
            }
            R.string.preject_function4
            -> {
                val intent = Intent(activity, ProjectGeneralListActivity::class.java);
                intent.putExtra("function_type", "HCS")
                startActivity(intent);
            }
            R.string.preject_function5
            -> {
                val intent = Intent(activity, DFMAHomeActivity::class.java);
                intent.putExtra("", "")
                startActivity(intent);
            }
            R.string.preject_function6
            -> {
                val intent = Intent(activity, RFA_RFI_Activity::class.java);
                intent.putExtra("", "")
                startActivity(intent);
            }
            R.string.preject_function7
            -> {
                val intent = Intent(activity, ProjectGeneralListActivity::class.java);
                intent.putExtra("function_type", "Quality")
                startActivity(intent);
            }
            R.string.preject_function8 -> {
//                val intent = Intent(activity, ResourcesHomeActivity::class.java);
//                intent.putExtra("", "")
//                startActivity(intent);
            }
        }
    }


}