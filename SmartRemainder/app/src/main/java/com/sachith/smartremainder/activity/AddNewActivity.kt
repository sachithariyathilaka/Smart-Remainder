package com.sachith.smartremainder.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.room.Room
import com.sachith.smartremainder.composables.AddNewPage
import com.sachith.smartremainder.database.DbConnection
import com.sachith.smartremainder.model.Remainder

class AddNewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddNewPage()
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

    //Go to Add new activity
    fun goBack(context: Context){
        context.startActivity(Intent(context,HomeActivity::class.java))
    }

    //Insert Remainder
    fun insertRemainder(context: Context, remainder: Remainder){
        val database = Room.databaseBuilder(context, DbConnection::class.java, "Remainder").allowMainThreadQueries().build()
        database.getRemainderDao().insert(remainder)
        Toast.makeText(context, "Remainder Saved", Toast.LENGTH_LONG).show()
        goBack(context)
    }

    //</editor-fold>
}
