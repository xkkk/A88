package com.baorun.handbook.a88.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baorun.handbook.a88.data.ChildrenData

@Database(entities = [ChildrenData::class, HistoryEntity::class, CollectEntity::class],version = 3,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun dataDao(): DataDao
    abstract fun historyDao(): HistoryDao
    abstract fun collectDao(): CollectDao
}