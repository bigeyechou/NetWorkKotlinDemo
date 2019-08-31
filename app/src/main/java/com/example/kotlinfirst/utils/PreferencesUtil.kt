package com.example.kotlinfirst.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.kotlinfirst.app.App
import java.util.logging.Logger

object PreferencesUtil {
  private val TAG = "SharedPreferences"
  private val name = "APP_Config"
  private val prefs: SharedPreferences by lazy { App.instance.applicationContext.getSharedPreferences(name, Context.MODE_PRIVATE) }

  public val USER_ID : String="user_id";
  public val USER_NAME : String="user_name";
  public val USER_AVATAR : String="user_avatar";
  public val USER_BLOG : String="user_blog";

  /**
   * 获取存放数据
   * @return 值
   */
  @Suppress("UNCHECKED_CAST")
  fun getValue(key: String, default: Any): Any = with(prefs) {
    return when (default) {
      is Int -> getInt(key, default)
      is String -> getString(key, default)
      is Long -> getLong(key, default)
      is Float -> getFloat(key, default)
      is Boolean -> getBoolean(key, default)
      else -> throw IllegalArgumentException("SharedPreferences 类型错误")
    }
  }

  fun getString(key: String, default: String = ""): String {
    return getValue(key, default) as String
  }

  fun getInt(key: String, default: Int = 0): Int {
    return getValue(key, default) as Int
  }

  fun getLong(key: String, default: Long = 0): Long {
    return getValue(key, default) as Long
  }

  fun getBoolean(key: String, default: Boolean = false): Boolean {
    return getValue(key, default) as Boolean
  }

  fun getFloat(key: String, default: Float = 0f): Float {
    return getValue(key, default) as Float
  }

  /**
   * 存放SharedPreferences
   * @param key 键
   * @param value 值
   */
  fun saveValue(key: String, value: Any?) = with(prefs.edit()) {
    when (value) {
      is Long -> putLong(key, value)
      is Int -> putInt(key, value)
      is String -> putString(key, value)
      is Float -> putFloat(key, value)
      is Boolean -> putBoolean(key, value)
      else -> throw IllegalArgumentException("SharedPreferences 类型错误")
    }.apply()
  }

  /**
   * 清除
   */
  fun clear() {
    prefs.edit().clear().apply()
  }

  /**
   * 删除某Key的值
   */
  fun remove(key: String) {
    prefs.edit().remove(key).apply()
  }
}