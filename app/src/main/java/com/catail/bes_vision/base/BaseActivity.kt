package com.catail.bes_vision.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.catail.bes_vision.BESVisionApplication
import com.catail.bes_vision.R
import com.catail.bes_vision.home.bean.ThemeBean
import com.catail.bes_vision.utils.*
import com.catail.bes_vision.utils.Logger.e
import com.catail.bes_vision.utils.Utils.createLoadingDialog
import com.gyf.immersionbar.ImmersionBar

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var mImmersionBar: ImmersionBar
    protected var mContext: Context? = null
    private val alertDialog: AlertDialog? = null
    private val TAG = javaClass.simpleName
    protected var msg: String? = null
    protected var mThemeFlag: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTheme();
        setContentView(layoutResId())
        e("当前Activity名称==", TAG)
        BESVisionApplication.activityList?.add(this)
        //        ButterKnife.bind(this);
//        ActivityManager.getInstance().addActivity(this);//Activity管理工具
        msg = getString(R.string.processing)
        mContext = this
        initView() //初始化view
        initData() //初始化数据


    }

    /**
     * 返回当前Activity布局文件的id
     *
     * @return
     */
    abstract fun layoutResId(): Int
    abstract fun initView()
    abstract fun initData()


    /**
     * 初始化沉浸式状态栏
     */
    fun initImmersionBar(color: Int) {

        if (color == R.color.white_background) {
            mImmersionBar = ImmersionBar.with(this)
                .statusBarColor(color)
                .statusBarDarkFont(
                    true,
                    0.2f
                ) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
//                .fitsSystemWindows(true) //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看
        } else {
            mImmersionBar = ImmersionBar.with(this)
                .statusBarColor(color)
        }


        mImmersionBar.init() //初始化
    }


    /**
     * 显示吐司消息
     *
     * @param msg 吐司消息
     */
    fun showToast(msg: String?) {
        runOnUiThread {
            if (!TextUtils.isEmpty(msg)) {
                Toast.makeText(this@BaseActivity, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private var loadingDialog: Dialog? = null

    /**
     * 显示ProgressDialog
     */
    fun showProgressDialog(msg: String?) {
        if (loadingDialog == null) {
            loadingDialog = createLoadingDialog(this@BaseActivity, msg)
        }
        loadingDialog!!.show()
    }

    /**
     * 隐藏progressDialog
     */
    protected fun dismissProgressDialog() {
        if (loadingDialog != null) {
            loadingDialog!!.dismiss()
            loadingDialog = null
        }
    }


    fun initTheme() {
        try {
            val themeBean: ThemeBean =
                Common.stringToObject(Preference.getSysparamFromSp(Config.THEME_BEAN)) as ThemeBean
            Logger.e("try==" + themeBean.themeFlag)
            mThemeFlag = themeBean.themeFlag;
            initThemeStyle(mThemeFlag); //0默认白色  1黑色
        } catch (e: Exception) {
            Logger.e("catch")
            try {
                val themeBean = ThemeBean().apply {
                    themeFlag = 0;//0默认白色  1黑色
                }
                val objectVal: String? = Utils.objectToString(themeBean)
                Preference.saveSysparamsToSp(Config.THEME_BEAN, objectVal)

                initThemeStyle(0);
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }

    fun initThemeStyle(style: Int) {
        //0默认白色  1黑色
        Logger.e("style===" + style)

        if (style == 0) {
            setTheme(R.style.AppTheme)
            initImmersionBar(R.color.white_background) //初始化沉浸式状态栏
        } else {
            setTheme(R.style.AppTheme_dark)
            initImmersionBar(R.color.black_background_000000) //初始化沉浸式状态栏
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        BESVisionApplication.activityList?.remove(this)
        //        ActivityManager.getInstance().removeActivity(this);
    }


}