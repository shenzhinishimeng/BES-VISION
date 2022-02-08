package com.catail.bes_vision.home.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseActivity
import com.catail.bes_vision.base.BaseFragment
import com.catail.bes_vision.home.adapter.MainPagerAdapter
import com.catail.bes_vision.home.adapter.MainProjectListAdapter
import com.catail.bes_vision.home.fragment.HomeMyFragment
import com.catail.bes_vision.home.fragment.HomeProjectFragment
import com.catail.bes_vision.utils.Logger
import com.catail.bes_vision.utils.UIUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.title_bar.*
import java.text.DecimalFormat
import java.util.*

class MainActivity : BaseActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private var mLeftDataLists: ArrayList<String>? = null
    private var mLeftRvListAdapter: MainProjectListAdapter? = null
    private var drawerToggle: ActionBarDrawerToggle? = null

    //    private var mHomeCompanyFragment: HomeCompanyFragment? = null
    private var mHomeProjectFragment: HomeProjectFragment? = null
    private var mHomeMyFragment: HomeMyFragment? = null;
    private var pagerList: ArrayList<BaseFragment>? = null


    override fun layoutResId(): Int {
        return R.layout.activity_main
    }


    override fun initView() {
        tv_title.text = resources.getString(R.string.app_name)
        //做首页单独颜色的时候用到   fl_content ,view_divide

        if (mThemeFlag == 0) {
            title_bar_left_menu.setBackgroundResource(R.mipmap.home_left_black_menu)
        } else {
            title_bar_left_menu.setBackgroundResource(R.mipmap.home_left_white_menu)
        }

        title_bar_left_menu.setOnClickListener(this)


//        view_pager

        //左侧区域

        val lm = LinearLayoutManager(this@MainActivity)
        lm.orientation = RecyclerView.VERTICAL
        rv_left_view.layoutManager = lm
        mLeftDataLists = ArrayList<String>()

        mLeftRvListAdapter = MainProjectListAdapter(
            R.layout.adapter_main_left_project_item, mLeftDataLists
        )

        rv_left_view.adapter = mLeftRvListAdapter
        mLeftRvListAdapter!!.setOnItemChildClickListener { adapter, view, position ->
            when (view.getId()) {
                R.id.rl_content -> {
                    //TODO 切换项目后 做的事
                }
            }
        }


        rg_group.setOnCheckedChangeListener(this)

        // 抽屉布局的控制开关，最后两个参数一般用不上，可以写0，用于指定打开和关闭抽屉的描述
        drawerToggle = ActionBarDrawerToggle(
            this, drawer_layout,
            null,
            -1,
            -1
        )
        drawerToggle!!.syncState() // 同步菜单和AcionBar的状态，把ActionBarDrawerToggle构造方法中的图片显示出来

        drawer_layout.setDrawerListener(drawerToggle) // 设置抽屉的监听器，用于监听抽屉的打开、关闭、滑动等等

        view_pager.setOnTouchListener { view, motionEvent -> false }

        //设置侧拉菜单为1/3 屏幕宽度
        val params: ViewGroup.LayoutParams = ll_content.getLayoutParams()
        val screenWidth: Int = UIUtils.getScreenWidth(this@MainActivity)
        params.height = LinearLayout.LayoutParams.MATCH_PARENT
        val df = DecimalFormat("0.000000000")
        val format = df.format((16.toFloat() / 9.toFloat()).toDouble())
        params.width = (screenWidth / java.lang.Float.valueOf(format)).toInt()
        ll_content.setLayoutParams(params)

        view_divide.setVisibility(View.GONE)
        //要不要单独设置沉浸式状态栏的颜色
//        fl_content.setBackgroundResource(R.drawable.home_statusbar_bg_pic)
//        initImmersionBar(R.color.blue_background_e4f8fb)


    }

    override fun initData() {


        pagerList = ArrayList<BaseFragment>();
//        mHomeCompanyFragment = HomeCompanyFragment()
        val bundle = Bundle()
        bundle.putInt("theme_style",mThemeFlag);

        mHomeProjectFragment = HomeProjectFragment()
        mHomeProjectFragment!!.arguments=bundle

        mHomeMyFragment = HomeMyFragment()
        mHomeMyFragment!!.arguments=bundle
//        pagerList?.add(mHomeCompanyFragment!!)

        pagerList?.add(mHomeProjectFragment!!)
        pagerList?.add(mHomeMyFragment!!)
        val adapter = MainPagerAdapter(supportFragmentManager, pagerList!!)
        view_pager.setAdapter(adapter)
        view_pager.offscreenPageLimit = 3
        tabStatus(0)

        Logger.e("pagerList==" + pagerList!!.size + "");
        Logger.e("view_pager==" + view_pager.childCount + "");

        try {
//            queryUserInfo()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_bar_left_menu -> "1"


        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.rb_home_company -> {
                tabStatus(0)
            }
            R.id.rb_home_project -> {
                tabStatus(0)
            }
            R.id.rb_home_my -> {
                tabStatus(1)
            }
        }
    }


    fun tabStatus(position: Int) {
//        if (position == 0) {
//            // 禁止手势滑动
//            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
//            title_bar_left_menu.visibility = View.GONE
//
//            rb_home_company.setTextColor(resources.getColor(R.color.blue_textcolor_39bcca))
//            rb_home_company.setChecked(true)
//            rb_home_project.setTextColor(resources.getColor(R.color.gray_textcolor))
//            rb_home_my.setTextColor(resources.getColor(R.color.gray_textcolor))
//            view_pager.setCurrentItem(0, false)
//        }
        if (position == 0) {
            //打开手势滑动
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            title_bar_left_menu.visibility = View.VISIBLE

            rb_home_company.setTextColor(resources.getColor(R.color.gray_textcolor))
            rb_home_project.setTextColor(resources.getColor(R.color.blue_textcolor_39bcca))
            rb_home_project.setChecked(true)
            rb_home_my.setTextColor(resources.getColor(R.color.gray_textcolor))
            view_pager.setCurrentItem(0, false)

        } else if (position == 1) {
            // 禁止手势滑动
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            title_bar_left_menu.visibility = View.GONE

            rb_home_company.setTextColor(resources.getColor(R.color.gray_textcolor))
            rb_home_project.setTextColor(resources.getColor(R.color.gray_textcolor))
            rb_home_my.setTextColor(resources.getColor(R.color.blue_textcolor_39bcca))
            rb_home_my.setChecked(true)
            view_pager.setCurrentItem(1, false)
        }
    }

}