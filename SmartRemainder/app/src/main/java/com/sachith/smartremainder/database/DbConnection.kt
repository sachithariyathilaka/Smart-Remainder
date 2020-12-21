package com.sachith.smartremainder.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sachith.smartremainder.database.dao.RemainderDao
import com.sachith.smartremainder.model.Remainder

@Database(version = 1, entities = [Remainder::class])
abstract class DbConnection : RoomDatabase() {

    //Remainder Dao Initialize
    abstract fun getRemainderDao(): RemainderDao

}