package com.catail.bes_vision.home.activity

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseActivity
import com.catail.bes_vision.home.bean.ChangePasswordRequestbean
import com.catail.bes_vision.home.bean.ChangePasswordResultbean
import com.catail.bes_vision.home.bean.LoginBean
import com.catail.bes_vision.utils.*
import com.catail.bes_vision.utils.Common.Companion.stringToObject
import com.catail.bes_vision.utils.GsonUtil.GsonString
import com.catail.bes_vision.utils.GsonUtil.GsonToBean
import com.catail.bes_vision.utils.Logger.e
import com.catail.bes_vision.utils.MD5Crypto.md5
import com.catail.bes_vision.utils.MyLog.loger
import com.catail.bes_vision.utils.Preference.getSysparamFromSp
import com.catail.bes_vision.utils.Util.showDialogLogin
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.Callback
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.title_bar.*
import okhttp3.Call
import okhttp3.MediaType
import okhttp3.Response

/**
 * 修改密码
 *
 * @author Administrator
 */
class ChangePassordActivty : BaseActivity(), View.OnClickListener {

    override fun layoutResId(): Int {
        return R.layout.activity_change_password
    }

    override fun initView() {
        // 标题
        tv_title.text = resources.getString(R.string.change_password)
        // 回退按钮
        title_bar_left_menu.visibility = View.VISIBLE
        title_bar_left_menu.setOnClickListener(this)

        /* 密码修改监听 */
        change_password_btn.setOnClickListener(this)
    }

    override fun initData() {}

    /**
     * 密码验证 符合密码修改标准为true 否则false 标准：新老密码不为空，不相同....
     *
     * @return
     */
    private fun passwordVerification(): Boolean {
        if (old_password!!.text.toString() == "") {
            Toast.makeText(
                this@ChangePassordActivty, resources.getString(R.string.input_old_psw),
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (new_password!!.text.toString() == "") {
            Toast.makeText(
                this@ChangePassordActivty, resources.getString(R.string.input_new_psw),
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (edit_change_password!!.text.toString() == "") {
            Toast.makeText(
                this@ChangePassordActivty, resources.getString(R.string.input_new_psw2),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        if (old_password!!.text.toString() == new_password!!.text.toString()) {
            Toast.makeText(
                this@ChangePassordActivty, resources.getString(R.string.psw_verification),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        if (new_password!!.text.toString() != edit_change_password!!.text.toString()) {
            Toast.makeText(
                this@ChangePassordActivty, resources.getString(R.string.new_verfication),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        if (new_password!!.text.toString()
                .trim { it <= ' ' }.length < 6 || old_password!!.text.toString()
                .trim { it <= ' ' }.length >= 9
        ) {
            Toast.makeText(
                this@ChangePassordActivty, resources.getString(R.string.the_password_must_be_bits),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        if (edit_change_password!!.text.toString().trim { it <= ' ' }.length < 6
            || old_password!!.text.toString().trim { it <= ' ' }.length >= 9
        ) {
            Toast.makeText(
                this@ChangePassordActivty, resources.getString(R.string.the_password_must_be_bits),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    override fun onClick(v: View) {
        // TODO Auto-generated method stub
        when (v.id) {
            R.id.change_password_btn -> changePassword()
            R.id.title_bar_left_menu -> finish()
            else -> {}
        }
    }

    /*
     * 修改密码
     */
    private fun changePassword() {
        if (passwordVerification()) {
            ChangePassword()
        }
    }

    /**
     * 查询项目成员
     */
    private fun ChangePassword() {
        showProgressDialog(msg)
        try {
            val loginBean = stringToObject(getSysparamFromSp(Config.LOGIN_BEAN)) as LoginBean
            val qaRequestBean = ChangePasswordRequestbean().apply {
                uid = loginBean.uid
                token = loginBean.token
                oldpwd = md5(old_password!!.text.toString().trim { it <= ' ' })
                newpwd1 = md5(new_password!!.text.toString().trim { it <= ' ' })
                newpwd2 = md5(edit_change_password!!.text.toString().trim { it <= ' ' })
            }
            val json = GsonString(qaRequestBean)
            e("CMSC0002--修改密码--上传参数", json)
            OkHttpUtils
                .postString()
                .url(NetApi.changePassword + "")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json)
                .build()
                .execute(object : Callback<Any?>() {
                    @Throws(Exception::class)
                    override fun parseNetworkResponse(response: Response, id: Int): Any? {
                        dismissProgressDialog()
                        val json = response.body()!!.string()
                        loger("CMSC0002--修改密码--返回值", json)
                        return GsonToBean(
                            json, ChangePasswordResultbean::class.java
                        )
                    }

                    override fun onError(call: Call, e: Exception, id: Int) {
                        dismissProgressDialog()
                        e("Exception", e.toString())
                    }

                    override fun onResponse(response: Any?, id: Int) {
                        val resultBean = response as ChangePasswordResultbean?
                        try {

                            if (resultBean != null) {
                                if (resultBean.errno == 0) {
                                    /* 修改密码成功 */
                                    Toast.makeText(
                                        this@ChangePassordActivty,
                                        resources.getString(R.string.change_success),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    /* 密码修改成功，关闭此之前的全部activity，并重新登录 */setResult(2)
                                    finish()
                                    startActivity(
                                        Intent(
                                            this@ChangePassordActivty,
                                            LoginActivity::class.java
                                        )
                                    )
                                } else if (resultBean.errno == 2) {
                                    //其他设备登录
                                    e("resultBean.getErrno()==2", resultBean.errno.toString())
                                    showDialogLogin(this@ChangePassordActivty)
                                } else if (resultBean.errno == 7) {
                                    //无数据返回

                                    showToast(resultBean.errstr)
                                } else {
                                    //其他错误代码
                                    showToast(resultBean.errstr)
                                }
                            } else {
                                showToast(resources.getString(R.string.no_data))
                            }
                        } catch (e: Exception) {
                            showToast("UnFinished-Unknown Error")
                        }
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}