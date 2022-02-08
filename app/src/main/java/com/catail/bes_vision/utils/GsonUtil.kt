package com.catail.bes_vision.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.google.gson.JsonParser
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*

object GsonUtil {
    private var gson: Gson? = null

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    @JvmStatic
    fun GsonString(`object`: Any?): String? {
        var json: String? = null
        if (gson != null) {
            json = gson!!.toJson(`object`)
        }
        return json
    }

    /**
     * 转成bean
     *
     * @param cls
     * @return
     */
    @JvmStatic
    fun <T> GsonToBean(json: String?, cls: Class<T>?): T? {
        var t: T? = null
        if (gson != null) {
            t = try {
                gson!!.fromJson(json, cls)
            } catch (e: JsonSyntaxException) {
                //  e.printStackTrace();
                Log.d("gsonString", e.toString())
                return null
            }
        }
        return t
    }

    /**
     * 转成list
     * 泛型在编译期类型被擦除导致报错
     *
     * @param cls
     * @return
     */
    @JvmStatic
    fun <T> GsonToList(json: String?, cls: Class<T>?): List<T>? {
        var list: List<T>? = null
        if (gson != null) {
            list = gson!!.fromJson(json, object : TypeToken<List<T>?>() {}.type)
        }
        return list
    }

    /**
     * 把一个json的字符串转换成为一个包含POJO对象的List
     *
     * @param string
     * @param cls
     * @param <T>
     * @return
    </T> */
    @JvmStatic
    fun <T> jsonStringConvertToList(string: String?, cls: Class<Array<T>?>?): List<T> {
        val gson = Gson()
        val array = gson.fromJson(string, cls)!!
        return Arrays.asList(*array)
    }

    /**
     * 转成list
     * 解决泛型问题
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
    </T> */
    @JvmStatic
    fun <T> jsonToList(json: String?, cls: Class<T>?): List<T> {
        val gson = Gson()
        val list: MutableList<T> = ArrayList()
        val array = JsonParser().parse(json).asJsonArray
        for (elem in array) {
            list.add(gson.fromJson(elem, cls))
        }
        return list
    }

    //    public static <T> String A_H_jsonStringConvertToList(String json) {
    //        Type listType = new TypeToken<List<A_H_G_TypeEditBean>>() {}.getType();
    //        List<A_H_G_TypeEditBean> list= JsonUtil.fromJson(json, listType);
    //
    //        Logger.e("list.toString()==",list.toString());
    //
    //        String jsonResult=JsonUtil.toJson("");
    //        Logger.e("jsonResult==",jsonResult);
    //        return jsonResult;
    //    }
    @JvmStatic
    fun <T> parseString2List(
        json: String?,
        clazz: Class<*>
    ): List<T> {
        val type: Type = ParameterizedTypeImpl(clazz)
        return Gson().fromJson(json, type)
    }

    /**
     * 转成list中有map的
     *
     * @return
     */
    @JvmStatic
    fun <T> GsonToListMaps(json: String?): List<Map<String, T>>? {
        var list: List<Map<String, T>>? = null
        if (gson != null) {
            list = gson!!.fromJson(
                json,
                object : TypeToken<List<Map<String?, T>?>?>() {}.type
            )
        }
        return list
    }

    /**
     * 转成map的
     *
     * @return
     */
    @JvmStatic
    fun <T> GsonToMaps(json: String?): Map<String, T>? {
        var map: Map<String, T>? = null
        if (gson != null) {
            map = gson!!.fromJson(json, object : TypeToken<Map<String?, T>?>() {}.type)
        }
        return map
    }

    class ParameterizedTypeImpl(var clazz: Class<*>) : ParameterizedType {
        override fun getActualTypeArguments(): Array<Type> {
            return arrayOf(clazz)
        }

        override fun getRawType(): Type {
            return MutableList::class.java
        }

        override fun getOwnerType(): Type? {
            return null
        }
    }

    init {
        if (gson == null) {
            gson = Gson()
        }
    }
}