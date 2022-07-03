package com.example.todoapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todoapp.R
import com.example.todoapp.database.DbHelper
import com.example.todoapp.models.ToDo
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        dbHelper = DbHelper(this)

        init()
    }

    private fun init(){
        button_update.setOnClickListener {
            var id = text_view_id.text.toString().toInt()
            var title = text_view_title.text.toString()
            var description = text_view_description.text.toString()
            var dailyTask = ToDo(id, title, description)

            dbHelper.updateTask(dailyTask)

            Toast.makeText(applicationContext,"Updated", Toast.LENGTH_LONG).show()

            startActivity(Intent(applicationContext, MainActivity::class.java))

        }
    }
}
