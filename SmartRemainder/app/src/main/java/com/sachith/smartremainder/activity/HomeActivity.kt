package com.sachith.smartremainder.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.room.Room
import com.sachith.smartremainder.composables.HomePage
import com.sachith.smartremainder.database.DbConnection
import com.sachith.smartremainder.model.Remainder

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomePage()
        }

        //Setup UI
        init()
    }

    //<editor-fold desc="Private Methods">

    //Setup UI
    private fun init() {

        //Hide status bar
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    //Load remainder data
    fun loadData(): List<Remainder> {
        val database = Room.databaseBuilder(this, DbConnection::class.java, "Remainder").allowMainThreadQueries().build()
        return database.getRemainderDao().fetch() as ArrayList<Remainder>
    }

    //Go to Add new activity
    fun goToAddNewPage(context: Context){
        context.startActivity(Intent(context,AddNewActivity::class.java))
    }

    //</editor-fold>
}

