package com.baorun.handbook.a6v.data

import android.util.PlatformUtil
import com.baorun.handbook.a6v.data.*
import com.blankj.utilcode.util.GsonUtils
import com.baorun.handbook.a6v.App
import com.baorun.handbook.a6v.AppContext
import com.baorun.handbook.a6v.Constant
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.data.*
import com.baorun.handbook.a6v.db.collectDao
import com.baorun.handbook.a6v.db.historyDao
import com.baorun.handbook.a6v.network.BaseResponse
import com.baorun.handbook.a6v.network.FeedbackDataResponse
import com.baorun.handbook.a6v.repository.MasterDataSource
import com.baorun.handbook.a6v.repository.NormalDataSource
import com.blankj.utilcode.util.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext


object DataManager : DataRepositorySource {


//    val isMaster: Boolean by lazy {
//        PlatformUtil.getInstance(AppContext).isMasterCarType
//        App.isMaster
//    }

    val isMaster: Boolean
        get() = App.isMaster
    private lateinit var dataSource: DataRepositorySource

    fun initDataSource() {
        val dataSource = NormalDataSource()
        this.dataSource = if (isMaster) MasterDataSource(dataSource) else dataSource
    }

    fun getHome360Res(): Int {
        return if (isMaster) R.drawable.assets_images_home_360_m else R.drawable.assets_images_home_360
    }

    fun getVisionOut(): Array<Int> {
        return if (isMaster) arrayOf(
            R.drawable.img_vision_out_1_m,
            R.drawable.img_vision_out_2_m
        ) else arrayOf(R.drawable.img_vision_out_1, R.drawable.img_vision_out_2)
    }

    override fun getVisionOut1HotspotList(): HotSpotWrapper {
        return dataSource.getVisionOut1HotspotList()
    }

    override fun getVisionOut2HotspotList(): HotSpotWrapper {
        return dataSource.getVisionOut2HotspotList()
    }

    override fun getVisionIn1HotspotList(): HotSpotWrapper {
        return dataSource.getVisionIn1HotspotList()
    }

    override fun getVisionIn2HotspotList(): HotSpotWrapper {
        return dataSource.getVisionIn2HotspotList()
    }

    override fun getIndicatorData(type: String, id: String): IndicatorData? {
        return dataSource.getIndicatorData(type, id)
    }


    override fun getSceneList(page: Int): Flow<List<ChildrenData>> {
        return dataSource.getSceneList(page)
    }

    override fun getQuestionList(page: Int): Flow<List<ChildrenData>> {
        return dataSource.getQuestionList(page)
    }

    override fun getGNList(page: Int): Flow<List<ChildrenData>> {
        return dataSource.getGNList(page)
    }

    override fun getWarnList(page: Int): Flow<List<ChildrenData>> {
        return dataSource.getWarnList(page)
    }

    override fun getWarnById(id: String): Flow<ChildrenData?> {
        return dataSource.getWarnById(id)
    }

    override fun getSceneDetail(belong: String): List<ChildrenData> {
        return dataSource.getSceneDetail(belong)
    }

    override fun getMaintenanceDetail(id: String): Flow<ChildrenData?> {
        return dataSource.getMaintenanceDetail(id)
    }

    override fun search(key: String): Flow<List<ChildrenData>> {
        LogUtils.i("search:$key")
        return dataSource.search(key)
    }


    suspend fun search(): List<ChildrenData> {
        return dataSource.getSceneList(1).first()
    }

    override fun findDataById(id: String): ChildrenData? {
        return dataSource.findDataById(id)
    }

    override suspend fun postFeedback(type: String, content: String): BaseResponse<Any> {
        return dataSource.postFeedback(type, content)
    }

    override suspend fun postFeedbackList(): BaseResponse<FeedbackDataResponse> {
        return dataSource.postFeedbackList()
    }

    override suspend fun postFeedbackDelete(id: Int): BaseResponse<Any> {
        return dataSource.postFeedbackDelete(id)
    }

    /**
     * 插入搜索记录
     */
    suspend fun insertHistory(data: ChildrenData) {
        withContext(Dispatchers.IO) {
            historyDao.insertData(data.toHistoryEntity())
        }
    }

    /**
     * 获取搜索记录
     */
    suspend fun getHistory(): Flow<List<ChildrenData>> {
        return flow {
            val list = historyDao.query()
            emit(list.reversed().take(10))
        }.flowOn(Dispatchers.IO)
    }


    /**
     * 插入收藏记录
     */
    suspend fun insertCollect(data: ChildrenData) {
        withContext(Dispatchers.IO) {
            collectDao.insertData(data.toCollectEntity())
        }
    }

    /**
     * 插入收藏记录
     */
    suspend fun insertCollect(id: String) {
        withContext(Dispatchers.IO) {
            findDataById(id)?.let {
                collectDao.insertData(it.toCollectEntity())
            }
        }
    }

    /**
     * 删除收藏记录
     */
    suspend fun deleteCollect(data: ChildrenData) {
        withContext(Dispatchers.IO) {
            collectDao.deleteData(data.toCollectEntity())
        }
    }


    suspend fun deleteCollect(id: String) {
        withContext(Dispatchers.IO) {
            findDataById(id)?.let {
                collectDao.deleteData(it.toCollectEntity())
            }
        }
    }

    /**
     * 查询收藏记录
     */
    suspend fun isExits(id: String): Boolean {
        val result: Int?
        withContext(Dispatchers.IO) {
            result = collectDao.isExits(id)
        }
        return result != null
    }

    /**
     * 获取收藏列表
     */
    suspend fun getCollectionList(): Flow<List<ChildrenData>> {
        return flow {
            val list = collectDao.query()
            emit(list.reversed())
        }.flowOn(Dispatchers.IO)
    }


    override fun test() {
    }


}