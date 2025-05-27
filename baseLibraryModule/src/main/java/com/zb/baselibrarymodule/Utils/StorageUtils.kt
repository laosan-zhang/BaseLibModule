package com.zb.baselibrarymodule.Utils

import com.tencent.mmkv.MMKV
/**
 *  author : 86175
 *  time   : 2025/5/23 10:56
 *  com.zb.baselibrarymodule.Utils
 * 基于 MMKV 的高性能存储工具类
 * 支持：多进程、加密存储、类型安全操作
 */
object StorageUtils {

    // 默认实例（单进程）
    val defaultKV: MMKV by lazy {
        MMKV.defaultMMKV()
    }

    // 自定义实例（支持多进程和加密）
    fun getKV(storageId: String = "default", multiProcess: Boolean = false, cryptKey: String? = null): MMKV {
        val mode = if (multiProcess) MMKV.MULTI_PROCESS_MODE else MMKV.SINGLE_PROCESS_MODE
        return if (cryptKey != null) {
            MMKV.mmkvWithID(storageId, mode, cryptKey)
        } else {
            MMKV.mmkvWithID(storageId, mode)
        }
    }

    /* ------------------- 基础操作 ------------------- */
    fun put(key: String, value: Any?) {
        when (value) {
            is Int -> defaultKV.encode(key, value)
            is Long -> defaultKV.encode(key, value)
            is Float -> defaultKV.encode(key, value)
            is Double -> defaultKV.encode(key, value.toFloat()) // MMKV 不直接支持 Double
            is Boolean -> defaultKV.encode(key, value)
            is String -> defaultKV.encode(key, value)
            is ByteArray -> defaultKV.encode(key, value)
            null -> defaultKV.remove(key)
            else -> throw IllegalArgumentException("不支持的类型: ${value.javaClass.name}")
        }
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T> get(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is Int -> defaultKV.decodeInt(key, defaultValue) as T
            is Long -> defaultKV.decodeLong(key, defaultValue) as T
            is Float -> defaultKV.decodeFloat(key, defaultValue) as T
            is Double -> defaultKV.decodeFloat(key, defaultValue.toFloat()).toDouble() as T
            is Boolean -> defaultKV.decodeBool(key, defaultValue) as T
            is String -> defaultKV.decodeString(key, defaultValue) as T
            is ByteArray -> defaultKV.decodeBytes(key, defaultValue) as T
            else -> {
                throw IllegalArgumentException("不支持的类型: ${T::class.java.name}")
            }
        }
    }

    fun remove(key: String) {
        defaultKV.remove(key)
    }

    fun clearAll() {
        defaultKV.clearAll()
    }

    /* ------------------- 扩展函数（Kotlin 友好） ------------------- */
    inline fun <reified T> get(key: String): T? {
        return when (T::class) {
            Int::class -> defaultKV.decodeInt(key, 0) as? T
            Long::class -> defaultKV.decodeLong(key, 0L) as? T
            Float::class -> defaultKV.decodeFloat(key, 0f) as? T
            Double::class -> defaultKV.decodeFloat(key, 0f).toDouble() as? T
            Boolean::class -> defaultKV.decodeBool(key, false) as? T
            String::class -> defaultKV.decodeString(key) as? T
            ByteArray::class -> defaultKV.decodeBytes(key) as? T
            else -> null
        }
    }

    /* ------------------- 高级功能 ------------------- */
    /**
     * 迁移 SharedPreferences 数据
     */
    fun migrateFromSharedPreferences(sharedPrefsName: String) {
        val sharedPrefs = MMKV.mmkvWithID(sharedPrefsName) ?: return
        defaultKV.importFromSharedPreferences(sharedPrefs)
        sharedPrefs.clearAll()
    }

    /**
     * 监听数据变化
     */
    fun addOnValueChangedListener(listener: (key: String) -> Unit) {
        defaultKV.registerOnSharedPreferenceChangeListener { _, key ->
            key?.let { listener(it) }
        }
    }
}