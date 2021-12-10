package com.baorun.handbook.a6v.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baorun.handbook.a6v.data.ChildrenData

@Database(entities = [ChildrenData::class, HistoryEntity::class, CollectEntity::class],version = 2,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun dataDao(): DataDao
    abstract fun historyDao(): HistoryDao
    abstract fun collectDao(): CollectDao
}