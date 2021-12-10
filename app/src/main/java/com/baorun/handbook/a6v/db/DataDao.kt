package com.baorun.handbook.a6v.db

import androidx.room.*
import com.baorun.handbook.a6v.data.ChildrenData

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(list:List<ChildrenData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertData(list: ChildrenData)

    @Update
     fun updateData(data: ChildrenData)

    @Delete
     fun deleteData(data: ChildrenData)


    @Transaction
    @Query("SELECT * FROM childrendata WHERE name LIKE '%' || :key || '%' ")
    fun search(key:String):List<ChildrenData>
}