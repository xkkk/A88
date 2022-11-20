package com.baorun.handbook.a6v.repository

import com.baorun.handbook.a6v.AppContext
import com.baorun.handbook.a6v.Constant
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.data.*
import com.baorun.handbook.a6v.network.Api
import com.baorun.handbook.a6v.network.BaseResponse
import com.baorun.handbook.a6v.network.FeedbackDataResponse
import com.baorun.handbook.a6v.network.RetrofitManager
import com.baorun.handbook.a6v.network.request.ClientAddBody
import com.baorun.handbook.a6v.network.request.DeviceId
import com.baorun.handbook.a6v.network.request.FeedbackDeleteBody
import com.baorun.handbook.a6v.utils.getDataJson
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class NormalDataSource : DataRepositorySource {

    //场景数据
    val changjingData: List<ChildrenData> by lazy {
        readJson("changjingData.json")
    }

    //用户手册数据
   private val gongnengData: List<ChildrenData>
        get() {
            return readJson("gongnengData.json")
        }

    //车辆告警数据
    private val warnData: List<ChildrenData>
        get() {
            return readJson("warnData.json")
        }

    //常见问题数据
    val wentiData: List<ChildrenData> by lazy {
        readJson("wentiData.json")
    }

    //维修保养数据
    private val weixiuData: List<ChildrenData> by lazy {
        readJson("weixiuData.json")
    }

    private val videoData:List<ChildrenData> by lazy{
        readJson("videoData.json")
    }

    private val voiceData:List<ChildrenData> by lazy{
        readJson("voiceData.json")
    }

    private val indicatorData: Indicator by lazy {
        readIndicatorJson()
    }

    private val api: Api by lazy {
        RetrofitManager.create(Api::class.java)
    }

    private fun readJson(fileName: String): List<ChildrenData> {
        val json = getDataJson(AppContext, fileName)
        val data = GsonUtils.fromJson(json, Data::class.java)
        return data.data
    }

    private fun readIndicatorJson(): Indicator {
        val json = getDataJson(AppContext, "zhishidengData.json")
        val data = GsonUtils.fromJson(json, Indicator::class.java)
        return data
    }

    override fun getIndicatorData(type: String, id: String): IndicatorData? {
        val data = getIndicatorData(type).find { it.id == id }
        return data
    }

    private fun getIndicatorData(type: String): List<IndicatorData> {
        return when (type) {
            IndicatorStyle.RED.name -> indicatorData.redData
            IndicatorStyle.BLUE.name -> indicatorData.blueData
            IndicatorStyle.GREEN.name -> indicatorData.greenData
            IndicatorStyle.YELLOW.name -> indicatorData.yellowData
            else -> emptyList()
        }
    }

    override fun getSceneList(page: Int): Flow<List<ChildrenData>> {
        val flow = flow {
            changjingData.forEach {
                it.coverRes = getCjCoverResById(it.id)
            }
            if (page == 1) {
                emit(changjingData.take(Constant.PAGE_SIZE))
            } else {
                emit(changjingData.takeLast(changjingData.size - Constant.PAGE_SIZE))
            }
        }.flowOn(Dispatchers.IO)
        return flow
    }

    override fun getQuestionList(page: Int): Flow<List<ChildrenData>> {
        val flow = flow {
            wentiData.forEach {
                it.coverRes = getQuestionCoverResById(it.id)
            }
            val list = when (page) {
                1 -> wentiData.take(Constant.PAGE_SIZE)
                2 -> wentiData.takeLast(wentiData.size - Constant.PAGE_SIZE)
                else -> emptyList()
            }
            emit(list)
        }.flowOn(Dispatchers.IO)

        return flow
    }

    override fun getVideoList(page: Int): Flow<List<ChildrenData>> {
        val flow = flow {
            videoData.forEach {
                it.coverRes = getVideoCoverResById(it.id)
            }
            val list = when (page) {
                1 -> videoData.take(8)
//                2 -> videoData.subList(App.PAGE_SIZE, App.PAGE_SIZE + App.PAGE_SIZE)
//                3 -> videoData.takeLast(videoData.size - App.PAGE_SIZE * 2)
                else -> emptyList()
            }
            emit(list)
        }.flowOn(Dispatchers.IO)

        return flow
    }

    override fun getGNList(page: Int): Flow<List<ChildrenData>> {
        val flow = flow{
            val temp = gongnengData
            emit(temp)
        }
        return flow
    }

    override fun getWarnList(page: Int): Flow<List<ChildrenData>> {
        val flow = flow {
            emit(warnData)
        }.flowOn(Dispatchers.IO)
        return flow
    }

    override fun getWarnById(id: String): Flow<ChildrenData?> {
        return flow {
            val data = warnData.find { it.id == id }
            emit(data)
        }.flowOn(Dispatchers.IO)
    }

    override fun getVoiceById(id: String): Flow<ChildrenData?> {
        return flow {
            val data = voiceData.find { it.id == id }
            emit(data)
        }.flowOn(Dispatchers.IO)
    }

    override fun getSceneDetail(belong: String): List<ChildrenData> {
        val childrenData = readJson("changjingData.json").filter { it.belong == belong }
        return childrenData
    }

    override fun getMaintenanceDetail(id: String): Flow<ChildrenData?> {
        val flow = flow {
            val childrenData = weixiuData.find { it.id == id }
            emit(childrenData)
        }.flowOn(Dispatchers.IO)
        return flow
    }


    override fun search(key: String): Flow<List<ChildrenData>> {
        return flow {
            val totalList = changjingData.plus(gongnengData)
            val result = mutableListOf<ChildrenData>()

            totalList.forEach {
                if (it.name.contains(key, true)) {
                    if (it.htmlUrl.isNotEmpty())
                        result.add(it)
                }
                it.children.forEach {
                    if (it.name.contains(key, true)) {
                        if (it.htmlUrl.isNotEmpty())
                            result.add(it)
                    }
                    it.children.forEach {
                        if (it.name.contains(key, true)) {
                            if (it.htmlUrl.isNotEmpty())
                                result.add(it)
                        }
                        it.children.forEach {
                            if (it.name.contains(key, true)) {
                                if (it.htmlUrl.isNotEmpty())
                                    result.add(it)
                            }
                        }
                    }

                }
            }
                emit(result)

        }.flowOn(Dispatchers.IO)
    }

    override fun findDataById(id: String): ChildrenData? {
        return when (id.split("_")[0]) {
            "wt" -> findDataById(wentiData, id)
            "gj" -> findDataById(warnData, id)
            "gn" -> findDataById(gongnengData, id)
            "cj" -> findDataById(changjingData, id)
            else -> null
        }
    }

    override suspend fun postFeedback(type: String, content: String): BaseResponse<Any> {
        return api.postFeedback(ClientAddBody(type,content))
    }

    override suspend fun postFeedbackList(): BaseResponse<FeedbackDataResponse> {
       return api.postFeedbackList(DeviceId())
    }

    override suspend fun postFeedbackDelete(id: Int): BaseResponse<Any> {
       return api.postFeedbackDelete(FeedbackDeleteBody(id))
    }


    override fun test() {
    }


    //常见问题模块 通过id查数据
    private fun findDataById(
        source: List<ChildrenData>,
        id: String
    ): ChildrenData? {
        val idSplits = id.split(".")
        val data = when (idSplits.size) {
            2 -> source.find { it.id == idSplits[0] }?.children?.find {
                it.id == id
            }
            else -> source.find { it.id == id }
        }
        return data
    }
    override fun getVisionIn1HotspotList(): HotSpotWrapper {
        return HotSpotWrapper(
            1920, 894, listOf(
                Hotspots(
                    603,
                    418,
                    "方向盘",
                    arrayListOf(
                        R.drawable.img_vision_v3_1,
                        R.drawable.img_vision_v3_2,
                        R.drawable.img_vision_v3_3,
                        R.drawable.img_vision_v3_4,
                        R.drawable.img_vision_v3_5
                    )
                ),
                Hotspots(
                    291,
                    500,
                    "仪表盘左侧开关",
                    arrayListOf(
                        R.drawable.img_vision_v1_1,
                        R.drawable.img_vision_v1_2,
                        R.drawable.img_vision_v1_3
                    )
                ),
                Hotspots(
                    523,
                    506,
                    "左前门开关",
                    arrayListOf(
                        R.drawable.img_vision_v2_1,
                        R.drawable.img_vision_v2_2,
                        R.drawable.img_vision_v2_3,
                    )
                ),
                Hotspots(960, 495, "空调控制面板", arrayListOf(R.drawable.img_vision_v4_1)),
                Hotspots(
                    917,
                    615,
                    "换挡控制面板",
                    arrayListOf(
                        R.drawable.img_vision_v5_1,
                        R.drawable.img_vision_v5_2,
                        R.drawable.img_vision_v5_3,
                        R.drawable.img_vision_v5_4,
                        R.drawable.img_vision_v5_5,
                    )
                )
            )
        )
    }

    override fun getVisionIn2HotspotList(): HotSpotWrapper {
        return HotSpotWrapper(1920,894,listOf(
            Hotspots(
                950,
                353,
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
            Hotspots(
                623,
                417,
                "主驾座椅",
                arrayListOf(
                    R.drawable.img_vision_v6_1,
                    R.drawable.img_vision_v6_2,
                    R.drawable.img_vision_v6_3,
                    R.drawable.img_vision_v6_4,
                    R.drawable.img_vision_v6_5,
                    R.drawable.img_vision_v6_6,
                )
            ),
            Hotspots(
                414,
                435,
                "安全带调整",
                arrayListOf(
                    R.drawable.img_vision_v8_1
                )
            )
        ))
    }

    override fun getVisionOut1HotspotList(): HotSpotWrapper {
        return HotSpotWrapper(1398,687, listOf(
            Hotspots(
                525,
                306,
                "前雨刷",
                arrayListOf(R.drawable.img_vision_2_1),
            ),
            Hotspots(
                571,
                379,
                "前大灯",
                arrayListOf(
                    R.drawable.img_vision_3_1,
                    R.drawable.img_vision_3_2,
                    R.drawable.img_vision_3_3,
                    R.drawable.img_vision_3_4
                )
            ),
            Hotspots(
                392,
                358,
                "发动机仓盖",
                arrayListOf(
                    R.drawable.img_vision_1_1,
                    R.drawable.img_vision_1_2,
                    R.drawable.img_vision_1_3,
                    R.drawable.img_vision_1_4,
                )
            ),
            Hotspots(
                949,
                326,
                "儿童安全锁",
                arrayListOf(R.drawable.img_vision_4_1),
            )
        ))

    }

    override fun getVisionOut2HotspotList(): HotSpotWrapper {
        return HotSpotWrapper(1398,687, listOf(
            Hotspots(
                402,
                275,
                "后雨刷",
                arrayListOf(R.drawable.img_vision_5_1)
            ),
            Hotspots(
                381,
                364,
                "掀背门",
                arrayListOf(
                    R.drawable.img_vision_6_1,
                    R.drawable.img_vision_6_2,
                    R.drawable.img_vision_6_3,
                    R.drawable.img_vision_6_4
                )
            ),
            Hotspots(
                666,
                330,
                "油箱盖板",
                arrayListOf(
                    R.drawable.img_vision_7_1,
                    R.drawable.img_vision_7_2,
                    R.drawable.img_vision_7_3
                )
            )
        ))
    }


    /**
     * Get cj cover res by id
     * 获取场景模块的封面图
     * @param id
     * @return
     */
   private fun getCjCoverResById(id: String): Int {
        return when (id) {
            "cj_1" -> R.drawable.img_scene_cover_1
            "cj_2" -> R.drawable.img_scene_cover_2
            "cj_3" -> R.drawable.img_scene_cover_3
            "cj_4" -> R.drawable.img_scene_cover_4
            "cj_5" -> R.drawable.img_scene_cover_5
            "cj_6" -> R.drawable.img_scene_cover_6
            "cj_7" -> R.drawable.img_scene_cover_7
            "cj_8" -> R.drawable.img_scene_cover_8
            "cj_9" -> R.drawable.img_scene_cover_9
            "cj_10" -> R.drawable.img_scene_cover_10
            "cj_11" -> R.drawable.img_scene_cover_11
            "cj_12" -> R.drawable.img_scene_cover_12
            "cj_13" -> R.drawable.img_scene_cover_13
            "cj_14" -> R.drawable.img_scene_cover_14
            "cj_15" -> R.drawable.img_scene_cover_15
            else -> -1
        }
    }


    private fun getQuestionCoverResById(id: String): Int {
        return when (id) {
            "wt_1" -> R.drawable.img_wenti_cover_01
            "wt_2" -> R.drawable.img_wenti_cover_02
            "wt_3" -> R.drawable.img_wenti_cover_03
            "wt_4" -> R.drawable.img_wenti_cover_04
            "wt_5" -> R.drawable.img_wenti_cover_05
            "wt_6" -> R.drawable.img_wenti_cover_06
            "wt_7" -> R.drawable.img_wenti_cover_07
            "wt_8" -> R.drawable.img_wenti_cover_08
            else -> -1
        }
    }
    /**
     * Get cj cover res by id
     * 获取视频模块的封面图
     * @param id
     * @return
     */
    private fun getVideoCoverResById(id: String): Int {
        return when (id) {
            "sp_1" -> R.drawable.assets_video_preview_1
            "sp_2" -> R.drawable.assets_video_preview_2
            "sp_3" -> R.drawable.assets_video_preview_3
            "sp_4" -> R.drawable.assets_video_preview_4
            "sp_5" -> R.drawable.assets_video_preview_5
            "sp_6" -> R.drawable.assets_video_preview_6
            "sp_7" -> R.drawable.assets_video_preview_7
            "sp_8" -> R.drawable.assets_video_preview_8
            else -> -1
        }
    }
}