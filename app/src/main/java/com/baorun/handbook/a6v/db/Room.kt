package com.baorun.handbook.a6v.db

import androidx.room.Room
import com.baorun.handbook.a6v.AppContext


private const val DB_NAME = "a6v.db"


val room =
    Room.databaseBuilder(AppContext, AppDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

val dataDao = room.dataDao()
val historyDao = room.historyDao()
val collectDao = room.collectDao()