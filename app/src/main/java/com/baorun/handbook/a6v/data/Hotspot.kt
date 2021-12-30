package com.baorun.handbook.a6v.data

import com.baorun.handbook.a6v.Constant
import java.util.concurrent.Flow
import kotlin.math.roundToInt


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


data class HotSpotWrapper(val baseWidth: Int,val baseHeight: Int,val hotspots: List<Hotspots>)

data class Hotspots(val id: String,val point:Point,val description: String,val drawableRes: ArrayList<Int>){

    //维修保养
    constructor( id: String,point: Point,description: String=""):this(id, point, description, arrayListOf())
    //指示器
    constructor(x: Int,y: Int,id: String,description: String):this(id,
        Point(x,y),description,
        arrayListOf())

    //兼容之前的写法
    constructor(x: Int,y: Int,id: String):this(id,Point(x,y))

    //视觉索引 内饰 外饰
    constructor(point: Point,description: String,drawableRes: ArrayList<Int>):this("", point, description, drawableRes)
    constructor(x:Int,y:Int,description: String,drawableRes: ArrayList<Int>):this(Point(x,y),description,drawableRes)


    fun scale(scaleX: Float,scaleY: Float):Hotspots{
        this.point.x = (point.x*scaleX).roundToInt()
        this.point.y = (point.y*scaleY).roundToInt()
        return this
    }

    /**
     * Point
     *
     * @property x 原始坐标 x
     * @property y 原始坐标 y
     * @property radius 半径
     * @constructor Create empty Point
     */
    data class Point(var x:Int,var y:Int,var radius:Int = Constant.RADIUS){
    }
}
