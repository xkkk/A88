package com.baorun.handbook.a88.network.request

import com.baorun.handbook.a88.App

data class FeedbackDeleteBody(val id:Int,val userId:String=App.userId)