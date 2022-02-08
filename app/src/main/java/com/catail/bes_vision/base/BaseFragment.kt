package com.catail.bes_vision.base

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.catail.bes_vision.interfaces.UiOpration
import com.catail.bes_vision.utils.Logger.e
import com.catail.bes_vision.utils.Utils.createLoadingDialog

abstract class BaseFragment : Fragment(), UiOpration {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getContentViewLayoutId(), null)
        initView(view)
        return view
    }

    /**
     * 指示Fragment是否是第一次显示
     */
    private var firstShow = true
    var isVisibleToUser = false

    /**
     * 这个方法会由系统调用，当Fragment的可见性发生改变的时候会调用
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        if (isVisibleToUser && firstShow) {
            firstShow = false
            val runnable = Runnable {
                initData()
                e("initData", "initData()被执行了")
            }
            Handler().postDelayed(runnable, 20)
        }
    }

    /**
     * 显示吐司消息
     *
     * @param msg 吐司消息
     */
    fun showToast(msg: String?) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        }
    }

    private var loadingDialog: Dialog? = null

    /**
     * 显示ProgressDialog
     */
    protected fun showProgressDialog(msg: String?) {
        if (loadingDialog == null) {
            loadingDialog = activity?.let { createLoadingDialog(it, msg) }
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
}