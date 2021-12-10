package com.baorun.handbook.a6v.network

data class BaseResponse<T>(val result:Boolean,val listCount:Int,val listData:List<T>)
