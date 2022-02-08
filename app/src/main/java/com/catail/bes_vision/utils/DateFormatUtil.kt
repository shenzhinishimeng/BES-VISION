package com.catail.bes_vision.utils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtil {

    /**
     * 将2016-12-22 时间转换为英文格式时间
     *
     * @param str
     * @return
     */
    @JvmStatic
    fun FormatDate(str: String?): String? {
        var EnStr: String? = null
        var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+08:00")
        try {
            val parse = simpleDateFormat.parse(str)
            simpleDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
            EnStr = simpleDateFormat.format(parse)
        } catch (e1: ParseException) {
//			e1.printStackTrace();
        }
        return EnStr
    }

    /**
     * 将 06-DEc-2016类型的时间转换为 06 Dec 2016
     *
     * @param str
     * @return
     */
    @JvmStatic
    fun FormatDateEN(str: String?): String? {
        var EnStr: String? = null
        var simpleDateFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
        simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+08:00")
        try {
            val parse = simpleDateFormat.parse(str)
            simpleDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
            EnStr = simpleDateFormat.format(parse)
        } catch (e1: ParseException) {
//			e1.printStackTrace();
        }
        return EnStr
    }

    /********************************************************************************/
    /** */
    /**
     * 将字符串日期转换为时间日期
     */
    @JvmStatic
    fun StrToDate(str: String?): Date? {
        var date: Date? = null
        if (str != null) {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            sdf.timeZone = TimeZone.getTimeZone("GMT+08:00")
            try {
                date = sdf.parse(str)
            } catch (e: ParseException) {
//				e.printStackTrace();
            }
        } else {
            Log.e("NullExcepton", "时间为空")
        }
        return date
    }

    /**
     * 将字符串日期转换为时间日期
     */
    @JvmStatic
    fun StrToDateNO(str: String?): Date? {
        var date: Date? = null
        if (str != null) {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            sdf.timeZone = TimeZone.getTimeZone("GMT+08:00")
            try {
                date = sdf.parse(str)
                //				Log.e("AAA", date.toString());
            } catch (e: ParseException) {
                // TODO Auto-generated catch block
//				e.printStackTrace();
            }
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return date
    }

    /**
     * 将时间日期转换为英文日期 yyyy-MM-dd HH:mm:ss
     */
    @JvmStatic
    fun CNDateStrTOENDate(CNDate: Date?): String? {
        var ENDate: String? = null
        if (CNDate != null) {
            val df = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.ENGLISH)
            df.timeZone = TimeZone.getTimeZone("GMT+08:00")
            ENDate = df.format(CNDate)
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return ENDate
    }

    /**
     * 将时间日期转换为英文日期 dd MMM yyyy
     */
    @JvmStatic
    fun CNDateStrTOENDateNO(CNDate: Date?): String? {
        val ENDate: String
        if (CNDate != null) {
//			Log.e("CNDate", CNDate.toString());
            val df = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
            df.timeZone = TimeZone.getTimeZone("GMT+08:00")
            ENDate = df.format(CNDate)
            //			Log.e("ENDate", ENDate.toString());
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return ENDate
    }

    /**
     * 中文时间转换为英文时间 (没有HH:mm:ss)
     *
     * @param CNStr
     * @return
     */
    @JvmStatic
    fun CNStr2ENStrNoTime(CNStr: String?): String? {
        val ENStr: String?
        ENStr = if (CNStr != null) {
            val strToDateNO = StrToDateNO(CNStr)
            CNDateStrTOENDateNO(strToDateNO)
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return ENStr
    }

    /**
     * 中文时间转换为英文时间
     *
     * @param CNStr
     * @return
     */
    @JvmStatic
    fun CNStr2ENStr(CNStr: String?): String? {
        val ENStr: String?
        ENStr = if (CNStr != null) {
            val strToDate = StrToDate(CNStr)
            CNDateStrTOENDate(strToDate)
        } else {
            Log.e("NullExcepton", "时间为空")
            return null
        }
        return ENStr
    }
    /**************************************************************************************/
    /** */
    /**
     * 英文时间转日期
     *
     * @param ENStr
     * @return
     */
    @JvmStatic
    fun ENStr2Date(ENStr: String?): Date? {
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss dd MMM yyyy", Locale.ENGLISH)
        simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+08:00")
        var date2: Date? = null
        try {
            date2 = simpleDateFormat.parse(ENStr)
        } catch (e: ParseException) {
            // TODO Auto-generated catch block
//			e.printStackTrace();
        }
        return date2
    }

    /**
     * 日期转中文时间
     *
     * @param date
     * @return
     */
    @JvmStatic
    fun Date2CNStr(date: Date?): String {
        //将日期转换为字符串
        val simpleDateFormat2 =
            SimpleDateFormat("yyyy MM dd HH:mm:ss")
        simpleDateFormat2.timeZone = TimeZone.getTimeZone("GMT+08:00")
        return simpleDateFormat2.format(date)
    }


    /**
     * 日期转中文时间
     *
     * @param date
     * @return
     */
    @JvmStatic
    fun Date2CNStrNoDay(date: Date?): String? {
        //将日期转换为字符串
        val simpleDateFormat2 = SimpleDateFormat("yyyy MM")
        simpleDateFormat2.timeZone = TimeZone.getTimeZone("GMT+08:00")
        return simpleDateFormat2.format(date)
    }

    @JvmStatic
    fun ENStr2CNStr(ENStr: String?): String? {
        val CNStr: String? = null
        if (ENStr != null) {
            val enStr2Date = ENStr2Date(ENStr)
            val date2cnStr = Date2CNStr(enStr2Date)
        } else {
            Log.e("NullExcepton", "时间为空")
        }
        return CNStr
    }
}