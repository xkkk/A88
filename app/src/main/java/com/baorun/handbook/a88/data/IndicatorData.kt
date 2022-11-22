package com.baorun.handbook.a88.data


enum class IndicatorStyle{
    BLUE,GREEN,YELLOW,RED
}

data class Indicator(
    val blueData: List<IndicatorData>,
    val greenData: List<IndicatorData>,
    val yellowData: List<IndicatorData>,
    val redData: List<IndicatorData>,
)

data class IndicatorData(val id: String, val name:String,val title: String, val content: Content) {
    data class Content(val text: String)
}


/**
{
"id": 1,
"title": "盲区监测状态指示灯*",
"content": {
"text": "指示灯红色点亮时，表示盲区监测系统存在故障。"
}
},
{
"id": 1,
"title": "充电系统报警灯",
"content": {
"text": "整车电源在“ON”挡位，发动机未启动时，报警灯点亮；发动机启动后，报警灯熄灭。若发动机启动后，报警灯点亮，表示充电系统存在故障。"
}
},
{
"id": 2,
"title": "机油压力低报警灯",
"content": {
"text": "整车电源在“ON”挡位，发动机未启动时，报警灯点亮；发动机启动后，报警灯熄灭。若发动机启动后，报警灯点亮，表示发动机机油压力不足。"
}
},
{
"id": 3,
"title": "发动机冷却液温度高指示灯",
"content": {
"text": "指示灯红色点亮表示发动机冷却液温度过高。"
}
},
{
"id": 4,
"title": "辅助保护系统（SRS）指示灯",
"content": {
"text": "指示灯红色点亮表示安全气囊系统存在故障。"
}
},
{
"id": 5,
"title": "车道偏离状态指示灯*",
"content": {
"text": "指示灯红色点亮表示车道偏离系统存在故障，出现此现象时，请及时前往广汽传祺特约店进行检修。"
}
},
{
"id": 6,
"title": "电子驻车（EPB）状态指示灯",
"content": {
"text": "指示灯红色点亮表示施加电子驻车制动。指示灯红色闪烁表示电子驻车制动器部分接合或存在故障。"
}
},
{
"id": 7,
"title": "驻车制动与制动系统指示灯",
"content": {
"text": "指示灯红色点亮表示制动液位过低或制动力分配系统（EBD）存在故障。"
}
},
{
"id": 8,
"title": "电动助力转向（EPS）指示灯",
"content": {
"text": "指示灯红色点亮表示电动助力转向（EPS）系统存在故障。"
}
},
{
"id": 9,
"title": "前碰撞预警状态指示灯*",
"content": {
"text": "指示灯红色闪烁表示前碰撞预警系统正在触发工作。"
}
},
{
"id": 10,
"title": "前乘客座椅安全带提示灯",
"content": {
"text": "提示灯红色点亮表示前排乘员座椅安全带未系好或系统存在故障。"
}
},
{
"id": 11,
"title": "驾驶员座椅安全带提示灯",
"content": {
"text": "提示灯红色点亮表示驾驶员安全带未系好或安全带系统存在故障。"
}
},
{
"id": 12,
"title": "第二排座椅安全带提示灯*",
"content": {
"text": "指示灯红色点亮表示后排对应的座椅安全带未系好或安全带系统存在故障。"
}
}
 */
val originRedIndicatorHotspotList = listOf(
    Hotspot(127, 123, "1"),
    Hotspot(179, 133, "2"),
    Hotspot(241,464, "3"),
    Hotspot(269, 131, "4"),
    Hotspot(939, 130, "5"),
    Hotspot(756, 132, "6"),
    Hotspot(796, 131, "7"),
    Hotspot(225, 132, "8"),
    Hotspot(976, 132, "9"),
    Hotspot(718, 131, "10"),
    Hotspot(313, 133, "11"),
    Hotspot(852, 123, "12"),
    Hotspot(852, 133, "13"),
)


val redIndicatorHotspotList = originRedIndicatorHotspotList.map {
    it.calculateScale(1057, 550)
}

/**
"id": 1,
"title": "发动机故障指示灯",

"id": 2,
"title": "排放故障指示灯",

"id": 3,
"title": "燃油低指示灯",

"id": 4,
"title": "电子驻车（EPB）故障指示灯",

"id": 5,
"title": "车辆稳定性辅助（ESP）指示灯",

"id": 6,
"title": "车辆稳定性辅助关闭（ESP OFF）指示灯",

"id": 7,
"title": "防抱死制动系统（ABS）指示灯 ",

"id": 8,
"title": "变速器故障指示灯*",

"id": 9,
"title": "胎压监测系统（TPMS）指示灯*",

"id": 10,
"title": "自适应巡航故障指示灯*",

"id": 11,
"title": "前碰缓解状态指示灯*",

"id": 12,
"title": "手扶方向盘指示灯*",

"id": 13,
"title": "横向控制状态指示灯*",

"id": 14,
"title": "后雾灯指示灯",

"id": 15,
"title": "下坡辅助指示灯*",

"id": 16,
"title": "四轮驱动（4WD）智能模式指示灯**",
 */
private val originYellowIndicatorHotspotList = listOf(
    Hotspot(55, 235, "1"),
    Hotspot(55, 199, "2"),
    Hotspot(757, 460, "3"),
    Hotspot(999, 301, "4"),
    Hotspot(999, 159, "5"),
    Hotspot(999, 196, "6"),
    Hotspot(999, 233, "7"),
    Hotspot(55, 160, "8"),
    Hotspot(154, 131, "9"),
    Hotspot(190, 130, "10"),
    Hotspot(975, 131, "11"),
    Hotspot(117, 135, "12"),
    Hotspot(82, 132, "13"),
    Hotspot(230, 132, "14"),
    Hotspot(999, 266, "15"),
    Hotspot(999, 360, "16"),
    Hotspot(907, 132, "17"),
    )
val yellowIndicatorHotspotList = originYellowIndicatorHotspotList.map {
    it.calculateScale(1056, 550)
}

/**
    "id": 1,
    "title": "左转向信号与危险警告指示灯",

    "id": 2,
    "title": "右转向信号与危险警告指示灯",

    "id": 3,
    "title": "电子驻车（EPB）状态指示灯",

    "id": 4,
    "title": "定速巡航指示灯*",

    "id": 5,
    "title": "位置灯指示灯*",

    "id": 6,
    "title": "车道偏离状态指示灯*",

    "id": 7,
    "title": "手扶方向盘指示灯*",

    "id": 8,
    "title": "四轮驱动（4WD）锁止模式指示灯*",

    "id": 9,
    "title": "虚拟钥匙连接指示灯*",

    "id": 10,
    "title": "盲区监测状态指示灯",

 */

private val originGreenIndicatorHotspotList = listOf(
    Hotspot(350,129,"1"),
    Hotspot(703,129,"2"),
    Hotspot(797,129,"3"),
    Hotspot(217,134,"4"),
    Hotspot(285,131,"5"),
    Hotspot(942,132,"6"),
    Hotspot(110,135,"7"),
    Hotspot(999,360,"8"),
    Hotspot(999,396,"9"),
    Hotspot(902,133,"10"),
    Hotspot(248,134,"11"),
)

val greenIndicatorHotspotList = originGreenIndicatorHotspotList.map {
    it.calculateScale(1054,550)
}


/**
    "id": 1,
    "title": "横向控制状态指示灯",

    "id": 2,
    "title": "自适应巡航前方有车辆指示灯*",

    "id": 3,
    "title": "自适应巡航前方无车辆指示灯*",

    "id": 4,
    "title": "远光灯指示灯*",

    "id": 5,
    "title": "智能远光灯指示灯*",

    "id": 6,
    "title": "定速巡航指示灯*",

    "id": 7,
    "title": "横向控制状态指示灯*",

    "id": 8,
    "title": "自适应巡航前方无车辆指示灯*",
    "id": 9,
    "title": "自适应巡航前方有车辆指示灯*",
    "id": 10,
    "title": "车道偏移状态指示灯*",
    "id": 11,
    "title": "第二排座椅安全带提示灯*",
    "id": 12,
    "title": "智能远光灯指示灯*",
*/
//1009*529
private val originBlueIndicatorHotspotList = listOf(
    Hotspot(65,131,"1"),
    Hotspot(164,131,"2"),
    Hotspot(225,130,"3"),
    Hotspot(320,130,"4"),
    Hotspot(355,130,"5"),

    Hotspot(260,132,"6"),

    Hotspot(99,131,"7"),

    Hotspot(194,131,"8"),
    Hotspot(131,131,"9"),
    Hotspot(941,131,"10"),
    Hotspot(870,131,"11"),
    Hotspot(290,131,"12"),

)

val blueIndicatorHotspotList = originBlueIndicatorHotspotList.map {
    it.calculateScale(1054,550)
}
