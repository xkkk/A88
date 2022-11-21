package com.baorun.handbook.a88.network.request

import com.baorun.handbook.a88.App

data class ClientAddBody(
    var type:String,
    var content:String,
    var name:String,
    var phone:String,
    var email:String,
    var vehicleId:Int,
    var userId:String
){
    constructor(type:String,content: String):this(type,content,"","","",12,App.userId)
}
