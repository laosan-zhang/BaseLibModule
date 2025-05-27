package com.zb.baselibrarymodule.Utils

import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Author by zhangbing
 * Time is 2025/5/15 16:57
 * 加密/解密/转码相关工具类
 */
object EncryptionUtils {
    /**
     * 字符串MD5 工具
     * @param string
     * @return [String] md5Str
     */
    @JvmOverloads
    fun md5(string: String?,charset: Charset = Charsets.UTF_8): String {
        if (string.isNullOrBlank()) {
            return ""
        }
        var md5: MessageDigest? = null
        try {
            md5 = MessageDigest.getInstance("MD5")
            val bytes = md5.digest(string.toByteArray(charset))
            var result = ""
            for (b in bytes) {
                var temp = Integer.toHexString(b.toInt() and 0xff)
                if (temp.length == 1) {
                    temp = "0$temp"
                }
                result += temp
            }
            return result
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * base64 字符串转码
     * @param string 待转码字符串
     * @param charset 编码方式
     * @return base64转码结果
     */
    @JvmOverloads
    fun base64Encode(string: String?, charset: Charset = Charsets.UTF_8): String {
        if (string.isNullOrBlank()) {
            return ""
        }
        return Base64.encodeToString(string.toByteArray(charset), Base64.DEFAULT)
    }

    /**
     * base64 字符串解码
     * @param string 待解码字符串
     * @param charset 编码方式
     * @return base64解码结果
     */
    @JvmOverloads
    fun base64Decode(string: String?, charset: Charset = Charsets.UTF_8): String {
        if (string.isNullOrBlank()) return ""
        return String(Base64.decode(string.toByteArray(charset), Base64.DEFAULT))
    }

    /**
     * base64 文件转码
     * @param file 待转码文件
     * @param charset 编码方式
     * @return base64转码结果
     */
    @JvmOverloads
    @RequiresApi(Build.VERSION_CODES.O)
    fun base64Encode(file: File?, charset: Charset = Charsets.UTF_8): String {
        return file?.let {
            if (!it.exists() || !it.isFile) return ""
            val fileBytes = Files.readAllBytes(it.toPath())
            Base64.encodeToString(fileBytes, Base64.DEFAULT)
        } ?: ""
    }

    /**
     * base64 文件解码
     * @param fileContent 待解码文件内容
     * @param filePath 待解码文件路径
     * @param fileName 待解码文件名称
     * @param charset 编码方式
     * @return base64转码结果
     */
    @JvmOverloads
    @RequiresApi(Build.VERSION_CODES.O)
    fun base64Decode(
        fileContent: String?,
        filePath: String?,
        fileName: String?,
        charset: Charset = Charsets.UTF_8
    ) {
        if (fileContent.isNullOrBlank() || filePath.isNullOrBlank() || fileName.isNullOrBlank()) return
        var outputFile = File(filePath)
        if (!outputFile.exists()) outputFile.mkdirs()
        outputFile = File(filePath, fileName)
        if (!outputFile.exists()) outputFile.createNewFile()
        val decodedBytes = Base64.decode(fileContent.toByteArray(charset), Base64.DEFAULT)
        Files.write(outputFile.toPath(), decodedBytes)
    }
}
