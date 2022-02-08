package com.catail.bes_vision.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.catail.bes_vision.BESVisionApplication
import com.catail.bes_vision.R
import com.catail.bes_vision.home.activity.LoginActivity
import com.catail.bes_vision.home.bean.LoginBean
import com.catail.bes_vision.utils.Preference.getSysparamFromSp
import com.catail.bes_vision.utils.Preference.saveSysparamsToSp
import com.catail.bes_vision.utils.Utils.objectToString
import com.catail.bes_vision.utils.Utils.setAlertDialogConner
import com.catail.bes_vision.utils.Utils.setAlertDialogSize
import com.catail.bes_vision.utils.Utils.stringToObject

object Util {
    /**
     * 重新登录
     */
    @JvmStatic
    fun showDialogLogin(activity: AppCompatActivity?) {
        val view = LayoutInflater.from(activity).inflate(R.layout.alertdialog_login, null)
        val alertDialog = AlertDialog.Builder(activity).create()
        alertDialog!!.setCancelable(false)
        alertDialog.setCanceledOnTouchOutside(false)
        if (activity != null && activity is Activity && !activity.isFinishing) {
            alertDialog.show()
        }
        val window = alertDialog.window
        window!!.setBackgroundDrawable(BitmapDrawable())
        window.setContentView(R.layout.alertdialog_login)
        val quitTextView = window.findViewById<TextView>(R.id.quit_text)
        quitTextView.setOnClickListener { view1: View? ->
            if (alertDialog != null && alertDialog.isShowing) alertDialog.dismiss()
            try {
                val loginBean = stringToObject(getSysparamFromSp(Config.LOGIN_BEAN)) as LoginBean?
                loginBean!!.isSuccess = false
                val objectVal = objectToString(loginBean)
                saveSysparamsToSp(Config.LOGIN_BEAN, objectVal)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            for (i in BESVisionApplication.activityList!!.indices) {
                BESVisionApplication.activityList!![i].finish()
            }
            /* 回退到登录界面 */
            val intent = Intent(activity, LoginActivity::class.java)
            activity!!.startActivity(intent)
        }
        val loginAgainText = window.findViewById<TextView>(R.id.login_again_text)
        loginAgainText.setOnClickListener { view12: View? ->
            if (alertDialog != null && alertDialog.isShowing) {
                alertDialog.dismiss()
            }
            try {
                val loginBean = stringToObject(getSysparamFromSp(Config.LOGIN_BEAN)) as LoginBean?
                loginBean!!.isSuccess = false
                val objectVal = objectToString(loginBean)
                saveSysparamsToSp(Config.LOGIN_BEAN, objectVal)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            for (i in BESVisionApplication.activityList!!.indices) {
                BESVisionApplication.activityList!![i].finish()
            }
            /* 回退到登录界面 */
            val intent = Intent(activity, LoginActivity::class.java)
            activity!!.startActivity(intent)
        }
        setAlertDialogConner(alertDialog)
        setAlertDialogSize(activity!!, alertDialog, 0.0)
    }
}