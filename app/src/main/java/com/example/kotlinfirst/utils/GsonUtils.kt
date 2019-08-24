package com.example.kotlinfirst.utils

import com.google.gson.Gson

/**
 * 类描述：项目的util工具 gson解析
 */
object GsonUtils {
  var gson: Gson? = Gson()
  /**
   * 说明：如果解析抛异常返回null
   * @param result 要解析的json字符串
   * @param clazz 对应的javabean的字节码
   * @return 返回 对应的javabean 对象
   */
  fun <T> fromJson(result: String, clazz: Class<T>): T? {
    try {
      if (gson == null) {
        gson = Gson()
      }
      return gson!!.fromJson(result, clazz)
    } catch (e: Exception) {

      return null
    }

  }

  fun toJson(obj: Any): String {
    if (null == gson) {
      gson = Gson()
    }
    return gson!!.toJson(obj)
  }
}
