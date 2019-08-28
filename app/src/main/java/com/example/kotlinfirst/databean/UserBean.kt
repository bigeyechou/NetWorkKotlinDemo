package com.example.kotlinfirst.databean

data class UserBean (
  val login: String,
  val id: String,
  val avatar_url: String,
  val name: String?,
  val company: String?,
  val blog: String?,
  val location: String?,
  val email: String?,
  val created_at: String,
  val updated_at: String?
  ) {
    companion object {
      val EMPTY = UserBean("", "", "", "", "", "", "", "", "", "")
    }
  }