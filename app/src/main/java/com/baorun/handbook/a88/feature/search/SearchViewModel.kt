package com.baorun.handbook.a88.feature.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.baorun.handbook.a88.data.ChildrenData
import com.baorun.handbook.a88.data.Data
import com.baorun.handbook.a88.data.DataManager
import com.blankj.utilcode.util.LogUtils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {


    val historyList = MutableLiveData<List<ChildrenData>>()

    private var _searchFlow = MutableStateFlow("")
    val searchResultList = _searchFlow.debounce(500).filter { it.isNotEmpty() }.flatMapLatest {
        searchFromData(it)
    }.asLiveData()

    private fun searchFromData(key: String):Flow<List<ChildrenData>>{
        return DataManager.search(key)
    }

    fun search(key:String) {
        _searchFlow.value = key
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