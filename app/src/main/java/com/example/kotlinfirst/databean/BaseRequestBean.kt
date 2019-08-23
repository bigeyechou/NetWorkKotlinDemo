package com.example.kotlinfirst.databean

class BaseRequestBean<T> {
  private var version = "v1"
  private var obj: T? = null

  fun getVersion(): String =version

  fun setVersion(version: String) {
    this.version = version
  }

  fun getObj(): T? =obj

  fun setObj(obj: T) {
    this.obj = obj
  }
}