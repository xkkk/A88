package com.baorun.handbook.a6v.data

import com.baorun.handbook.a6v.data.*
import com.blankj.utilcode.util.GsonUtils
import com.baorun.handbook.a6v.App
import com.baorun.handbook.a6v.AppContext
import com.baorun.handbook.a6v.Constant
import com.baorun.handbook.a6v.data.*
import com.baorun.handbook.a6v.db.collectDao
import com.baorun.handbook.a6v.db.historyDao
import com.baorun.handbook.a6v.network.Api
import com.baorun.handbook.a6v.network.RetrofitManager
import com.baorun.handbook.a6v.utils.getDataJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext


val api: Api = RetrofitManager.create(Api::class.java)

object DataManager {


    private const val VEHICLE_TYPE_A60 = 52
    private const val VEHICLE_TYPE_6V = 64

    /**
     * 是否是A60
     */
    private val isA60 = App.vehicleType == VEHICLE_TYPE_A60

    //场景数据
    private val changjingData: List<ChildrenData> by lazy {
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
    private val wentiData: List<ChildrenData> by lazy {
        readJson("wentiData.json")
    }

    //维修保养数据
    private val weixiuData: List<ChildrenData> by lazy {
        readJson("weixiuData.json")
    }


    private val indicatorData: Indicator by lazy {
        readIndicatorJson()
    }

    private fun readJson(fileName: String): List<ChildrenData> {
        val json = getDataJson(AppContext, fileName)
        val data = GsonUtils.fromJson(json, Data::class.java)
        return data.data
    }


    /**
     * Get scene list
     * 获取场景列表
     * @param page
     * @return
     */
    fun getSceneListFromJson(page: Int): Flow<List<ChildrenData>> {
        val flow = flow {
            changjingData.forEach {
                it.coverRes = if(isA60) getCjCoverResById(it.id) else getCjCoverResById(it.id)
            }
            if (page == 1) {
                emit(changjingData.take(Constant.PAGE_SIZE))
            } else {
                emit(changjingData.takeLast(changjingData.size - Constant.PAGE_SIZE))
            }
        }.flowOn(Dispatchers.IO)
        return flow
    }


    /**
     * 获取常见问题列表
     */
    fun getQuestionListFromJson(page: Int): Flow<List<ChildrenData>> {
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

    /**
     * 获取用户手册列表
     */
    fun getGNListFromJson(): Flow<List<ChildrenData>> {
        val flow = flow {
            val temp = gongnengData
            emit(temp)
        }.flowOn(Dispatchers.IO)
        return flow
    }


    /**
     * 根据场景id 获取场景详情
     */
    fun getCJFromJson(belong: String): List<ChildrenData> {
        val childrenData = readJson("changjingData.json").filter { it.belong == belong }
        return childrenData
    }

    /**
     * 根据维修保养的id 获取详情
     */
    fun getMaintenanceDataFromJson(id: String): Flow<ChildrenData?> {
        val flow = flow {
            val childrenData = weixiuData.find { it.id == id }
            emit(childrenData)
        }.flowOn(Dispatchers.IO)
        return flow
    }


    /**
     * 根据关键字查询
     * 查询 场景、视频、功能数据
     * 只查询带有详情页的 即含htmlurl的数据
     *
     */
    fun search(key: String): Flow<List<ChildrenData>> {
        return flow {
            withContext(Dispatchers.IO) {

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
                        val third = it.children.filter { it.name.contains(key, true) }
                        if (third.isNotEmpty()) {
                            result.addAll(third)
                        }

                    }
                }
                emit(result)
            }
        }.flowOn(Dispatchers.IO)

    }


    /**
     * 插入搜索记录
     */
    suspend fun insertHistory(data: ChildrenData) {

        historyDao.insertData(data.toHistoryEntity())
    }

    /**
     * 获取搜索记录
     */
    fun getHistory(): Flow<List<ChildrenData>> {
        return flow {
            val list = historyDao.query()
            emit(list.reversed().take(10))
        }.flowOn(Dispatchers.IO)
    }


    /**
     * 插入收藏记录
     */
    suspend fun insertCollect(data: ChildrenData) {
        collectDao.insertData(data.toCollectEntity())
    }

    /**
     * 插入收藏记录
     */
    suspend fun insertCollect(id: String) {

        findDataById(id)?.let {
            collectDao.insertData(it.toCollectEntity())
        }
    }

    /**
     * 删除收藏记录
     */
    suspend fun deleteCollect(data: ChildrenData) {
        collectDao.deleteData(data.toCollectEntity())
    }


    suspend fun deleteCollect(id: String) {

        findDataById(id)?.let {
            collectDao.deleteData(it.toCollectEntity())
        }
    }

    /**
     * 查询收藏记录
     */
    suspend fun isExits(id: String): Boolean {
        val result = collectDao.isExits(id)
        return result != null
    }

    /**
     * 获取收藏列表
     */
    fun getCollectionList(): Flow<List<ChildrenData>> {
        return flow {
            val list = collectDao.query()
            emit(list.reversed())
        }.flowOn(Dispatchers.IO)
    }


    /**
     * Find data by id
     *
     * @param belong
     * @param id
     * @return
     */
    fun findDataById(id: String): ChildrenData? {
        return when (id.split("_")[0]) {
            "wt" -> findDataById(wentiData, id)
            "gj" -> findDataById(warnData, id)
            "gn" -> findDataById(gongnengData, id)
            "cj" -> findDataById(changjingData, id)
            else -> null
        }
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


    /**
     * 获取车辆告警列表
     */
    fun getWarnListFromJson(): Flow<List<ChildrenData>> {
        val flow = flow {
            emit(warnData)
        }.flowOn(Dispatchers.IO)
        return flow
    }


    fun getWarnById(id: String): Flow<ChildrenData?> {
        return flow {
            val data = warnData.find { it.id == "gj_$id" }
            emit(data)
        }.flowOn(Dispatchers.IO)
    }


    private fun readIndicatorJson(): Indicator {
        val json = getDataJson(AppContext, "zhishidengData.json")
        val data = GsonUtils.fromJson(json, Indicator::class.java)
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

    fun getTipsPairData(type: String, id: String): Pair<String, String>? {
        val data = getIndicatorData(type).find { it.id == id }
        data?.let {
            return Pair(it.title, it.content.text)
        }
        return null
    }


    fun getVisionIn1HotspotList(): List<Hotspot> {
        return originVisionIn1HotspotList.map { it.calculateScale(1055,519) }
    }

    fun getVisionIn2HotspotList(): List<Hotspot> {
        return originVisionIn2HotspotList.map { it.calculateScale(1055,519) }
    }

    fun getVisionOut1HotspotList(): List<Hotspot> {
        return originVisionOut1HotspotList.map { it.calculateScale(1398,687) }
    }

    fun getVisionOut2HotspotList(): List<Hotspot> {
        return originVisionOut2HotspotList.map { it.calculateScale(1398,687) }
    }
}