package com.baorun.handbook.a6v.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.baorun.handbook.a6v.R
import com.baorun.handbook.a6v.db.CollectEntity
import com.baorun.handbook.a6v.db.HistoryEntity


/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/19.
 */
data class Data(val dataType: String, val data: List<ChildrenData>)

@Entity()
data class ChildrenData(
    var parentId: String,
    var belong: String,
    @PrimaryKey var id: String,
    var name: String,
    var htmlUrl: String,
    var description: String,
    @Ignore val data1:List<String> = emptyList(),
    @Ignore val data2:List<String> = emptyList(),
    @Ignore val children: List<ChildrenData> = emptyList()
) {

    constructor():this("","","","","","")

    //封面资源
    @Ignore var coverRes: Int? = null
    @Ignore var checked:Boolean = false
}


fun ChildrenData.toCollectEntity(): CollectEntity {
    return CollectEntity(parentId,belong,id,name,htmlUrl, description)
}
fun ChildrenData.toHistoryEntity(): HistoryEntity {
    return HistoryEntity(parentId,belong,id,name,htmlUrl, description)
}

/**
 * Get cj cover res by id
 * 获取场景模块的封面图
 * @param id
 * @return
 */
fun getCjCoverResById(id: String): Int {
    return when (id) {
        "cj_1" -> R.drawable.img_scene_cover_1
        "cj_2" -> R.drawable.img_scene_cover_2
        "cj_3" -> R.drawable.img_scene_cover_3
        "cj_4" -> R.drawable.img_scene_cover_4
        "cj_5" -> R.drawable.img_scene_cover_5
        "cj_6" -> R.drawable.img_scene_cover_6
        "cj_7" -> R.drawable.img_scene_cover_7
        "cj_8" -> R.drawable.img_scene_cover_8
        "cj_9" -> R.drawable.img_scene_cover_9
        "cj_10" -> R.drawable.img_scene_cover_10
        "cj_11" -> R.drawable.img_scene_cover_11
        "cj_12" -> R.drawable.img_scene_cover_12
        "cj_13" -> R.drawable.img_scene_cover_13
        "cj_14" -> R.drawable.img_scene_cover_14
        "cj_15" -> R.drawable.img_scene_cover_15
        else -> -1
    }
}


fun getQuestionCoverResById(id: String): Int {
    return when (id) {
        "wt_1" -> R.drawable.img_wenti_cover_01
        "wt_2" -> R.drawable.img_wenti_cover_02
        "wt_3" -> R.drawable.img_wenti_cover_03
        "wt_4" -> R.drawable.img_wenti_cover_04
        "wt_5" -> R.drawable.img_wenti_cover_05
        "wt_6" -> R.drawable.img_wenti_cover_06
        "wt_7" -> R.drawable.img_wenti_cover_07
        "wt_8" -> R.drawable.img_wenti_cover_08
        "wt_9" -> R.drawable.img_wenti_cover_09
        "wt_10" -> R.drawable.img_wenti_cover_10
        else -> -1
    }
}
