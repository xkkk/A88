package com.baorun.handbook.a88.network

data class FeedbackDataResponse(
    val id: Int,
    val type: String,
    val content: String,
    val createTimeString: String,
    val reply: String,
    val replyTimeString: String,
    val userId: String
)
