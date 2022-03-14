package com.example.studytest.viewModel

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.studytest.R
import com.example.studytest.model.User

class UserAdapter(activity:Activity,val resourceId:Int,data:ArrayList<User>):
    ArrayAdapter<User>(activity, resourceId,data) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceId,parent,false)
        val userName:TextView = view.findViewById(R.id.view_Content)
        val userAge:TextView = view.findViewById(R.id.view_Title)
        val userItem = getItem(position)
        if (userItem != null) {
            userName.text = userItem.userName
            userAge.text = userItem.userAge.toString()
        }
        return view
    }
}