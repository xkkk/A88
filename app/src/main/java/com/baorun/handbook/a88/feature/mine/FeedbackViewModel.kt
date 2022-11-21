package com.baorun.handbook.a88.feature.mine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baorun.handbook.a88.data.DataManager
import com.baorun.handbook.a88.network.FeedbackDataResponse
import com.blankj.utilcode.util.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedbackViewModel : ViewModel() {

    val result = MutableLiveData<Pair<Boolean, String>>()

    val feedbackList = MutableLiveData<List<FeedbackDataResponse>>()

    val deleteResult = MutableLiveData<Pair<Boolean, String>>()

    val refreshData = MutableLiveData<Boolean>()
    fun submit(type: String, content: String) {
        if (NetworkUtils.isConnected()) {
            viewModelScope.launch(Dispatchers.IO) {
                runCatching {
                    val response = DataManager.postFeedback(type, content)
                    if (!response.result) {
                        throw IllegalArgumentException("提交失败")
                    } else {
                        refreshData.postValue(true)
                        result.postValue(Pair(true, "提交成功"))
                    }
                }.onFailure {
                    result.postValue(Pair(false, it.message ?: "提交失败"))
                }
            }
        } else {
            result.postValue(Pair(false, "网络链接异常"))
        }
    }


    fun feedbackList() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val response = DataManager.postFeedbackList()
                if (response.result) {
                    feedbackList.postValue(response.listData)
                } else {
                    throw IllegalArgumentException()
                }
            }.onFailure {
                feedbackList.postValue(emptyList())
            }.onSuccess {

            }

        }
    }

    fun deleteFeedback(id: Int) {
        if(NetworkUtils.isConnected()) {
            viewModelScope.launch(Dispatchers.IO) {
                runCatching {
                    val response = DataManager.postFeedbackDelete(id)
                    if (response.result) {
                        deleteResult.postValue(Pair(true, "删除成功"))
                    } else {
                        throw IllegalArgumentException()
                    }
                }.onFailure {
                    deleteResult.postValue(Pair(false, "删除失败"))
                }
            }

        }else{
            deleteResult.postValue(Pair(false,"网络链接异常"))
        }
    }
}