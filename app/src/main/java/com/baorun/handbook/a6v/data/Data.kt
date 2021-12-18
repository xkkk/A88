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


