package com.catail.bes_vision.home.bean

/**
 * Created by D on 2018/4/13.
 */
class LoginResultBean {
    var errno = 0
    var errstr: String? = null
    var errstr_cn: String? = null
    var result: ResultBean? = null

    class ResultBean {
        var uid: String? = null
        var token: String? = null
        override fun toString(): String {
            return "ResultBean{" +
                    "uid='" + uid + '\'' +
                    ", token='" + token + '\'' +
                    '}'
        }
    }

    override fun toString(): String {
        return "LoginResultBean{" +
                "errno=" + errno +
                ", errstr='" + errstr + '\'' +
                ", errstr_cn='" + errstr_cn + '\'' +
                ", result=" + result +
                '}'
    }
}