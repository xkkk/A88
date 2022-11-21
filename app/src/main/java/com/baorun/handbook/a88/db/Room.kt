package com.baorun.handbook.a88.db

import androidx.room.Room
import com.baorun.handbook.a88.AppContext


private const val DB_NAME = "a88.db"


val room =
    Room.databaseBuilder(AppContext, AppDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

val dataDao = room.dataDao()
val historyDao = room.historyDao()
val collectDao = room.collectDao()