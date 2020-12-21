package com.sachith.smartremainder.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sachith.smartremainder.model.Remainder

@Dao
interface RemainderDao {

    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(remainder: Remainder)

    //Fetch
    @Query("SELECT * FROM Remainder")
    fun fetch(): List<Remainder>
}