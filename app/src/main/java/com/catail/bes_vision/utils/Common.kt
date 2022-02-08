package com.catail.bes_vision.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.catail.bes_vision.R
import java.io.*
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

class Common {

    private val dialog: AlertDialog? = null
    private val catagoryListView // 类别
            : ListView? = null

    companion object {
        /**
         * 对象转字符串
         *
         * @param object
         * @return
         * @throws IOException
         */
        @JvmStatic
        @Throws(IOException::class)
        fun objectToString(`object`: Any?): String {
            // 创建字节输出流
            val baos = ByteArrayOutputStream()
            // 创建字节对象输出流
            var out: ObjectOutputStream? = null

            // 然后通过将字对象进行64转码，写入key值为key的sp中
            out = ObjectOutputStream(baos)
            out.writeObject(`object`)
            val objectVal = String(
                Base64.encode(
                    baos.toByteArray(),
                    Base64.DEFAULT
                )
            )
            out.close()
            out.close()
            baos.close()
            return objectVal
        }

        /**
         * 字符串转对象
         *
         * @param objectVal
         * @return
         * @throws IOException
         * @throws ClassNotFoundException
         */
        @JvmStatic
        @Throws(IOException::class, ClassNotFoundException::class)
        fun stringToObject(objectVal: String?): Any {
            val buffer = Base64.decode(objectVal, Base64.DEFAULT)
            // 通过读取字节流，创建字节流输入流，写入对象
            val bais = ByteArrayInputStream(buffer)
            var ois: ObjectInputStream? = null
            ois = ObjectInputStream(bais)
            val `object` = ois.readObject()
            bais.close()
            ois.close()
            return `object`
        }

        /**
         * 像素转屏幕无关像素（dp）
         *
         * @param px
         * @param context
         * @return
         */
        @JvmStatic
        fun px2dp(px: Int, context: Context): Int {
            val desntity = context.resources.displayMetrics.density
            return (px / desntity + 0.5f).toInt()
        }

        /**
         * dp转px
         *
         * @param dp
         * @param context
         * @return
         */
        @JvmStatic
        fun dp2px(dp: Int, context: Context): Int {
            val desntity = context.resources.displayMetrics.density
            return (dp * desntity + 0.5f).toInt()
        }
        @JvmStatic
        fun showWindow(view: View?, width: Int, height: Int, parent: View) {
            val location = IntArray(2)
            parent.getLocationOnScreen(location)
            val popupWindow = PopupWindow(view, width, height, true)
            popupWindow.setBackgroundDrawable(BitmapDrawable())
            popupWindow.isOutsideTouchable = true
            popupWindow.isFocusable = true
            // popupWindow.setBackgroundDrawable(new ColorDrawable(R.color.green));
            popupWindow.showAtLocation(
                parent, Gravity.NO_GRAVITY, location[0],
                location[1] - popupWindow.height
            )
        }

        /**
         * 吐司
         *
         * @param activity
         * @param str
         */
        @JvmStatic
        fun showToast(activity: Activity?, str: String?) {
            if (activity != null) {
                activity.runOnUiThread(Runnable { // TODO Auto-generated method stub
                    Toast.makeText(activity, str, Toast.LENGTH_SHORT).show()
                })
            }
        }

        /**
         * 设置
         *
         * @param context
         * @param title
         * @param negativeButtonText
         * @param negativeButtonListener
         * @param positiveButtonText
         * @param positiveButtonListener
         */
        @JvmStatic
        fun showDialog(
            context: Context?, title: String?,
            negativeButtonText: String?, negativeButtonListener: DialogInterface.OnClickListener?,
            positiveButtonText: String?, positiveButtonListener: DialogInterface.OnClickListener?
        ) {
            val builder = AlertDialog.Builder(context)
            builder.setCancelable(false)
            builder.setTitle(title)
                .setNegativeButton(negativeButtonText, negativeButtonListener)
                .setPositiveButton(positiveButtonText, positiveButtonListener)
                .create().show()
        }

        /**
         * 返回当前时间 例： 2016-01-01 12:00:00
         */
        @JvmStatic
        @get:SuppressLint("SimpleDateFormat")
        val nowDateTime: String
            get() {
                var retTime = ""
                val sDateFormat = SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss"
                )
                retTime = sDateFormat.format(Date())
                return retTime
            }

        /**
         * 获取当前日期 例：2016-01-01
         *
         * @return
         */
        @JvmStatic
        val nowDate: String
            get() {
                var retDate = ""
                val nowTime = nowDateTime
                retDate = nowTime.substring(0, 10)
                return retDate
            }

        /**
         * 2016-01-01 格式转化为 06-Apr-2016 格式
         *
         * @param date
         * @return
         */
        @JvmStatic
        @SuppressLint("SimpleDateFormat")
        fun dateToEnFormat(date: String?): String {
            var date = date
            var ret = ""
            if (date == null || date.length != 10 || !date.contains("-")) return ret
            date = date.replace("-".toRegex(), "/")
            val formatter = SimpleDateFormat("yyyy/MM/dd")
            val pos = ParsePosition(0)
            val strtodate = formatter.parse(date, pos)
            val df = SimpleDateFormat(
                "dd-MMM-yyyy",
                Locale.ENGLISH
            )
            ret = df.format(strtodate)
            return ret
        }

        /**
         * 获取当前日期 例：12:00
         *
         * @return
         */
        @JvmStatic
        val nowShortTime: String
            get() {
                var retTime = ""
                val nowTime = nowDateTime
                retTime = nowTime.substring(11, 16)
                return retTime
            }
        @JvmStatic
        fun loadDiaolog(context: Context?, proceStr: String?): AlertDialog {
            /**
             * 加载等待....
             */
            //
            val loadView: View = LayoutInflater.from(context).inflate(
                R.layout.load_dialog, null)
            // 加载dialog,loading...
            val loadDialog = AlertDialog.Builder(context).create()
            val window = loadDialog.window
            window!!.setBackgroundDrawableResource(android.R.color.transparent)
            val text: TextView = loadView.findViewById<TextView>(R.id.load_content)
            text.setText(proceStr)
            loadDialog.setCancelable(true)
            loadDialog.setCanceledOnTouchOutside(false)
            loadDialog.show()
            window.setContentView(loadView)
            // dialog.setContentView(view);
            return loadDialog
        }
        @JvmStatic
        fun cancleDialog(
            activity: Activity,
            loadDialog: AlertDialog?
        ) {
            if (loadDialog != null) {
                activity.runOnUiThread(Runnable { // TODO Auto-generated method stub
                    if (loadDialog.isShowing) {
                        loadDialog.dismiss()
                    }
                })
            }
        }

        private val contentView: RemoteViews? = null
        private const val count = 0
        private const val sum = 0
        private const val len = 0
        private val mSavePath: String? = null
        private const val length = 0
        private val os: OutputStream? = null
        private val inputStream: InputStream? = null
    }
}