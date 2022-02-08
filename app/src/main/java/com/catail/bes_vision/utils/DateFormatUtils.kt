package com.catail.bes_vision.utils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtils {


    /**
     * 英文字符串转日期
     */
    @JvmStatic
    fun En2Date(EnStr: String?): Date? {
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss dd-MMM-yyyy", Locale.ENGLISH)
        simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+08:00")
        val date: Date?
        date = if (EnStr != null) {
            try {
                simpleDateFormat.parse(EnStr)
            } catch (e: ParseException) {
                CN2Date(EnStr)
                //				e.printStackTrace();
            }
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return date
    }

    /**
     * 英文字符串转日期
     */
    @JvmStatic
    fun En2DateNo(EnStr: String?): Date? {
        val simpleDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
        simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+08:00")
        val date: Date?
        date = if (EnStr != null) {
            try {
                simpleDateFormat.parse(EnStr)
            } catch (e: ParseException) {
                CN2Date(EnStr)
                //				e.printStackTrace();
            }
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return date
    }

    /**
     * 中文字符串转日期
     */
    @JvmStatic
    fun CN2Date(CnStr: String?): Date? {
        //2017-06-01 04:15:00
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+08:00")
        var date: Date? = null
        if (CnStr != null) {
            try {
                date = simpleDateFormat.parse(CnStr)
            } catch (e: ParseException) {
//				e.printStackTrace();
            }
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return date
    }

    /**
     * 中文字符串转日期
     */
    @JvmStatic
    fun CN2DateNo(CnStr: String?): Date? {
        //2017-06-01
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+08:00")
        var date: Date? = null
        if (CnStr != null) {
            try {
                date = simpleDateFormat.parse(CnStr)
            } catch (e: ParseException) {
//				e.printStackTrace();
            }
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return date
    }

    @JvmStatic
    fun CN2DateNoDay(CnStr: String?): Date? {
        //2017-06-01
        val simpleDateFormat = SimpleDateFormat("yyyy-MM")
        simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+08:00")
        var date: Date? = null
        if (CnStr != null) {
            try {
                date = simpleDateFormat.parse(CnStr)
            } catch (e: ParseException) {
//				e.printStackTrace();
            }
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return date
    }

    /**
     * 转中文日期
     */
    @JvmStatic
    fun DatetoCNDate(date: Date?): String? {
        val CnStr: String
        //将日期转换为字符串
        if (date != null) {
            val simpleDateFormat2 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            simpleDateFormat2.timeZone = TimeZone.getTimeZone("GMT+08:00")
            CnStr = simpleDateFormat2.format(date)
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return CnStr
    }

    /**
     * 转中文日期
     */
    @JvmStatic
    fun DatetoCNStrNo(date: Date?): String? {
        val CnStr: String
        //将日期转换为字符串
        if (date != null) {
            val simpleDateFormat2 = SimpleDateFormat("yyyy-MM-dd")
            simpleDateFormat2.timeZone = TimeZone.getTimeZone("GMT+08:00")
            CnStr = simpleDateFormat2.format(date)
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return CnStr
    }

    /**
     * 转中文日期
     */
    @JvmStatic
    fun DatetoCNStrNoDay(date: Date?): String? {
        val CnStr: String
        //将日期转换为字符串
        if (date != null) {
            val simpleDateFormat2 = SimpleDateFormat("yyyy-MM")
            simpleDateFormat2.timeZone = TimeZone.getTimeZone("GMT+08:00")
            CnStr = simpleDateFormat2.format(date)
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return CnStr
    }

    /**
     * 英文格式的字符串转中文格式字符串时间
     */
    @JvmStatic
    fun EnStr2CnStr(EnStr: String?): String? {
        val en2Date = En2Date(EnStr)
        return DatetoCNDate(en2Date)
    }
    /*******************************************************************************************/
    /** */
    /**
     * 转英文日期
     */
    @JvmStatic
    fun DatetoEnDateNo(date: Date?): String? {
        val CnStr: String
        //将日期转换为字符串
        if (date != null) {
            val simpleDateFormat2 = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
            simpleDateFormat2.timeZone = TimeZone.getTimeZone("GMT+08:00")
            CnStr = simpleDateFormat2.format(date)
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return CnStr
    }

    /**
     * 转英文日期
     */
    @JvmStatic
    fun DatetoEnDate(date: Date?): String? {
        val CnStr: String
        //将日期转换为字符串
        if (date != null) {
            val simpleDateFormat2 = SimpleDateFormat("dd MMM yyyy HH:mm:ss ", Locale.ENGLISH)
            simpleDateFormat2.timeZone = TimeZone.getTimeZone("GMT+08:00")
            CnStr = simpleDateFormat2.format(date)
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return CnStr
    }

    @JvmStatic
    fun CnStr2EnStr(CnStr: String?): String? {
        val en2Date = En2Date(CnStr)
        return DatetoEnDate(en2Date)
    }

    /**
     * 英文字符串转日期
     */
    @JvmStatic
    fun En2DateNoSS(EnStr: String?): Date? {
        val simpleDateFormat = SimpleDateFormat("HH:mm dd-MMM-yyyy", Locale.ENGLISH)
        simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+08:00")
        val date: Date?
        date = if (EnStr != null) {
            try {
                simpleDateFormat.parse(EnStr)
            } catch (e: ParseException) {
                CN2DateNoSS(EnStr)
                //				e.printStackTrace();
            }
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return date
    }

    /**
     * 中文字符串转日期
     */
    @JvmStatic
    fun CN2DateNoSS(CnStr: String?): Date? {
        //2017-06-01 04:15:00
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+08:00")
        var date: Date? = null
        if (CnStr != null) {
            try {
                date = simpleDateFormat.parse(CnStr)
            } catch (e: ParseException) {
//				e.printStackTrace();
            }
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return date
    }

    /**
     * 转英文日期
     */
    @JvmStatic
    fun DatetoEnDateNoSS(date: Date?): String? {
        val CnStr: String
        //将日期转换为字符串
        if (date != null) {
            val simpleDateFormat2 = SimpleDateFormat("dd MMM yyyy HH:mm ", Locale.ENGLISH)
            simpleDateFormat2.timeZone = TimeZone.getTimeZone("GMT+08:00")
            CnStr = simpleDateFormat2.format(date)
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return CnStr
    }

    @JvmStatic
    fun CnStr2EnStrNoSS(CnStr: String?): String? {
        val en2Date = En2DateNoSS(CnStr)
        return DatetoEnDateNoSS(en2Date)
    }
}