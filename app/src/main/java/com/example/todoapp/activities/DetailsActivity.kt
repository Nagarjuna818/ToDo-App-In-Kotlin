package com.example.todoapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoapp.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var title = intent.getStringExtra("TITLE")
        var description = intent.getStringExtra("DESCRIPTION")

        text_view_title.text = title
        text_view_description.text  = description

        init()
    }

    private fun init() {
        button_back.setOnClickListener{
            var intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
    }



}