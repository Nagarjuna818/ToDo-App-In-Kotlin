package com.example.todoapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.activities.DetailsActivity
import com.example.todoapp.activities.EditActivity
import com.example.todoapp.database.DbHelper
import com.example.todoapp.models.ToDo
import kotlinx.android.synthetic.main.row_todo_activity.view.*

class ToDoAdapter(var mContext: Context): RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    var mList: ArrayList<ToDo> = ArrayList()
    lateinit var dbHelper: DbHelper

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(dailyTask: ToDo){

            dbHelper = DbHelper(mContext)
            itemView.text_view_title.text = dailyTask.title
            itemView.button_update.setOnClickListener{
                var intent = Intent(mContext, EditActivity::class.java)
                mContext.startActivity(intent)
            }

            itemView.button_delete.setOnClickListener{

                var id = dailyTask.id

                dbHelper.deleteTask(id)
                mList = dbHelper.getAllTasks()
                notifyDataSetChanged()
                Toast.makeText(mContext,"Deleted",Toast.LENGTH_LONG).show()

            }
            itemView.text_view_title.setOnClickListener{
                var intent = Intent(mContext, DetailsActivity::class.java)
                intent.putExtra("TITLE",dailyTask.title)
                intent.putExtra("DESCRIPTION",dailyTask.description)
                mContext.startActivity(intent)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_todo_activity,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var dailyTask = mList[position]
        holder.bind(dailyTask)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(list: ArrayList<ToDo>){
        mList = list
        notifyDataSetChanged()
    }

}
