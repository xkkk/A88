package com.baorun.handbook.a6v.data


import com.baorun.handbook.a6v.AppContext
import com.baorun.handbook.a6v.network.Api
import com.baorun.handbook.a6v.network.BaseResponse
import com.baorun.handbook.a6v.network.FeedbackDataResponse
import com.baorun.handbook.a6v.network.RetrofitManager
import com.baorun.handbook.a6v.network.request.ClientAddBody
import com.baorun.handbook.a6v.network.request.DeviceId
import com.baorun.handbook.a6v.network.request.FeedbackDeleteBody
import com.baorun.handbook.a6v.utils.getDataJson
import com.blankj.utilcode.util.GsonUtils
import kotlinx.coroutines.flow.Flow


interface DataRepositorySource {

    /**
     * Get maintenance hotspot list
     * 获取维修保养热点坐标
     * @return
     */
    fun getMaintenanceHotspotList(): HotSpotWrapper {
        return HotSpotWrapper(
            892, 440, listOf(
                Hotspots("wx_1", Hotspots.Point(439, 344)),
                Hotspots("wx_2", Hotspots.Point(285, 290)),
                Hotspots("wx_3", Hotspots.Point(163, 228)),
                Hotspots("wx_4", Hotspots.Point(239, 128)),
                Hotspots("wx_5", Hotspots.Point(370, 161)),
                Hotspots("wx_6", Hotspots.Point(496, 239)),
                Hotspots("wx_7", Hotspots.Point(637, 265)),
                Hotspots("wx_8", Hotspots.Point(729, 190)),
                Hotspots("wx_9", Hotspots.Point(640, 70))
            )
        )
    }

    /**
     * Get vision out1hotspot list
     * 外饰1
     * @return
     */
    fun getVisionOut1HotspotList(): HotSpotWrapper

    /**
     * Get vision out2hotspot list
     * 外饰2
     * @return
     */
    fun getVisionOut2HotspotList(): HotSpotWrapper

    /**
     * Get vision in1hotspot list
     * 内饰1
     * @return
     */
    fun getVisionIn1HotspotList(): HotSpotWrapper

    /**
     * Get vision in2hotspot list
     * 内饰2
     * @return
     */
    fun getVisionIn2HotspotList(): HotSpotWrapper


    /**
     * Get red indicator hotspot list
     * 红色指示器
     * @return
     */
    fun getRedIndicatorHotspotList(): HotSpotWrapper {
        return HotSpotWrapper(
            1057, 550, listOf(
                Hotspots("1", Hotspots.Point(127, 133)),
                Hotspots("2", Hotspots.Point(179, 133)),
                Hotspots("3", Hotspots.Point(241, 464)),
                Hotspots("4", Hotspots.Point(269, 131)),
                Hotspots("5", Hotspots.Point(939, 130)),
                Hotspots("6", Hotspots.Point(756, 132)),
                Hotspots("7", Hotspots.Point(796, 131)),
                Hotspots("8", Hotspots.Point(225, 132)),
                Hotspots("9", Hotspots.Point(976, 132)),
                Hotspots("10", Hotspots.Point(718, 131)),
                Hotspots("11", Hotspots.Point(313, 133)),
                Hotspots("12", Hotspots.Point(852, 133))
            )
        )
    }

    /**
     * Get red indicator hotspot list
     * 黄色指示器
     * @return
     */
    fun getYellowIndicatorHotspotList(): HotSpotWrapper {
        return HotSpotWrapper(
            1056, 550, listOf(
                Hotspots(55, 235, "1"),
                Hotspots(55, 199, "2"),
                Hotspots(757, 460, "3"),
                Hotspots(999, 301, "4"),
                Hotspots(999, 159, "5"),
                Hotspots(999, 196, "6"),
                Hotspots(999, 233, "7"),
                Hotspots(55, 160, "8"),
                Hotspots(154, 131, "9"),
                Hotspots(190, 130, "10"),
                Hotspots(975, 131, "11"),
                Hotspots(117, 135, "12"),
                Hotspots(82, 132, "13"),
                Hotspots(230, 132, "14"),
                Hotspots(999, 266, "15"),
                Hotspots(999, 360, "16"),
                Hotspots(907, 132, "17")
            )
        )
    }

    /**
     * Get red indicator hotspot list
     * 绿色指示器
     * @return
     */
    fun getGreenIndicatorHotspotList(): HotSpotWrapper {
        return HotSpotWrapper(
            1054, 550, listOf(
                Hotspots(350, 129, "1"),
                Hotspots(703, 129, "2"),
                Hotspots(797, 129, "3"),
                Hotspots(217, 134, "4"),
                Hotspots(285, 131, "5"),
                Hotspots(942, 132, "6"),
                Hotspots(110, 135, "7"),
                Hotspots(999, 360, "8"),
                Hotspots(999, 396, "9"),
                Hotspots(902, 133, "10"),
                Hotspots(248, 134, "11")
            )
        )
    }

    /**
     * Get red indicator hotspot list
     * 蓝白指示器
     * @return
     */
    fun getBlueIndicatorHotspotList(): HotSpotWrapper {
        return HotSpotWrapper(
            1054, 550, listOf(
                Hotspots(65, 131, "1"),
                Hotspots(164, 131, "2"),
                Hotspots(225, 130, "3"),
                Hotspots(320, 130, "4"),
                Hotspots(355, 130, "5"),
                Hotspots(260, 132, "6"),
                Hotspots(99, 131, "7"),
                Hotspots(194, 131, "8"),
                Hotspots(131, 131, "9"),
                Hotspots(941, 131, "10"),
                Hotspots(870, 131, "11"),
                Hotspots(290, 131, "12")
            )
        )
    }

    fun getIndicatorData(type:String,id: String):IndicatorData?

    fun getSceneList(page:Int):Flow<List<ChildrenData>>

    fun getQuestionList(page: Int):Flow<List<ChildrenData>>

    fun getGNList(page: Int):Flow<List<ChildrenData>>

    fun getWarnList(page: Int):Flow<List<ChildrenData>>

    fun getWarnById(id:String): Flow<ChildrenData?>

    fun getSceneDetail(belong:String):List<ChildrenData>

    fun getMaintenanceDetail(id:String):Flow<ChildrenData?>

    fun search(key:String):Flow<List<ChildrenData>>

    fun findDataById(id:String):ChildrenData?

    suspend fun postFeedback(type:String,content:String): BaseResponse<Any>

    suspend fun postFeedbackList(): BaseResponse<FeedbackDataResponse>

    suspend fun postFeedbackDelete(id:Int):BaseResponse<Any>

    fun test()

}
