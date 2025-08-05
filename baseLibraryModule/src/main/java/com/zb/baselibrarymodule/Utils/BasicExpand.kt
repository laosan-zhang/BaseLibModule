package com.zb.baselibrarymodule.Utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.File
import java.nio.charset.Charset
import java.util.regex.Pattern

/**
 *  author : 86175
 *  time   : 2025/5/15 17:15
 *  com.zb.baselibrarymodule.Utils
 *  基本数据类型扩展
 */
//IP地址正则
const val ipRegex =
    "^((http|https)://)((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(:([0-9]|[1-9]\\d|[1-9]\\d{2}|[1-9]\\d{3}|[1-5]\\d{4}|6[0-4]\\d{2}|655[0-2]\\d|6553[0-5])$)"

//手机号正则
const val phoneRegex = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$"

//身份证号码正则
const val idCardRegex =
    "^[1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}[\\dXx]$"

//邮箱正则
const val EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"

//密码正则。4~32位的字母、数字、下划线
const val PASSWORD_REGEX = "^\\w{4,32}$"

//验证码正则
const val VERIFY_CODE_REGEX = "^[a-zA-Z\\d]{6}$"

/**
 * 判断当前字符串是否时合法IP地址
 */
fun String?.isIpLegal(): Boolean {
    return this?.let { content ->
        val pattern =
            Pattern.compile(ipRegex)
        val matcher = pattern.matcher(content)
        matcher.find()
    } ?: false
}

/**
 * 判断当前字符串是否是手机号码
 */
fun String?.isPhone(): Boolean {
    return this?.let { content ->
        val pattern = Pattern.compile(phoneRegex)
        val matcher = pattern.matcher(content)
        matcher.find()
    } ?: false
}

/**
 * 判断当前字符串是否是身份证
 */
fun String?.isIdCard(): Boolean {
    return this?.let { content ->
        val pattern = Pattern.compile(idCardRegex)
        val matcher = pattern.matcher(content)
        matcher.find()
    } ?: false
}


/**
 * 判断当前字符串是否符合某种格式
 * @param [String] regexStr 正则表达式
 * @return [Boolean] true:匹配  false:不匹配
 */
fun String?.match(regexStr: String): Boolean {
    return this?.let { content ->
        val pattern =
            Pattern.compile(regexStr)
        val matcher = pattern.matcher(content)
        matcher.find()
    } ?: false
}

/**
 * base64 字符串转码
 * @param charset 编码方式
 * @return base64转码结果
 */
fun String?.toBase64(charset: Charset = Charsets.UTF_8): String {
    return EncryptionUtils.base64Encode(this, charset)
}

/**
 * base64 字符串解码
 * @param charset 编码方式
 * @return base64解码结果
 */
fun String?.fromBase64(charset: Charset = Charsets.UTF_8): String {
    return EncryptionUtils.base64Decode(this, charset)
}

/**
 * base64 文件转码
 * @param file 待转码文件
 * @param charset 编码方式
 * @return base64转码结果
 */
@RequiresApi(Build.VERSION_CODES.O)
fun File?.toBase64(charset: Charset = Charsets.UTF_8): String {
    return EncryptionUtils.base64Encode(this, charset)
}

/**
 * base64 文件解码
 * @param filePath 待解码文件路径
 * @param fileName 待解码文件名称
 * @param charset 编码方式
 * @return base64转码结果
 */
@RequiresApi(Build.VERSION_CODES.O)
fun String?.fromBase64(
    filePath: String?,
    fileName: String?,
    charset: Charset = Charsets.UTF_8
) {
    EncryptionUtils.base64Decode(this, filePath, fileName, charset)
}

/**
 * 字符串md5加密
 * @param charset 编码方式
 * @return md5解密结果
 */
fun String?.toMD5(charset: Charset = Charsets.UTF_8): String {
    return EncryptionUtils.md5(this)
}

/**
 * 集合排序工具
 * @param comparator 排序规则，默认按照toString 字符串排序
 */
fun <T> List<T>.sort(
    comparator: Comparator<T> = Comparator { a, b ->
        a.toString().compareTo(b.toString())
    }
): List<T> {
    return this.sortedWith(comparator)
}

