package com.baorun.handbook.a6v.data


const val ORIGIN_WIDTH = 1780
const val ORIGIN_HEIGHT = 720

data class Hotspot(var x: Int, val y: Int, val description: String, val drawableRes: ArrayList<Int>){
    //将id当作description传递到下一级
    constructor(x: Int,y: Int,id:String):this(x, y, id, arrayListOf())
    var scaleX = x.toDouble()/ ORIGIN_WIDTH
    var scaleY = y.toDouble()/ ORIGIN_HEIGHT
}

fun Hotspot.calculateScale(width:Int, height:Int): Hotspot {
    this.scaleX = x.toDouble()/width
    this.scaleY = y.toDouble()/height
    return this
}