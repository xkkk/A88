package com.baorun.handbook.a88.feature.warn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.baorun.handbook.a88.data.ChildrenData
import com.baorun.handbook.a88.data.DataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/20.
 */
class WarnViewModel:ViewModel() {



    val warnListLiveData = MutableLiveData<List<ChildrenData>>()

    val warnData = MutableLiveData<ChildrenData?>()

    fun getWarnList(){
        viewModelScope.launch(Dispatchers.IO) {
            DataManager.getWarnList(1).collect {
                warnListLiveData.postValue(it)
            }
        }
    }

    fun getWarnById(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            DataManager.getWarnById(id).collect {
                warnData.postValue(it)
            }
        }
    }

    fun getVoiceData(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            DataManager.getVoiceById(id).collect {
                warnData.postValue(it)
            }
        }
    }

}