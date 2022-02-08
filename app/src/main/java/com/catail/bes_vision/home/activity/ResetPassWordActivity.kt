package com.catail.bes_vision.home.activity

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.catail.bes_vision.BESVisionApplication
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseActivity
import com.catail.bes_vision.home.bean.ConfirmChangePWDBean
import com.catail.bes_vision.home.bean.ResetPwdRequestBean
import com.catail.bes_vision.utils.GsonUtil
import com.catail.bes_vision.utils.MD5Crypto
import com.catail.bes_vision.utils.NetApi
import com.google.gson.Gson
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.Callback
import kotlinx.android.synthetic.main.activity_resetpassword.*
import kotlinx.android.synthetic.main.title_bar.*
import okhttp3.Call
import okhttp3.MediaType
import okhttp3.Response

class ResetPassWordActivity : BaseActivity(), View.OnClickListener {
    private var resetpwd_New_PassWord
            : EditText? = null// 新密码
    private var resetpwd_Confirm_Password
            : EditText? = null// 确认新密码
    private var phoneNumber: String? = null// 前页面传来的电话号码


    override fun layoutResId(): Int {
        return R.layout.activity_resetpassword
    }

    override fun initView() {
        phoneNumber = getIntent().getStringExtra("phoneNumber")
        resetpwd_New_PassWord = findViewById(R.id.resetpwd_new_PassWord)
        resetpwd_Confirm_Password = findViewById(R.id.resetpwd_confirm_password)
        // 提交
        resetpwd_confirm.setOnClickListener(this)
        title_bar_left_menu.setOnClickListener(this)
        tv_title.setText(getResources().getString(R.string.restPassword_title))
    }


    override fun initData() {

    }

    fun ConfirmChangePassword(userPhoneNum: String?, userPwd: String?) {
        val url: String = NetApi.ResetPassword
        Log.e("URL", url + "")
        val MD5userPwd: String? = MD5Crypto.md5(userPwd)
        val resetPwd = ResetPwdRequestBean().apply {
            user_phone = userPhoneNum
            newpwd = MD5userPwd
        }
        val content: String = Gson().toJson(resetPwd)
        Log.e("content", content)
        OkHttpUtils.postString().url(url)
            .mediaType(MediaType.parse("application/json; charset=utf-8")).content(content)
            .build().execute(RestPwdCallBack())
    }

    inner class RestPwdCallBack : Callback<Any?>() {
        override fun onError(arg0: Call, arg1: Exception, arg2: Int) {
            Log.e("onError", arg1.message!!)
        }

        override fun onResponse(arg0: Any?, arg1: Int) {
            val confirmChangePWD: ConfirmChangePWDBean? = arg0 as ConfirmChangePWDBean?
            val result: String? = confirmChangePWD?.errno
            if (!result?.isEmpty()!!) {
                if (result == "0") {
                    val intent = Intent(this@ResetPassWordActivity, LoginActivity::class.java)
                    startActivity(intent)
                    this@ResetPassWordActivity.finish()
                } else {
                    Toast.makeText(
                        this@ResetPassWordActivity,
                        getResources().getString(R.string.password_modification_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                    this@ResetPassWordActivity.finish()
                }
            } else {
                return
            }
        }

        @Throws(Exception::class)
        override fun parseNetworkResponse(arg0: Response, arg1: Int): Any? {
            val json = arg0.body()!!.string()
            Log.e("json=", json)
            return GsonUtil.GsonToBean(json, ConfirmChangePWDBean::class.java)
        }
    }

    override fun onClick(arg0: View) {
        when (arg0.id) {
            R.id.resetpwd_confirm -> {
                val new_PassWord: String =
                    resetpwd_New_PassWord?.getText().toString().trim { it <= ' ' }
                val confirm_Password: String =
                    resetpwd_Confirm_Password?.getText().toString().trim { it <= ' ' }
                if (!new_PassWord.isEmpty()) {
                    // 第一次密码不能为空
                    if (!confirm_Password.isEmpty()) {
                        // 第二次输入密码不能为空
                        if (new_PassWord.length < 6 || new_PassWord.length >= 9) {
                            // 密码必须是6-8位
                            Toast.makeText(
                                this@ResetPassWordActivity,
                                getResources().getString(R.string.the_password_must_be_bits),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            return
                        }
                        if (confirm_Password.length < 6 || new_PassWord.length >= 9) {
                            // 密码必须是6-8位
                            Toast.makeText(
                                this@ResetPassWordActivity,
                                getResources().getString(R.string.the_password_must_be_bits),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            return
                        }
                        if (new_PassWord != confirm_Password) {
                            // 两次输入的密码不一致
                            Toast.makeText(
                                this@ResetPassWordActivity,
                                getResources().getString(R.string.the_two_passwords_do_not_agree),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            return
                        } else {
                            // TODO 确认修改密码
                            Log.e("New_PassWord", new_PassWord)
                            ConfirmChangePassword(phoneNumber, new_PassWord)
                        }
                    } else {
                        Toast.makeText(
                            this@ResetPassWordActivity,
                            getResources().getString(R.string.verify_that_the_password_is_not_empty),
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }
                } else {
                    Toast.makeText(
                        this@ResetPassWordActivity,
                        getResources().getString(R.string.the_password_cannot_be_empty),
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
            }
            R.id.title_bar_left_menu -> this@ResetPassWordActivity.finish()
            else -> {}
        }
    }
}