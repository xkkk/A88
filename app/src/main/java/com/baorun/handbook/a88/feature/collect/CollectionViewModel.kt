package com.baorun.handbook.a88.feature.collect

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baorun.handbook.a88.data.ChildrenData
import com.baorun.handbook.a88.data.DataManager

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CollectionViewModel:ViewModel() {

    val collectionList = MutableLiveData<List<ChildrenData>>()

    val collectStatus = MutableLiveData<Boolean>()

    val currentData = MutableLiveData<ChildrenData>()

    fun getCollectionList(){
            viewModelScope.launch(Dispatchers.IO){
                DataManager.getCollectionList().collect{
                    collectionList.postValue(it)
                }
            }
    }

    fun delete(data: ChildrenData){
        viewModelScope.launch(Dispatchers.IO) {
            DataManager.deleteCollect(data)
        }
    }

    fun delete(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            DataManager.deleteCollect( id)
        }
    }

    fun insert(data: ChildrenData){
        viewModelScope.launch(Dispatchers.IO){
            DataManager.insertCollect(data)
        }
    }

    fun insert(id:String){
        viewModelScope.launch(Dispatchers.IO){
            DataManager.insertCollect( id)
        }
    }

    fun isCollect(id:String){
        viewModelScope.launch(Dispatchers.IO){
           val isExits = DataManager.isExits(id)
            collectStatus.postValue(isExits)
        }
    }

    /*
    * 仅 针对常见问题和车辆告警模块
    * */
    fun getCurrentData(id:String){
      viewModelScope.launch(Dispatchers.IO) {
         val data = DataManager.findDataById( id)
          data?.let {
              currentData.postValue(it)
          }
      }
    }
}