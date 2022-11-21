package com.baorun.handbook.a88.db

import androidx.room.*
import com.baorun.handbook.a88.data.ChildrenData

@Dao
interface CollectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertData(data: CollectEntity)

    @Delete
     fun deleteData(data: CollectEntity)

    @Transaction
    @Query("SELECT id,parentId,belong,htmlUrl,description,name FROM table_collect")
    fun query():List<ChildrenData>

    @Query("SELECT 1 FROM table_collect WHERE id=:id limit 1")
     fun isExits(id:String):Int?
}