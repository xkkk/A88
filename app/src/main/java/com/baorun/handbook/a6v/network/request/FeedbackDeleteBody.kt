package com.baorun.handbook.a6v.network.request

import com.baorun.handbook.a6v.App

data class FeedbackDeleteBody(val id:Int,val userId:String=App.userId)