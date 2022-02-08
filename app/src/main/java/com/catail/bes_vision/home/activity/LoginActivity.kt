package com.catail.bes_vision.home.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import com.blankj.utilcode.util.PermissionUtils.*
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseActivity
import com.catail.bes_vision.home.bean.LoginBean
import com.catail.bes_vision.home.bean.LoginRequestBean
import com.catail.bes_vision.home.bean.LoginResultBean
import com.catail.bes_vision.utils.*
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.Callback
import kotlinx.android.synthetic.main.activity_login2.*
import okhttp3.Call
import okhttp3.MediaType
import okhttp3.Response
import java.util.*

class LoginActivity : BaseActivity(), View.OnClickListener {
    private var languageTextList: ArrayList<TextView>? = null // 切换中英文,'忘记密码'
    private var inputEditList: ArrayList<EditText>? = null// 用户名，密码输入框
    private var registerBtn: TextView? = null// 新用户注册按钮
    private var registerMsgText: TextView? = null// 注册文本信息
    private var loginBtn: Button? = null// 登录按钮
    private var loginBean: LoginBean? = null
    private var userName = ""


    override fun layoutResId(): Int {
        loginAgain()
        return R.layout.activity_login2
    }


    override fun initView() {
        languageTextList = ArrayList<TextView>()
        val tvEnglish: TextView = findViewById(R.id.tv_english)
        val tvChinese: TextView = findViewById(R.id.tv_chinese)
        val tvLanguage: TextView = findViewById(R.id.tv_language)
        tvLanguage.setOnClickListener(this)
        val tvForgetPassword: TextView = findViewById(R.id.tv_forget_password)
        languageTextList!!.add(tvEnglish)
        languageTextList!!.add(tvChinese)
        languageTextList!!.add(tvForgetPassword)

        inputEditList = ArrayList<EditText>()
        val etName: EditText = findViewById(R.id.et_name)
        val etPswd: EditText = findViewById(R.id.et_pswd)
        inputEditList!!.add(etName)
        inputEditList!!.add(etPswd)

        // 版本号

        // 版本号
        registerBtn = findViewById(R.id.register)
        registerMsgText = findViewById(R.id.register_msg)
//        versionCodeText = findViewById(R.id.version_code)
        //系统的版本号
        loginBtn = findViewById(R.id.btn_login)

        try {
            /* 获取版本号 */
            Thread.sleep(20)
            version_code.text = "V" + packageManager.getPackageInfo(packageName, 0).versionName
            tv_cms_version_code.text =
                "V" + packageManager.getPackageInfo(packageName, 0).versionName
        } catch (e1: PackageManager.NameNotFoundException) {
            e1.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        for (i in languageTextList!!.indices) {
            languageTextList!!.get(i).setOnClickListener(this)
        }
        /* 登录按钮 */
        /* 登录按钮 */
        loginBtn?.setOnClickListener(this)
        try {
            if (Preference.sysParamSp != null) {
                if (Preference.sysParamSp!!.contains(Config.LOGIN_BEAN)) {
                    val objectVal: String? =
                        Preference.getSysparamFromSp(Config.LOGIN_BEAN) // 获取个人基本信息
                    loginBean = Utils.stringToObject(objectVal) as LoginBean // 登录信息字符串转对象
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (loginBean != null) {
            if (loginBean!!.getUserName() != null) {
                inputEditList!!.get(0).setText(loginBean!!.getUserName()) // 记忆用户姓名
                inputEditList!!.get(0).setSelection(
                    inputEditList!!.get(0).getText().toString().trim { it <= ' ' }.length
                ) // 设置姓名内容光标处于末尾
            }
        }
        inputEditList!!.get(1)
            .setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
                val userName: String =
                    inputEditList!!.get(0).getText().toString().trim { it <= ' ' }
                val password: String =
                    inputEditList!!.get(1).getText().toString().trim { it <= ' ' }
                try {
                    if (validateLogin(userName, password)) userLogin(userName, password)
                } catch (e: Exception) {
                    Toast.makeText(
                        this@LoginActivity, resources.getString(R.string.login_exception),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                true
            })

        /* 注册事件 */
        registerBtn?.setOnClickListener(this)


    }

    override fun initData() {

    }


    /**
     * 免登陆
     */
    private fun loginAgain() {
        try {
            val loginBean: LoginBean =
                Common.stringToObject(Preference.getSysparamFromSp(Config.LOGIN_BEAN)) as LoginBean
            if (loginBean != null && loginBean.isSuccess()) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                //                startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                finish()
            }
        } catch (e: Exception) {
//            e.printStackTrace();
        }
    }

    /**
     * 登录验证
     */
    private fun validateLogin(userName: String, password: String): Boolean {
        if (userName.length == 0 || userName == "") {
            Toast.makeText(
                this@LoginActivity,
                getString(R.string.hint_enter_name),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        if (password.length == 0 || password == "") {
            Toast.makeText(
                this@LoginActivity,
                getString(R.string.hint_enter_pswd),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> {
                val userName: String =
                    inputEditList?.get(0)?.getText().toString().trim { it <= ' ' }
                val password: String =
                    inputEditList?.get(1)?.getText().toString().trim { it <= ' ' }
                try {
                    if (validateLogin(userName, password)) {
                        userLogin(userName, password)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(
                        this@LoginActivity, resources.getString(R.string.login_exception),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            R.id.tv_forget_password ->
                startActivity(Intent(this@LoginActivity, FindPasswordActivity::class.java))
        }
    }


    /**
     * 用户登录
     */
    private fun userLogin(uin: String, pwdStr: String) {
        try {
            showProgressDialog(msg)
            userName = uin
            val pwdEncryptionStr: String? = MD5Crypto.md5(pwdStr)
            val deviceInfo = ("AND"
                    + "|手机品牌:" + Build.BRAND
                    + "|程序SDK:" + Build.VERSION.RELEASE
                    + "|手机SDK:" + Build.VERSION.SDK_INT
                    + "|APP版本:"
                    + "V" + packageManager.getPackageInfo(packageName, 0).versionName)
//            val loginRequestBean = LoginRequestBean()

            val apply = LoginRequestBean().apply {
                phone = uin
                pwd = pwdEncryptionStr.toString()
                device_info = deviceInfo
            }
//            var phone: String? = null
//            var pwd: String? = null
//            var device_info: String? = null

            val json: String? = GsonUtil.GsonString(apply)
            Logger.e("CMSC0001--登录--上传值", json)
            Logger.e("登录URL==", NetApi.Login)
            OkHttpUtils
                .postString().url(NetApi.Login)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json)
                .build()
                .execute(object : Callback<Any?>() {
                    @Throws(Exception::class)
                    override fun parseNetworkResponse(response: Response, id: Int): Any? {
                        dismissProgressDialog()
                        val json = response.body()!!.string()
                        Logger.e("CMSC0001--登录--返回值", json)
                        return GsonUtil.GsonToBean(json, LoginResultBean::class.java)
                    }

                    override fun onError(call: Call, e: Exception, id: Int) {
                        dismissProgressDialog()
                        e.printStackTrace()
                        //            Logger.e("onError", e.getMessage().toString());
                        try {
                            val loginData = LoginBean()
                            loginData.setUserName(userName)
                            loginData.setSuccess(false)
                            val objectVal: String? = Utils.objectToString(loginData)
                            Preference.saveSysparamsToSp(Config.LOGIN_BEAN, objectVal)
                        } catch (e1: Exception) {
                            e1.printStackTrace()
                        }
                    }

                    override fun onResponse(response: Any?, id: Int) {
                        try {
                            val resultBean: LoginResultBean? = response as LoginResultBean?
                            Logger.e("CMSC0001--登录--返回值", GsonUtil.GsonString(loginBean));
                            if (resultBean?.errno === 0) {
                                try {
                                    val loginData = LoginBean()
                                    loginData.setUserName(userName)
                                    loginData.setSuccess(true)
                                    loginData.setUid(resultBean.result?.uid)
                                    loginData.setToken(resultBean.result?.token)
                                    val objectVal: String? = Utils.objectToString(loginData)
                                    Preference.saveSysparamsToSp(Config.LOGIN_BEAN, objectVal)
                                    val intent =
                                        Intent(this@LoginActivity, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            } else {
                                showToast(resultBean?.errstr)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}