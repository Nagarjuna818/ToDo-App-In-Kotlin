package com.example.todoapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.adapters.ToDoAdapter
import com.example.todoapp.database.DbHelper
import com.example.todoapp.models.ToDo
import kotlinx.android.synthetic.main.activity_add_task.*
import kotlinx.android.synthetic.main.activity_main.*

class AddTaskActivity : AppCompatActivity() {
    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        dbHelper = DbHelper(this)

        init()
    }

    private fun init(){
        button_save.setOnClickListener {
            var id = edit_text_id.text.toString().toInt()
            var title = edit_text_title.text.toString()
            var description = edit_text_description.text.toString()
            var dailyTask = ToDo(id, title, description)

            dbHelper.addTask(dailyTask)

            Toast.makeText(applicationContext,"Created", Toast.LENGTH_LONG).show()
            startActivity(Intent(applicationContext,MainActivity::class.java))

        }
    }
}