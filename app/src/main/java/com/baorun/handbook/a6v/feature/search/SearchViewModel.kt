package com.baorun.handbook.a6v.feature.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baorun.handbook.a6v.data.ChildrenData
import com.baorun.handbook.a6v.data.DataManager

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {


    val searchResultList = MutableLiveData<List<ChildrenData>>()
    val historyList = MutableLiveData<List<ChildrenData>>()

    fun search(key:String){
        viewModelScope.launch(Dispatchers.IO) {
            DataManager.search(key).collect {
                searchResultList.postValue(it)
            }
        }
    }

    fun insertHistory(data: ChildrenData){
        viewModelScope.launch(Dispatchers.IO) {
            DataManager.insertHistory(data)
        }
    }


    fun getHistoryList(){
        viewModelScope.launch(Dispatchers.IO){
            DataManager.getHistory().collect {
                historyList.postValue(it)
            }
        }
    }
}