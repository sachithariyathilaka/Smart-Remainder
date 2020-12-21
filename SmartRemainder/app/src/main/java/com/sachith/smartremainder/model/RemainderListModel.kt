package com.sachith.smartremainder.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.sachith.smartremainder.database.DbConnection

class RemainderListModel
constructor(context: Context): ViewModel(){

    fun newSearch(context: Context): List<Remainder>{
        val database = Room.databaseBuilder(context, DbConnection::class.java, "Remainder").allowMainThreadQueries().build()
        return database.getRemainderDao().fetch()
    }

}

