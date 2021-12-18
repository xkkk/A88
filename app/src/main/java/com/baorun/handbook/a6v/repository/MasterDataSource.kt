package com.baorun.handbook.a6v.repository

import com.baorun.handbook.a6v.Constant
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.data.ChildrenData
import com.baorun.handbook.a6v.data.DataRepositorySource
import com.baorun.handbook.a6v.data.HotSpotWrapper
import com.baorun.handbook.a6v.data.Hotspots
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
        return HotSpotWrapper(1398,687, listOf(
            Hotspots(
                525,
                306,
                "前雨刷",
                arrayListOf(R.drawable.img_vision_2_1_m),
            ),
            Hotspots(
                571,
                379,
                "前大灯",
                arrayListOf(
                    R.drawable.img_vision_3_1_m,
                    R.drawable.img_vision_3_2_m,
                    R.drawable.img_vision_3_3_m,
                    R.drawable.img_vision_3_4_m
                )
            ),
            Hotspots(
                392,
                358,
                "发动机仓盖",
                arrayListOf(
                    R.drawable.img_vision_1_1_m,
                    R.drawable.img_vision_1_2_m,
                    R.drawable.img_vision_1_3_m,
                    R.drawable.img_vision_1_4_m,
                )
            ),
            Hotspots(
                949,
                326,
                "儿童安全锁",
                arrayListOf(R.drawable.img_vision_4_1_m),
            )
        ))
    }

    override fun getVisionOut2HotspotList(): HotSpotWrapper {
        return HotSpotWrapper(1398,687, listOf(
            Hotspots(
                402,
                275,
                "后雨刷",
                arrayListOf(R.drawable.img_vision_5_1_m)
            ),
            Hotspots(
                381,
                364,
                "掀背门",
                arrayListOf(
                    R.drawable.img_vision_6_1_m,
                    R.drawable.img_vision_6_2_m,
                    R.drawable.img_vision_6_3_m,
                    R.drawable.img_vision_6_4_m
                )
            ),
            Hotspots(
                666,
                330,
                "油箱盖板",
                arrayListOf(
                    R.drawable.img_vision_7_1_m,
                    R.drawable.img_vision_7_2_m,
                    R.drawable.img_vision_7_3_m
                )
            )
        ))
    }


    override fun getVisionIn1HotspotList(): HotSpotWrapper {
        return HotSpotWrapper(
            1055, 519, listOf(
                Hotspots(
                    286,
                    263,
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
                    241,
                    305,
                    "仪表盘左侧开关",
                    arrayListOf(
                        R.drawable.img_vision_v1_1_m,
                        R.drawable.img_vision_v1_2_m,
                        R.drawable.img_vision_v1_3_m,
                    )
                ),
                Hotspots(
                    107,
                    328,
                    "左前门开关",
                    arrayListOf(
                        R.drawable.img_vision_v2_1_m,
                        R.drawable.img_vision_v2_2_m,
                        R.drawable.img_vision_v2_3_m
                    )
                ),
                Hotspots(528, 315, "空调控制面板", arrayListOf(R.drawable.img_vision_v4_1)),
                Hotspots(
                    483,
                    418,
                    "换挡控制面板",
                    arrayListOf(
                        R.drawable.img_vision_v5_1_m,
                        R.drawable.img_vision_v5_2_m,
                        R.drawable.img_vision_v5_3_m,
                        R.drawable.img_vision_v5_4_m,
                        R.drawable.img_vision_v5_5_m
                    )
                )
            )
        )
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
            "wt_9" -> R.drawable.img_wenti_cover_09_m
            "wt_10" -> R.drawable.img_wenti_cover_10_m
            else -> -1
        }
    }
    override fun test() {
    }
}