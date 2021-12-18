package com.baorun.handbook.a6v.feature.mine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baorun.handbook.a6v.data.DataManager
import com.baorun.handbook.a6v.network.FeedbackDataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedbackViewModel:ViewModel() {

    val result = MutableLiveData<Boolean>()

    val feedbackList = MutableLiveData<List<FeedbackDataResponse>>()

    val deleteResult = MutableLiveData<Boolean>()

    val refreshData = MutableLiveData<Boolean>()
    fun submit(type:String,content:String){
        viewModelScope.launch(Dispatchers.IO){
            runCatching {
                val response = DataManager.postFeedback(type, content)
                if(!response.result){
                    throw IllegalArgumentException()
                }else{
                    refreshData.postValue(true)
                    result.postValue(true)
                }
            }.onFailure {
                result.postValue(false)
            }

        }
    }


    fun feedbackList(){
//        feedbackList.value = emptyList()
        viewModelScope.launch(Dispatchers.IO){
            runCatching {
                val response = DataManager.postFeedbackList()
                if(response.result){
                    feedbackList.postValue(response.listData)
                }else{
                    throw IllegalArgumentException()
                }
            }.onFailure {
                feedbackList.postValue(emptyList())
            }.onSuccess {

            }

        }
    }

    fun deleteFeedback(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val response = DataManager.postFeedbackDelete(id)
                if(response.result){
                    deleteResult.postValue(true)
                }else{
                    throw IllegalArgumentException()
                }
            }.onFailure {
                deleteResult.postValue(false)
            }
        }

    }
}