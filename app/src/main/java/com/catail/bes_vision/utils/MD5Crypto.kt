package com.catail.bes_vision.utils

import android.util.Log
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object MD5Crypto {
    val LOG_TAG = MD5Crypto::class.java.simpleName

    // MD5计算
    @JvmStatic
    @Throws(Exception::class)
    fun encryptForByte(src: ByteArray?): ByteArray {
        val mD = MessageDigest.getInstance("MD5")
        val re = mD.digest(src)

        // 打印MD5结果
        val sb = StringBuffer()
        for (b in re) {
            sb.append(String.format("%X", b))
        }
        Log.d(LOG_TAG, "MD5处理结果＿$sb")
        Log.d(LOG_TAG, "length: " + re.size)
        return re
    }

    @JvmStatic
    fun md5(str: String?): String? {
        if (str == null) {
            return null
        }
        var messageDigest: MessageDigest? = null
        try {
            messageDigest = MessageDigest.getInstance("MD5")
            messageDigest.reset()
            messageDigest.update(str.toByteArray(StandardCharsets.UTF_8))
        } catch (e: NoSuchAlgorithmException) {
            return str
        }
        val byteArray = messageDigest.digest()
        val md5StrBuff = StringBuffer()
        for (i in byteArray.indices) {
            if (Integer.toHexString(0xFF and byteArray[i].toInt()).length == 1) md5StrBuff.append("0")
                .append(
                    Integer.toHexString(0xFF and byteArray[i].toInt())
                ) else md5StrBuff.append(
                Integer.toHexString(
                    0xFF and byteArray[i]
                        .toInt()
                )
            )
        }
        return md5StrBuff.toString()
    }
}