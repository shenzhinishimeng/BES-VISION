package com.catail.bes_vision.home.activity


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.*
import com.catail.bes_vision.BESVisionApplication
import com.catail.bes_vision.R
import com.catail.bes_vision.base.BaseActivity
import com.catail.bes_vision.utils.Config
import com.squareup.okhttp.*
import kotlinx.android.synthetic.main.activity_find_password.*
import java.io.IOException
import java.util.*

class FindPasswordActivity : BaseActivity(), View.OnClickListener {
    private var nextBtn: Button? = null// 下一步
    private var verficationCode: String? = null// 随机数记录
    private var timeI = 60
    private var SMSText: String? = null
    private var phoneNumYanZheng: String? = null
    private var response: Response? = null
    private val client: OkHttpClient = OkHttpClient()
    private val handler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == -9) {
                Log.e("iiii", timeI.toString() + "")
                get_code_text.text =
                    resources.getString(R.string.send_again).toString() + "(" + timeI + ")"
                get_code_text.setClickable(false)
            } else if (msg.what == -8) {
                get_code_text.text = resources.getString(R.string.get_code)
                get_code_text.setClickable(true)
                // i = 30;
            }
        }
    }

    override fun initView() {
        // 找回密码title
        val titleText: TextView = findViewById(R.id.tv_title)

        // 回退按钮
        val leftBtn: ImageView = findViewById(R.id.title_bar_left_menu)
        nextBtn = findViewById(R.id.next_btn)

        // “密码找回”
        titleText.setText(getResources().getString(R.string.retrieve_password))
        leftBtn.visibility = View.VISIBLE

        get_code_text.setOnClickListener(this)
        nextBtn!!.setOnClickListener(this)
        //        get_code_text.setClickable(false);
//        nextBtn.setClickable(false);
    }


    private fun btnSetting_1() {
        get_code_text.setTextColor(getResources().getColor(R.color.blue_textcolor_39bcca))
        nextBtn!!.setBackgroundResource(R.drawable.login_btn)
        nextBtn!!.setTextColor(getResources().getColor(R.color.white_background))
        get_code_text.setClickable(true)
        nextBtn!!.isClickable = true
    }

    private fun btnSetting_2() {
        get_code_text.setClickable(false)
        nextBtn!!.isClickable = false
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun layoutResId(): Int {

        return R.layout.activity_find_password
    }

    override fun initData() {

    }

    fun onFinish(view: View?) {
        finish()
    }

    /**
     * 设置重发时间为60s
     */
    private fun setTime() {
        Thread {
            for (i in 60 downTo 1) {
                timeI = i
                handler.sendEmptyMessage(-9)
                if (i <= 0) break
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            handler.sendEmptyMessage(-8)
        }.start()
    }

    val currentTime: Long
        get() = System.currentTimeMillis()
    private var FirstClickTime: Long = 0
    override fun onClick(v: View) {
        when (v.id) {
            R.id.get_code_text -> {
                if (phone_edit.getText().toString().isEmpty()) {
                    showToast(getResources().getString(R.string.please_enter_your_mobile_phone_number))
                    return
                }
                FirstClickTime = currentTime
                verficationCode = verificationCodeGeneration(4)
                Log.e("random=", verficationCode!!)
                phoneNumYanZheng = phone_edit.getText().toString()
                //                CheckPhoneIsExist(phoneNumYanZheng);
                SMSText = "【CMS】Your verification code is $verficationCode."
                val phone = "+65$phoneNumYanZheng"
                Log.e("SMCText", SMSText!!)
                Log.e("phone", phone)
                // +65 91687854
                SendSMSToIdentifying(phone)
                setTime()
            }
            R.id.next_btn -> {
                if (phone_edit.getText().toString().isEmpty() && code_edit.getText().toString()
                        .isEmpty()
                ) {
                    showToast(getResources().getString(R.string.please_enter_your_mobile_phone_number))
                    return
                }
                if (code_edit.getText().toString().isEmpty()) {
                    showToast(getResources().getString(R.string.please_enter_the_verification_code))
                    return
                }
                val SeconedClickTime = currentTime
                val resultTime = SeconedClickTime - FirstClickTime
                if (resultTime <= 60 * 1000 && resultTime >= 0) {
                    if (!phone_edit.getText().toString().trim { it <= ' ' }.isEmpty()) {
                        // 手机号不能为空
                        if (!code_edit.getText().toString().trim { it <= ' ' }.isEmpty()) {
                            // 验证码不能为空
                            if (phoneNumYanZheng == phone_edit.getText().toString()
                                    .trim { it <= ' ' }
                            ) {
                                // 发送验证码的手机和当前手机是否一致
                                if (verficationCode == code_edit.getText().toString()
                                        .trim { it <= ' ' }
                                ) {
                                    // 验证码是否一致
                                    val intent = Intent(
                                        this@FindPasswordActivity,
                                        ResetPassWordActivity::class.java
                                    )
                                    intent.putExtra(
                                        "phoneNumber",
                                        phone_edit.getText().toString().trim { it <= ' ' })
                                    startActivity(intent)
                                    this@FindPasswordActivity.finish()
                                } else {
                                    Toast.makeText(
                                        this@FindPasswordActivity,
                                        getResources().getString(R.string.verification_code_error),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    this@FindPasswordActivity,
                                    getResources().getString(
                                        R.string.verify_that_the_phone_is_inconsistent_with_the_input_phone_number
                                    ),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                this@FindPasswordActivity,
                                getResources().getString(R.string.the_captcha_can_not_be_empty),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    } else {
                        Toast.makeText(
                            this@FindPasswordActivity,
                            getResources().getString(R.string.the_phone_number_can_not_be_empty),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                } else {
                    Toast.makeText(
                        this@FindPasswordActivity,
                        getResources().getString(R.string.validation_timeout),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    /**
     * 使用JDK发送单条短信,智能匹配短信模板
     *
     * @param apikey
     * 成功注册后登录云片官网,进入后台可查看
     * @param text
     * 需要使用已审核通过的模板或者默认模板
     * @param mobile
     * 接收的手机号,仅支持单号码发送
     */
    // public void testSendSms(String apikey, String mobile, String text) {
    //
    // YunpianRestClient client = new YunpianRestClient(apikey);//
    // //用apikey生成client,可作为全局静态变量
    //
    // SmsOperator smsOperator = client.getSmsOperator();// 获取所需操作类
    // ResultDO<SendSingleSmsInfo> result = smsOperator.singleSend(mobile,
    // text);// 发送短信,ResultDO<?>.isSuccess()判断是否成功
    // Log.e("error", "短信：" + result);
    // }
    /**
     * 云片短信发送验证码
     */
    private fun SendSMSToIdentifying(phoneNum: String) {
        object : Thread() {
            override fun run() {
                val formBody: RequestBody =
                    FormEncodingBuilder().add("apikey", Config.YunPianApikey)
                        .add("text", SMSText).add("mobile", phoneNum).build()
                // Request request = new
                // Request.Builder().url("https://sms.yunpian.com/v2/sms/single_send.json")
                val request: Request =
                    Request.Builder().url("https://us.yunpian.com/v2/sms/single_send.json")
                        .post(formBody).build()
                try {
                    response = client.newCall(request).execute()
                    if (!response!!.isSuccessful()) {
                        throw IOException("Unexpected code $response")
                    }
                } catch (e1: IOException) {
                    e1.printStackTrace()
                }
            }
        }.start()
    } //    private void CheckPhoneIsExist(String userPhoneNum) {

    //        String url = Config.SERVER_URLTEST + ConstantValue.CheckPhoneIsExist;
    //        UserPhone userPhone = new UserPhone();
    //        userPhone.setUser_phone(userPhoneNum);
    //        String content = new Gson().toJson(userPhone);
    //        OkHttpUtils.postString().url(url).mediaType(MediaType.parse("application/json; charset=utf-8")).content(content)
    //                .build().execute(new MyStringCallback());
    //    }
    //    public class MyStringCallback extends Callback {
    //
    //        @Override
    //        public void onError(Call call, Exception e, int id) {
    //            Log.e("onError=", e.getMessage());
    //
    //        }
    //
    //        @Override
    //        public void onResponse(Object response, int id) {
    //            CheckPhoneIsExist checkPhoneIsExist = (CheckPhoneIsExist) response;
    //            if (!checkPhoneIsExist.getIsexist().isEmpty() || !checkPhoneIsExist.getIsexist().equals("")
    //                    || checkPhoneIsExist.getIsexist() != "") {
    //                // 获取的结果不为空
    //                if (checkPhoneIsExist.getIsexist().equals("1")) {
    //                    SMSText = "【CMS】" + "Your verification code is" + " " + verficationCode + ".";
    //                    String phone = "+65" + phoneNumYanZheng;
    //                    Log.e("SMCText", SMSText);
    //                    Log.e("phone", phone);
    //                    // +65 91687854
    //                    SendSMSToIdentifying(phone);
    //                    setTime();
    //                } else {
    //                    Toast.makeText(FindPasswordActivity.this, getResources().getString(R.string.check_phone_is_exist),
    //                            Toast.LENGTH_SHORT).show();
    //                }
    //            } else {
    //                return;
    //            }
    //        }
    //
    //        @Override
    //        public Object parseNetworkResponse(okhttp3.Response arg0, int arg1) throws Exception {
    //            String json = arg0.body().string();
    //            if (!json.isEmpty() || !json.equals("") || json != "") {
    //                // 获取的结果不为空
    //                CheckPhoneIsExist checkPhoneIsExist = GsonUtil.GsonToBean(json, CheckPhoneIsExist.class);
    //                return checkPhoneIsExist;
    //            } else {
    //                return null;
    //            }
    //
    //        }
    //
    //    }
    companion object {
        /**
         * 生成四位随机数
         */
        fun verificationCodeGeneration(num: Int): String {
            val digits = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
            val rnum = Random(Date().time)
            for (i in digits.indices) {
                val index = Math.abs(rnum.nextInt()) % 10
                val tmpDigit = digits[index]
                digits[index] = digits[i]
                digits[i] = tmpDigit
            }
            var returnStr = digits[0]
            for (i in 1 until num) {
                returnStr = digits[i] + returnStr
            }
            return returnStr
        }
    }
}