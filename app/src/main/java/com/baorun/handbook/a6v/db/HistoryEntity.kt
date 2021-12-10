package com.baorun.handbook.a6v.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "table_history")
data class HistoryEntity(
    var parentId: String,
    var belong: String,
    @PrimaryKey var id: String,
    var name: String,
    var htmlUrl: String,
    var description: String,
)
