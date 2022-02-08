package com.catail.bes_vision.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Process
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.catail.bes_vision.R
import java.io.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    /**
     * 对象转字符串
     *
     * @param object
     * @return
     * @throws IOException
     */
    @JvmStatic
    @Throws(IOException::class)
    fun objectToString(`object`: Any?): String? {
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
    fun stringToObject(objectVal: String?): Any? {
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
     * 得到自定义的progressDialog
     *
     * @param context
     * @param msg
     * @return
     */
    @JvmStatic
    fun createLoadingDialog(context: Activity, msg: String?): Dialog? {
        var context = context
        return try {
            while (context.parent != null) {
                context = context.parent
            }
            val inflater = LayoutInflater.from(context)
            val v: View = inflater.inflate(R.layout.loading_dialog, null) // 得到加载view
            val layout = v.findViewById<View>(R.id.dialog_view) as LinearLayout // 加载布局
            // main.xml中的ImageView
            val spaceshipImage = v.findViewById<View>(R.id.img) as ImageView
            val tipTextView = v.findViewById<View>(R.id.tipTextView) as TextView // 提示文字
            // 加载动画
            val hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                context, R.anim.load_animation
            )
            // 使用ImageView显示动画
            spaceshipImage.startAnimation(hyperspaceJumpAnimation)
            tipTextView.text = msg // 设置加载信息
            val loadingDialog = Dialog(context, R.style.loading_dialog) // 创建自定义样式dialog

            //        loadingDialog.setCancelable(false);// 不可以用“返回键”取消
            loadingDialog.setCanceledOnTouchOutside(false) //点击对话框外不可以取消
            loadingDialog.setContentView(
                layout, LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
            ) // 设置布局
            loadingDialog
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    var loadingDialog: Dialog? = null

    /**
     * 在某些没有baseActivity使用progressDialog出现方法
     */
    @JvmStatic
    fun showProgressDialog(activity: Activity?, msg: String?) {
//        loadingDialog=null;
        if (loadingDialog == null) {
            loadingDialog = activity?.let { Utils.createLoadingDialog(it, msg) }
            if (!loadingDialog!!.isShowing) {
                loadingDialog!!.show()
            }
        } else {
        }
    }

    /**
     * 在某些没有baseActivity使用progressDialog出现方法
     */
    fun dismissProgressDialog() {
        if (loadingDialog != null) {
            loadingDialog!!.dismiss()
            loadingDialog = null
        }
    }


    /**
     * 杀死当前应用的进程
     */
    @JvmStatic
    fun killProcess() {
        val pid = Process.myPid()
        Process.killProcess(pid)
    }

    /**
     * 进行添加水印图片和文字
     *
     * @param src
     * @param waterMak
     * @return
     */
    @JvmStatic
    fun createBitmap(src: Bitmap?, waterMak: Bitmap?, title: String?): Bitmap? {
        if (src == null) {
            return src
        }
        // 获取原始图片与水印图片的宽与高
        val width = src.width
        val height = src.height
        val newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val mCanvas = Canvas(newBitmap)
        // 往位图中开始画入src原始图片
        mCanvas.drawBitmap(src, 0f, 0f, null)
        if (null != waterMak) {
            val ww = waterMak.width
            val wh = waterMak.height
            // 在src的右下角添加水印
            val paint = Paint()
            // paint.setAlpha(100);
            mCanvas.drawBitmap(
                waterMak,
                (width - ww - 5).toFloat(),
                (height - wh - 5).toFloat(),
                paint
            )
        }
        // 开始加入文字
        if (null != title) {
            val textPaint = Paint()
            // 设置字体颜色
            textPaint.color = Color.WHITE
            // 设置字体大小
            textPaint.textSize = 40f
            // 阴影设置
            textPaint.setShadowLayer(3f, 1f, 1f, Color.DKGRAY)
            val familyName = "宋体"
            val typeface = Typeface.create(familyName, Typeface.BOLD_ITALIC)
            textPaint.typeface = typeface
            textPaint.textAlign = Paint.Align.LEFT
            val textWidth = textPaint.measureText(title)
            // 文字 添加位置
            mCanvas.drawText(title, width - textWidth - 10, (height - 26).toFloat(), textPaint)
        }
        //        mCanvas.save(Canvas.ALL_SAVE_FLAG);
        mCanvas.save()
        // 保存
        mCanvas.restore()
        return newBitmap
    }

    /**
     * 删除原图保存水印照片方法
     */
    @JvmStatic
    fun saveBitmap(captureFileUri: Uri, bm: Bitmap) {
        val f = File(captureFileUri.path)
        if (f.exists()) {
            f.delete()
        }
        try {
            val out = FileOutputStream(f)
            // 压缩图片 80 是压缩率，表示压缩20%; 如果不压缩是100，
            bm.compress(Bitmap.CompressFormat.JPEG, 40, out)
            out.flush()
            out.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 删除原图保存水印照片方法
     */
    @JvmStatic
    fun saveStringBitmap(captureFileUri: String, bm: Bitmap) {
        try {
            val f = File(captureFileUri)
            Logger.e("captureFileUri.getPath()=$captureFileUri")
            if (f.exists()) {
                f.delete()
                Logger.e("f.delete()")
            } else {
                Logger.e("f.delete()==no")
            }
            val out = FileOutputStream(f)
            // 压缩图片 80 是压缩率，表示压缩20%; 如果不压缩是100，
            bm.compress(Bitmap.CompressFormat.JPEG, 40, out)
            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    //删除文件夹

    @JvmStatic
    fun delFolder(folderPath: String) {
        try {
            delAllFile(folderPath) //删除完里面所有内容
            var filePath = folderPath
            filePath = filePath
            val myFilePath = File(filePath)
            myFilePath.delete() //删除空文件夹
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //删除指定文件夹下的所有文件

    @JvmStatic
    fun delAllFile(path: String): Boolean {
        var flag = false
        val file = File(path)
        if (!file.exists()) {
            return flag
        }
        if (!file.isDirectory) {
            return flag
        }
        val tempList = file.list()
        var temp: File? = null
        for (i in tempList.indices) {
            temp = if (path.endsWith(File.separator)) {
                File(path + tempList[i])
            } else {
                File(path + File.separator + tempList[i])
            }
            if (temp.isFile) {
                temp.delete()
            }
            if (temp.isDirectory) {
                delAllFile(path + "/" + tempList[i]) //先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]) //再删除空文件夹
                flag = true
            }
        }
        return flag
    }


    /**
     * 专为Android4.4设计的从Uri获取文件绝对路径，以前的方法已不好使
     */

    @JvmStatic
    @SuppressLint("NewApi")
    fun getPath(context: Context, uri: Uri): String? {
        val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }
            } else if (isDownloadsDocument(uri)) {
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)
                )
                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])
                return getDataColumn(context, contentUri, selection, selectionArgs)
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
            return getDataColumn(context, uri, null, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
        }
        return null
    }


    @JvmStatic
    fun getDataColumn(
        context: Context, uri: Uri?, selection: String?,
        selectionArgs: Array<String>?
    ): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)
        try {
            cursor = context.contentResolver.query(
                uri!!, projection, selection, selectionArgs,
                null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val column_index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(column_index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */

    @JvmStatic
    fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */

    @JvmStatic
    fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */

    @JvmStatic
    fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }


    @JvmStatic
    fun OriginalUrlToThumbUrl(OriginalUrl: String): String? {
        var thambulr = ""
        val imgStr = OriginalUrl.split("/").toTypedArray()
        for (i in imgStr.indices) {
            if (i == imgStr.size - 1) {
                thambulr = thambulr + Config.ThambStr + imgStr[i]
            } else {
                thambulr += imgStr[i] + "/"
            }
        }
        Logger.e("thamburl==", thambulr)
        return thambulr
    }

    @JvmStatic
    fun Keep1Decimal(num1: Double): Double {
        val b = BigDecimal(num1)
        //保留2位小数
        return b.setScale(1, BigDecimal.ROUND_HALF_UP).toDouble()
    }

    @JvmStatic
    fun getCurrentCNDate(): String? {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(calendar.time)
    }

    @JvmStatic
    fun getCurrentDate(): String? {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val dateStr = sdf.format(calendar.time)
        val date: Date? = DateFormatUtils.CN2DateNo(dateStr)
        return DateFormatUtils.DatetoEnDateNo(date)
    }

    @JvmStatic
    fun getCurrentYearMonth(): String? {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM")
        val dateStr = sdf.format(calendar.time)
        val date: Date? = DateFormatUtils.CN2DateNoDay(dateStr)
        return DateFormatUtils.DatetoCNStrNoDay(date)
    }


    @JvmStatic
    fun getCurrentDateAndTimeNoss(): String? {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val dateStr = sdf.format(calendar.time)
        val date: Date? = DateFormatUtils.CN2DateNoSS(dateStr)
        return DateFormatUtils.DatetoEnDateNoSS(date)
    }


    @JvmStatic
    fun setAlertDialogSize(activity: AppCompatActivity, dialog: AlertDialog, size: Double) {
        val width = activity.windowManager.defaultDisplay.width
        val height = activity.windowManager.defaultDisplay.height
        if (size != 0.0) {
            dialog.window!!.setLayout((width * size).toInt(), ActionBar.LayoutParams.WRAP_CONTENT)
        } else {
            dialog.window!!.setLayout((width * 0.88).toInt(), ActionBar.LayoutParams.WRAP_CONTENT)
        }
    }

    @JvmStatic
    fun setAlertDialogConner(dialog: AlertDialog) {
        dialog.window!!.setBackgroundDrawable(null)
    }

}