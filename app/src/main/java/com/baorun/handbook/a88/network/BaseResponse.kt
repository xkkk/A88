package com.baorun.handbook.a88.network

data class BaseResponse<T>(val result:Boolean,val listCount:Int,val listData:List<T>)
