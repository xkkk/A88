package com.baorun.handbook.a6v.db

import androidx.room.*
import com.baorun.handbook.a6v.data.ChildrenData

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertData(data: HistoryEntity)

    @Delete
     fun deleteData(data: HistoryEntity)

    @Transaction
    @Query("SELECT id,parentId,belong,htmlUrl,description,name FROM table_history")
    fun query():List<ChildrenData>
}