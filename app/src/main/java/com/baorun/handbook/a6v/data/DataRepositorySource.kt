package com.baorun.handbook.a6v.data


import com.baorun.handbook.a6v.AppContext
import com.baorun.handbook.a6v.R
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
            1054, 491, listOf(
                Hotspots( "1", Hotspots.Point(57, 271), "r_cdxt",),
                Hotspots("2", Hotspots.Point(83, 99),"r_jyyld"),
                Hotspots("3",Hotspots.Point(249, 99),"r_fdjlqy", ),
                Hotspots("4", Hotspots.Point(213, 99),"r_srs"),
                Hotspots("5", Hotspots.Point(793, 99),"r_epb"),
                Hotspots("6", Hotspots.Point(831, 99),"r_ebd"),
                Hotspots("7", Hotspots.Point(171, 99),"r_eps"),
                Hotspots("8", Hotspots.Point(973, 99),"r_qpzyj"),
                Hotspots("9", Hotspots.Point(712, 99),"r_qckzy"),
                Hotspots("10", Hotspots.Point(313, 99),"r_jsyzy"),
                Hotspots("11", Hotspots.Point(893, 99),"r_depzy"),
                Hotspots("12", Hotspots.Point(57, 313),"r_xtgz"),
                Hotspots("13", Hotspots.Point(750, 99),"r_cmkq"),
                Hotspots("14", Hotspots.Point(126, 99),"r_sffxp"),
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
            1054, 490, listOf(
                Hotspots("1",Hotspots.Point(57,203),"y_fdjgz"),
                Hotspots("2",Hotspots.Point(57,164),"y_pfgz"),
                Hotspots("3",Hotspots.Point(755,430),"y_ryd"),
                Hotspots("4",Hotspots.Point(998,264),"y_epb"),
                Hotspots("5",Hotspots.Point(998,129),"y_esp"),
                Hotspots("6",Hotspots.Point(998,163),"y_espoff"),
                Hotspots("7",Hotspots.Point(998,192),"y_abs"),
                Hotspots("8",Hotspots.Point(57,126),"y_hhdlbsq"),
                Hotspots("9",Hotspots.Point(154,99),"y_tpms"),
                Hotspots("10",Hotspots.Point(194,99),"y_zsyxh"),
                Hotspots("11",Hotspots.Point(973,95),"y_qpzyj"),
                Hotspots("12",Hotspots.Point(903,99),"y_mqjc"),
                Hotspots("13",Hotspots.Point(82,99),"y_hxkz"),
                Hotspots("14",Hotspots.Point(229,99),"y_hwd"),
                Hotspots("15",Hotspots.Point(996,229),"y_xpfz"),
                Hotspots("16",Hotspots.Point(938,99),"y_cdpl"),
                Hotspots("17",Hotspots.Point(998,353),"y_gpf"),
                Hotspots("18",Hotspots.Point(239,426),"y_dldc"),
                Hotspots("19",Hotspots.Point(57,320),"y_jglxs")
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
            1054, 490, listOf(
                Hotspots("1",Hotspots.Point(351,95),"g_zzxxh"),
                Hotspots("2",Hotspots.Point(704,95),"g_yzxxh"),
                Hotspots("3",Hotspots.Point(797,95),"g_epb"),
                Hotspots("4",Hotspots.Point(284,95),"g_wzd"),
                Hotspots("5",Hotspots.Point(998,361),"g_xlys"),
                Hotspots("6",Hotspots.Point(901,95),"g_mqjczt"),
                Hotspots("7",Hotspots.Point(291,429),"g_ready"),
                Hotspots("8",Hotspots.Point(526,346),"g_qzev"),
                Hotspots("9",Hotspots.Point(58,283),"g_evksx")
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
            1054, 491, listOf(
                Hotspots("1",Hotspots.Point(66,95),"b_hxkzzt"),
                Hotspots("2",Hotspots.Point(195,95),"b_zsyyc"),
                Hotspots("3",Hotspots.Point(248,95),"b_zsywc"),
                Hotspots("4",Hotspots.Point(323,95),"b_ygd"),
                Hotspots("5",Hotspots.Point(353,95),"b_znygd"),
                Hotspots("6",Hotspots.Point(939,95),"w_cdplzt"),
                Hotspots("7",Hotspots.Point(130,95),"w_sffxp"),
                Hotspots("8",Hotspots.Point(98,95),"h_hxkzzt"),
                Hotspots("9",Hotspots.Point(226,95),"h_zsywc"),
                Hotspots("10",Hotspots.Point(163,95),"h_zsyyc"),
                Hotspots("11",Hotspots.Point(967,95),"w_cdplzt"),
                Hotspots("12",Hotspots.Point(852,95),"w_depzy"),
                Hotspots("13",Hotspots.Point(288,95),"w_znygd"),
                Hotspots("14",Hotspots.Point(997,352),"w_gpf")
            )
        )
    }

    fun getIndicatorData(type:String,id: String):IndicatorData?

    fun getSceneList(page:Int):Flow<List<ChildrenData>>

    fun getQuestionList(page: Int):Flow<List<ChildrenData>>

    fun getVideoList(page: Int):Flow<List<ChildrenData>>

    fun getVoiceById(id:String):Flow<ChildrenData?>

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
