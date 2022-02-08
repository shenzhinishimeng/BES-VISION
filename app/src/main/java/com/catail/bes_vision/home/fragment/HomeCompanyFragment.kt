package com.catail.bes_vision.home.fragment

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseFragment
import com.catail.bes_vision.home.adapter.HomeAdapter
import com.catail.bes_vision.home.bean.HomeDataBean
import com.catail.bes_vision.utils.Logger
import com.chad.library.adapter.base.BaseQuickAdapter
import java.util.*

class HomeCompanyFragment : BaseFragment(), BaseQuickAdapter.OnItemChildClickListener {
    private var mDataList: ArrayList<HomeDataBean>? = null;
    private var homeAdapter: HomeAdapter? = null;
    override fun getContentViewLayoutId(): Int {
        return R.layout.fragment_home_company
    }

    override fun initView(view1: View?) {
        val mRvListview = view1!!.findViewById<RecyclerView>(R.id.rv_list);
        val gm1 = GridLayoutManager(activity, 4)
        mRvListview.layoutManager = gm1

        mDataList = ArrayList<HomeDataBean>()
//        homeAdapter = HomeAdapter(R.layout.adapter_home_item, mDataList);
        homeAdapter = HomeAdapter(mDataList);
        mRvListview.adapter = homeAdapter
        homeAdapter!!.setOnItemChildClickListener(this)

    }

    override fun initData() {
        initFunction()
    }

    fun initFunction() {
        val bean1 = HomeDataBean().apply {
            function_icon = R.mipmap.ic_launcher
            fuction_name = R.string.company_function1
            fuction_msg = "0"
            function_item_type=0
        }
        val bean2 = HomeDataBean().apply {
            function_icon = R.mipmap.ic_launcher
            fuction_name = R.string.company_function2
            fuction_msg = "0"
            function_item_type=0
        }
        val bean3 = HomeDataBean().apply {
            function_icon = R.mipmap.ic_launcher
            fuction_name = R.string.company_function3
            fuction_msg = "0"
            function_item_type=0
        }
        val bean4 = HomeDataBean().apply {
            function_icon = R.mipmap.ic_launcher
            fuction_name = R.string.company_function4
            fuction_msg = "0"
            function_item_type=0
        }
        val bean5 = HomeDataBean().apply {
            function_icon = R.mipmap.ic_launcher
            fuction_name = R.string.company_function5
            fuction_msg = "0"
            function_item_type=0
        }
        val bean6 = HomeDataBean().apply {
            function_icon = R.mipmap.ic_launcher
            fuction_name = R.string.company_function6
            fuction_msg = "0"
            function_item_type=0
        }
        val bean7 = HomeDataBean().apply {
            function_icon = R.mipmap.ic_launcher
            fuction_name = R.string.company_function7
            fuction_msg = "0"
            function_item_type=0
        }
        val bean8 = HomeDataBean().apply {
            function_icon = R.mipmap.ic_launcher
            fuction_name = R.string.company_function8
            fuction_msg = "0"
            function_item_type=0
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
            R.string.company_function1 -> Logger.e(resources.getString(R.string.company_function1));
            R.string.company_function2 -> Logger.e(resources.getString(R.string.company_function2));
            R.string.company_function3 -> Logger.e(resources.getString(R.string.company_function3));
            R.string.company_function4 -> Logger.e(resources.getString(R.string.company_function4));
            R.string.company_function5 -> Logger.e(resources.getString(R.string.company_function5));
            R.string.company_function6 -> Logger.e(resources.getString(R.string.company_function6));
            R.string.company_function7 -> Logger.e(resources.getString(R.string.company_function7));
            R.string.company_function8 -> Logger.e(resources.getString(R.string.company_function8));
        }
    }
}