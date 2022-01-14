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
            1045, 514, listOf(
                Hotspots("wx_1", Hotspots.Point(131, 116)),
                Hotspots("wx_2", Hotspots.Point(234, 116)),
                Hotspots("wx_3", Hotspots.Point(336, 116)),
                Hotspots("wx_4", Hotspots.Point(438, 116)),
                Hotspots("wx_5", Hotspots.Point(542, 116)),
                Hotspots("wx_6", Hotspots.Point(643, 116)),
                Hotspots("wx_7", Hotspots.Point(744, 116)),
                Hotspots("wx_8", Hotspots.Point(848, 116)),
                Hotspots("wx_9", Hotspots.Point(950, 116))
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
            1051, 547, listOf(
                Hotspots( "1", Hotspots.Point(55, 274), "r_cdxt",),
                Hotspots("2", Hotspots.Point(73, 132),"r_jyyld"),
                Hotspots("3",Hotspots.Point(165, 132),"r_fdjlqy", ),
                Hotspots("4", Hotspots.Point(204, 132),"r_srs"),
                Hotspots("5", Hotspots.Point(936, 132),"r_cdplzt"),
                Hotspots("6", Hotspots.Point(748, 132),"r_epb"),
                Hotspots("7", Hotspots.Point(787, 132),"r_ebd"),
                Hotspots("8", Hotspots.Point(123, 132),"r_eps"),
                Hotspots("9", Hotspots.Point(993, 161),"r_qpzyj"),
                Hotspots("10", Hotspots.Point(708, 132),"r_qckzy"),
                Hotspots("11", Hotspots.Point(312, 132),"r_jsyzy"),
                Hotspots("12", Hotspots.Point(842, 133),"r_depzy"),
                Hotspots("13", Hotspots.Point(56, 329),"r_xtgz")
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
            1051, 547, listOf(
                Hotspots("1",Hotspots.Point(57,234),"y_fdjgz"),
                Hotspots("2",Hotspots.Point(55,198),"y_pfgz"),
                Hotspots("3",Hotspots.Point(754,462),"y_ryd"),
                Hotspots("4",Hotspots.Point(996,346),"y_epb"),
                Hotspots("5",Hotspots.Point(996,201),"y_esp"),
                Hotspots("6",Hotspots.Point(996,233),"y_espoff"),
                Hotspots("7",Hotspots.Point(996,271),"y_abs"),
                Hotspots("8",Hotspots.Point(57,159),"y_hhdlbsq"),
                Hotspots("9",Hotspots.Point(153,131),"y_tpms"),
                Hotspots("10",Hotspots.Point(191,131),"y_zsyxh"),
                Hotspots("11",Hotspots.Point(992,159),"y_qpzyj"),
                Hotspots("12",Hotspots.Point(117,131),"y_sffxp"),
                Hotspots("13",Hotspots.Point(82,131),"y_hxkz"),
                Hotspots("14",Hotspots.Point(230,131),"y_hwd"),
                Hotspots("15",Hotspots.Point(996,309),"y_xpfz"),
                Hotspots("18",Hotspots.Point(239,459),"y_dldc"),
                Hotspots("19",Hotspots.Point(57,358),"y_jglxs"),



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
            1051, 548, listOf(
                Hotspots("1",Hotspots.Point(348,131),"g_zzxxh"),
                Hotspots("2",Hotspots.Point(701,131),"g_yzxxh"),
                Hotspots("3",Hotspots.Point(796,131),"g_epb"),
                Hotspots("5",Hotspots.Point(284,131),"g_wzd"),
                Hotspots("6",Hotspots.Point(934,131),"g_cdplzt"),
                Hotspots("7",Hotspots.Point(117,134),"g_sffxp"),
                Hotspots("9",Hotspots.Point(996,394),"g_xlys"),
                Hotspots("10",Hotspots.Point(888,131),"g_mqjczt"),
                Hotspots("11",Hotspots.Point(250,131),"g_qwd"),
                Hotspots("12",Hotspots.Point(291,461),"g_ready"),
                Hotspots("13",Hotspots.Point(525,378),"g_qzev"),
                Hotspots("14",Hotspots.Point(843,130),"g_evksx"),
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
            1050, 548, listOf(
                Hotspots("1",Hotspots.Point(66,130),"b_hxkzzt"),
                Hotspots("2",Hotspots.Point(184,131),"b_zsyyc"),
                Hotspots("3",Hotspots.Point(241,131),"b_zsywc"),
                Hotspots("4",Hotspots.Point(324,131),"b_ygd"),
                Hotspots("5",Hotspots.Point(355,131),"b_znygd"),
                Hotspots("7",Hotspots.Point(95,130),"h_hxkzzt"),
                Hotspots("8",Hotspots.Point(211,131),"h_zsywc"),
                Hotspots("9",Hotspots.Point(153,131),"h_zsyyc"),
                Hotspots("10",Hotspots.Point(938,131),"w_cdplzt"),
                Hotspots("11",Hotspots.Point(842,132),"w_depzy"),
                Hotspots("12",Hotspots.Point(291,131),"w_znygd"),
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
