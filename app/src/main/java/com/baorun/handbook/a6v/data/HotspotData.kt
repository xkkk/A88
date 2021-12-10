package com.baorun.handbook.a6v.data

import com.baorun.handbook.a6v.R


/**
 * Origin maintenance hotspot list
 * 892*440
 */
val originMaintenanceHotspotList = listOf(
    Hotspot(439,344,"wx_1"),
    Hotspot(285,290,"wx_2"),
    Hotspot(163,228,"wx_3"),
    Hotspot(239,128,"wx_4"),
    Hotspot(370,161,"wx_5"),
    Hotspot(496,239,"wx_6"),
    Hotspot(637,265,"wx_7"),
    Hotspot(729,190,"wx_8"),
    Hotspot(640,70,"wx_9"),
)


val maintenanceHotspotList =
    originMaintenanceHotspotList.map {
        it.calculateScale(892,440)
    }



/**
 * Origin vision in hotspot list1
 * out_1 的热点原始坐标点
 * 1398*687
 */

val originVisionOut1HotspotList = listOf(
    Hotspot(525, 306, "前雨刷", arrayListOf(R.drawable.img_vision_2_1)),
    Hotspot(
        608, 390,
        "前大灯",
        arrayListOf(
            R.drawable.img_vision_3_1,
            R.drawable.img_vision_3_2,
            R.drawable.img_vision_3_3,
            R.drawable.img_vision_3_4
        )
    ),
    Hotspot(
        392,
        358,
        "发动机仓盖",
        arrayListOf(R.drawable.img_vision_1_1,
            R.drawable.img_vision_1_2,
            R.drawable.img_vision_1_3,
            R.drawable.img_vision_1_4,
        )
    ),
    Hotspot(949, 326, "儿童安全锁", arrayListOf(R.drawable.img_vision_4_1)),
)



/**
 * Origin vision in hotspot list2
 * out_2 的热点原始坐标点
 */
val originVisionOut2HotspotList = listOf(
    Hotspot(
        402,
        275,
        "后雨刷",
        arrayListOf(R.drawable.img_vision_5_1)
    ),
    Hotspot(
        381,
        364,
        "掀背门",
        arrayListOf(R.drawable.img_vision_6_1,R.drawable.img_vision_6_2,R.drawable.img_vision_6_3,R.drawable.img_vision_6_4)
    ),
    Hotspot(
        666,
        330,
        "油箱盖板",
        arrayListOf(R.drawable.img_vision_7_1, R.drawable.img_vision_7_2, R.drawable.img_vision_7_3)
    ),
)





val originVisionIn1HotspotList = listOf(
    Hotspot(
        286,
        263,
        "方向盘",
        arrayListOf(R.drawable.img_vision_v3_1,
            R.drawable.img_vision_v3_2,
            R.drawable.img_vision_v3_3,
            R.drawable.img_vision_v3_4,
            R.drawable.img_vision_v3_5
        )
    ),
    Hotspot(
        241,
        305,
        "仪表盘左侧开关",
        arrayListOf(
            R.drawable.img_vision_v1_1,
            R.drawable.img_vision_v1_2,
        )
    ),
    Hotspot(
        107,
        328,
        "左前门开关",
        arrayListOf(R.drawable.img_vision_v2_1, R.drawable.img_vision_v2_2, R.drawable.img_vision_v2_3)
    ),
    Hotspot(528, 315, "空调控制面板", arrayListOf(R.drawable.img_vision_v4_1)),
    Hotspot(
        483,
        418,
        "换挡控制面板",
        arrayListOf(R.drawable.img_vision_v5_1, R.drawable.img_vision_v5_2, R.drawable.img_vision_v5_3, R.drawable.img_vision_v5_4, R.drawable.img_vision_v5_5)
    ),
)


val originVisionIn2HotspotList = listOf(
    Hotspot(
        521,
        127,
        "顶灯",
        arrayListOf(
            R.drawable.img_vision_v7_1,
            R.drawable.img_vision_v7_2,
            R.drawable.img_vision_v7_3,
            R.drawable.img_vision_v7_4,
            R.drawable.img_vision_v7_5,
            R.drawable.img_vision_v7_6,
            R.drawable.img_vision_v7_7,
            R.drawable.img_vision_v7_8,
            R.drawable.img_vision_v7_9,
        )
    ),
    Hotspot(
        330,
        200,
        "主驾座椅",
        arrayListOf(
            R.drawable.img_vision_v6_1,
            R.drawable.img_vision_v6_2,
            R.drawable.img_vision_v6_3,
            R.drawable.img_vision_v6_4,
            R.drawable.img_vision_v6_5,
            R.drawable.img_vision_v6_6
        )
    ),
    Hotspot(
        181,
        181,
        "安全带调整",
        arrayListOf(
            R.drawable.img_vision_v8_1
        )
    ),
)
