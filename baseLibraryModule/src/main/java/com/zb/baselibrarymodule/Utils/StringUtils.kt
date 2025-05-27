package com.zb.baselibrarymodule.Utils

/**
 *  author : 86175
 *  time   : 2025/5/15 16:53
 *  com.zb.baselibrarymodule.Utils
 *  字符串类工具类
 */

class StringUtils {
    companion object {
        /**
         * 手机号码检测
         */
        @JvmStatic
        fun isPhone(phone: String?): Boolean {
            return phone.isPhone()
        }

        /**
         * 判断当前地址是否是IP地址
         */
        @JvmStatic
        fun isIpLegal(ipAddress: String?) = run {
            ipAddress.isIpLegal()
        }

        /**
         * URL移除 http或https头部信息
         */
        @JvmStatic
        fun formatUrl(source: String?): String? {
            source
                ?.replace("http://", "")
                ?.replace("https://", "")
                ?.apply {
                    if (isEmpty()) {
                        return source
                    }
                    val i = indexOf('/')
                    if (i >= 0)
                        return substring(i)
                } ?: return source
            return source
        }

        /**
         * 手机号码脱敏
         */
        @JvmStatic
        fun resetPhone(phone: String?): String {
            return if (!isPhone(phone)) "" else {
                phone!!.let {
                    val str1 = it.substring(0, 3)
                    val str2 = it.substring(it.length - 4)
                    "$str1****$str2"
                }
            }
        }

        /**
         * 身份证号码脱敏
         */
        @JvmStatic
        fun resetIdCard(idcard: String?): String {
            return if (!idcard.match(idCardRegex)) "" else {
                idcard!!.let {
                    it.replaceRange(3..15,"*")
                }
            }
        }

        /**
         * 根据时间戳，获取分钟、秒等信息
         */
        @JvmStatic
        fun getTime(long: Long): String {
            return if (long == 0L) "00:00" else {
                if (long <= 59L) {
                    if (long > 9) {
                        "00:$long"
                    } else {
                        "00:0$long"
                    }
                } else {
                    var time = ""
                    var min = long / 60
                    var sec = long % 60
                    time += if (min > 9) {
                        "$min"
                    } else {
                        "0$min"
                    }
                    time += ":"
                    time += if (sec > 9) {
                        "$sec"
                    } else {
                        "0$sec"
                    }
                    time
                }
            }
        }

        /**
         * 数字前面自动拼接0
         */
        fun formatNumber(number: Int): String {
            return if (number > 9) number.toString() else "0${number}"
        }

        /**
         * 去除字符串中的 空白字符、制表符、换页符
         */
        @JvmStatic
        fun formatStr(str: String?): String {
            if (EmptyCheckUtils.isEmpty(str)) {
                return ""
            }
            return str!!.replace("\\s*", "")
        }
    }
}

