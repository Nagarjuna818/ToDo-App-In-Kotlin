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
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var dbHelper: DbHelper
    lateinit var dailyTaskAdapter: ToDoAdapter
    var mList: ArrayList<ToDo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        dbHelper = DbHelper(this)
        init()
    }

    private fun init(){
        button_add.setOnClickListener {
            var intent = Intent(applicationContext, AddTaskActivity::class.java)
            startActivity(intent)
        }

        dailyTaskAdapter = ToDoAdapter(this)
        recycler_view.adapter = dailyTaskAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)


        mList = dbHelper.getAllTasks()
        dailyTaskAdapter.setData(mList)

        Toast.makeText(applicationContext, "Updated", Toast.LENGTH_LONG).show()
    }

    override fun onRestart() {
        super.onRestart()
        mList = dbHelper.getAllTasks()
        dailyTaskAdapter.setData(mList)
    }
}