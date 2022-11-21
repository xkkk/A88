package com.baorun.handbook.a88.feature.scene

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
 * Created by xukun on 2021/8/15.
 */
class SceneViewModel:ViewModel() {

    val sceneListLiveDataNew = MutableLiveData<List<ChildrenData>>()

    val videoListLivaDataNew = MutableLiveData<List<ChildrenData>>()

    fun getSceneList(page:Int){
        viewModelScope.launch(Dispatchers.IO){
            DataManager.getSceneList(page).collect {
                sceneListLiveDataNew.postValue(it)
            }
        }
    }

    fun getQuestionList(page: Int){
        viewModelScope.launch(Dispatchers.IO){
            DataManager.getQuestionList(page).collect {
                sceneListLiveDataNew.postValue(it)
            }
        }
    }

    fun getVideoList(page: Int){
        viewModelScope.launch(Dispatchers.IO) {
            DataManager.getVideoList(page).collect {
                videoListLivaDataNew.postValue(it)
            }
        }
    }

}