package com.baorun.handbook.a88.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_collect")
data class CollectEntity(
    var parentId: String,
    var belong: String,
    @PrimaryKey var id: String,
    var name: String,
    var htmlUrl: String,
    var description: String,
)
