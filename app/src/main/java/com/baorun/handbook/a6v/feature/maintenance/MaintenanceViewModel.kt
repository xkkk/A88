package com.baorun.handbook.a6v.feature.maintenance

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baorun.handbook.a6v.data.ChildrenData
import com.baorun.handbook.a6v.data.DataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MaintenanceViewModel:ViewModel() {
    val maintenanceLiveData = MutableLiveData<ChildrenData>()
    fun getData(id:String){
        viewModelScope.launch(Dispatchers.IO){
            DataManager.getMaintenanceDetail(id).collect {
                it?.let {
                    maintenanceLiveData.postValue(it)
                }
            }
        }
    }
}