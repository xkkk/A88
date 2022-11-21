package com.baorun.handbook.a88.repository

import com.baorun.handbook.a88.Constant
import com.baorun.handbook.a88.R
import com.baorun.handbook.a88.data.ChildrenData
import com.baorun.handbook.a88.data.DataRepositorySource
import com.baorun.handbook.a88.data.HotSpotWrapper
import com.baorun.handbook.a88.data.Hotspots
import com.blankj.utilcode.util.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MasterDataSource(val dataSource: NormalDataSource):DataRepositorySource by dataSource {

    override fun getSceneList(page: Int): Flow<List<ChildrenData>> {
        val flow = flow {
            dataSource.changjingData.forEach {
                it.coverRes = getCjCoverResById(it.id)
            }
            if (page == 1) {
                emit(dataSource.changjingData.take(Constant.PAGE_SIZE))
            } else {
                emit(dataSource.changjingData.takeLast(dataSource.changjingData.size - Constant.PAGE_SIZE))
            }
        }.flowOn(Dispatchers.IO)
        return flow
    }

    override fun getQuestionList(page: Int): Flow<List<ChildrenData>> {
        val flow = flow {
            dataSource.wentiData.forEach {
                it.coverRes = getQuestionCoverResById(it.id)
            }
            val list = when (page) {
                1 -> dataSource.wentiData.take(Constant.PAGE_SIZE)
                2 -> dataSource.wentiData.takeLast(dataSource.wentiData.size - Constant.PAGE_SIZE)
                else -> emptyList()
            }
            emit(list)
        }.flowOn(Dispatchers.IO)

        return flow
    }

    override fun getVisionOut1HotspotList(): HotSpotWrapper {
        return HotSpotWrapper(978,455, listOf(
            Hotspots(
                388,
                172,
                "前雨刷",
                arrayListOf(R.drawable.img_vision_2_1_m,R.drawable.img_vision_2_2_m),
            ),
            Hotspots(
                434,
                218,
                "前大灯",
                arrayListOf(
                    R.drawable.img_vision_3_1_m,
                    R.drawable.img_vision_3_2_m,
                    R.drawable.img_vision_3_3_m,
                    R.drawable.img_vision_3_4_m
                )
            ),
            Hotspots(
                297,
                216,
                "发动机仓盖",
                arrayListOf(
                    R.drawable.img_vision_1_1_m,
                    R.drawable.img_vision_1_2_m,
                    R.drawable.img_vision_1_3_m,
                    R.drawable.img_vision_1_4_m,
                    R.drawable.img_vision_1_5_m,
                )
            ),
            Hotspots(
                692,
                187,
                "儿童安全锁",
                arrayListOf(R.drawable.img_vision_4_1_m),
            )
        ))
    }

    override fun getVisionOut2HotspotList(): HotSpotWrapper {
        return HotSpotWrapper(1920,1080, listOf(
//            Hotspots(
//                673,
//                392,
//                "后雨刷",
//                arrayListOf(
//                    R.drawable.img_vision_5_1_m,
//                    R.drawable.img_vision_5_2_m,
//                    R.drawable.img_vision_5_3_m,
//                    R.drawable.img_vision_5_4_m,
//                    R.drawable.img_vision_5_5_m,
//                    R.drawable.img_vision_5_6_m,
//                    R.drawable.img_vision_5_7_m,
//                )
//            ),
            Hotspots(
                621,
                595,
                "掀背门",
                arrayListOf(
                    R.drawable.img_vision_5_1_m,
                    R.drawable.img_vision_5_2_m,
                    R.drawable.img_vision_5_3_m,
                    R.drawable.img_vision_5_4_m,
                    R.drawable.img_vision_5_5_m,
                    R.drawable.img_vision_5_6_m,
                    R.drawable.img_vision_5_7_m,
                )
            ),
            Hotspots(
                1007,
                501,
                "油箱盖板",
                arrayListOf(
                    R.drawable.img_vision_6_1_m,
                    R.drawable.img_vision_6_2_m,
                    R.drawable.img_vision_6_3_m,
                    R.drawable.img_vision_6_4_m
                )
            )
        ))
    }


    override fun getVisionIn1HotspotList(): HotSpotWrapper {
        return HotSpotWrapper(
            1920, 894, listOf(
                Hotspots(
                    603,
                    418,
                    "方向盘",
                    arrayListOf(
                        R.drawable.img_vision_v3_1_m,
                        R.drawable.img_vision_v3_2_m,
                        R.drawable.img_vision_v3_3_m,
                        R.drawable.img_vision_v3_4_m,
                        R.drawable.img_vision_v3_5_m
                    )
                ),
                Hotspots(
                    291,
                    500,
                    "仪表盘左侧开关",
                    arrayListOf(
                        R.drawable.img_vision_v1_1_m,
                        R.drawable.img_vision_v1_2_m
                    )
                ),
                Hotspots(
                    523,
                    506,
                    "左前门开关",
                    arrayListOf(
                        R.drawable.img_vision_v2_1_m,
                        R.drawable.img_vision_v2_2_m,
                        R.drawable.img_vision_v2_3_m,
                        R.drawable.img_vision_v2_4_m,
                    )
                ),
                Hotspots(960, 495, "空调控制面板", arrayListOf(R.drawable.img_vision_v4_1_m)),
                Hotspots(
                    917,
                    615,
                    "换挡控制面板",
                    arrayListOf(
                        R.drawable.img_vision_v5_1_m,
                        R.drawable.img_vision_v5_2_m,
                        R.drawable.img_vision_v5_3_m,
                        R.drawable.img_vision_v5_4_m,
                        R.drawable.img_vision_v5_5_m,
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
                    R.drawable.img_vision_v7_1_m,
                    R.drawable.img_vision_v7_2_m,
                    R.drawable.img_vision_v7_3_m,
                    R.drawable.img_vision_v7_4_m,
                    R.drawable.img_vision_v7_5_m,
                    R.drawable.img_vision_v7_6_m,
                    R.drawable.img_vision_v7_7_m,
                )
            ),
            Hotspots(
                623,
                417,
                "主驾座椅",
                arrayListOf(
                    R.drawable.img_vision_v6_1_m,
                    R.drawable.img_vision_v6_2_m,
                    R.drawable.img_vision_v6_3_m,
                    R.drawable.img_vision_v6_4_m,
                    R.drawable.img_vision_v6_5_m,
                    R.drawable.img_vision_v6_6_m,
                    R.drawable.img_vision_v6_7_m,
                )
            ),
            Hotspots(
                414,
                435,
                "安全带调整",
                arrayListOf(
                    R.drawable.img_vision_v8_1_m
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
            "cj_1" -> R.drawable.img_scene_cover_1_m
            "cj_2" -> R.drawable.img_scene_cover_2_m
            "cj_3" -> R.drawable.img_scene_cover_3_m
            "cj_4" -> R.drawable.img_scene_cover_4_m
            "cj_5" -> R.drawable.img_scene_cover_5_m
            "cj_6" -> R.drawable.img_scene_cover_6_m
            "cj_7" -> R.drawable.img_scene_cover_7_m
            "cj_8" -> R.drawable.img_scene_cover_8_m
            "cj_9" -> R.drawable.img_scene_cover_9_m
            "cj_10" -> R.drawable.img_scene_cover_10_m
            "cj_11" -> R.drawable.img_scene_cover_11_m
            "cj_12" -> R.drawable.img_scene_cover_12_m
            "cj_13" -> R.drawable.img_scene_cover_13_m
            "cj_14" -> R.drawable.img_scene_cover_14_m
            "cj_15" -> R.drawable.img_scene_cover_15_m
            else -> -1
        }
    }

    private fun getQuestionCoverResById(id: String): Int {
        return when (id) {
            "wt_1" -> R.drawable.img_wenti_cover_01_m
            "wt_2" -> R.drawable.img_wenti_cover_02_m
            "wt_3" -> R.drawable.img_wenti_cover_03_m
            "wt_4" -> R.drawable.img_wenti_cover_04_m
            "wt_5" -> R.drawable.img_wenti_cover_05_m
            "wt_6" -> R.drawable.img_wenti_cover_06_m
            "wt_7" -> R.drawable.img_wenti_cover_07_m
            "wt_8" -> R.drawable.img_wenti_cover_08_m
            else -> -1
        }
    }
    override fun test() {
    }
}