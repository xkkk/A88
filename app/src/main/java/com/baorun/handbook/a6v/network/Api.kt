package com.baorun.handbook.a6v.network

import com.baorun.handbook.a6v.network.request.ClientAddBody
import com.baorun.handbook.a6v.network.request.DeviceId
import com.baorun.handbook.a6v.network.request.FeedbackDeleteBody
import retrofit2.http.Body
import retrofit2.http.POST


interface Api {


    /**
     * 提交反馈
     */
    @POST("feedback/clientAdd")
    suspend fun postFeedback(@Body param: ClientAddBody): BaseResponse<Any>

    /**
     * 获取反馈列表
     */
    @POST("feedback/listByUserId")
    suspend fun postFeedbackList(@Body param: DeviceId): BaseResponse<FeedbackDataResponse>


    /**
     * 删除反馈记录
     */
    @POST("feedback/clientDelete")
    suspend fun postFeedbackDelete(@Body param: FeedbackDeleteBody): BaseResponse<Any>


}