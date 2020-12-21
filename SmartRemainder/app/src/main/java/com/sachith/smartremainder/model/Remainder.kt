package com.sachith.smartremainder.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Remainder {

    @PrimaryKey
    @ColumnInfo(name = "Title")
    var title:String = ""

    @ColumnInfo(name = "Date")
    var date: String = ""

    @ColumnInfo(name = "Time")
    var time: String = ""

    constructor(title: String, date: String, time: String) {
        this.title = title
        this.date = date
        this.time = time
    }
}