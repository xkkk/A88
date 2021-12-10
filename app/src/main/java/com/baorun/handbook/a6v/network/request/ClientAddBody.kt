package com.baorun.handbook.a6v.network.request

data class ClientAddBody(
    var type:String,
    var content:String,
    var name:String,
    var phone:String,
    var email:String,
    var vehicleId:Int,
    var userId:String
){
    constructor(type:String,content: String,deviceId:String):this(type,content,"","","",6,deviceId)
}
