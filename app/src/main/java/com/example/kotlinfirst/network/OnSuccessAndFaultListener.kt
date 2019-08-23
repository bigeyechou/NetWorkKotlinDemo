package com.example.kotlinfirst.network

interface OnSuccessAndFaultListener {

  fun onSuccess(result:String)

  fun onFault(errorMsg: String)
}