package com.example.studytest.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.studytest.R
import com.example.studytest.databinding.ActivityMainBinding
import com.example.studytest.model.UserList
import com.example.studytest.viewModel.Logic
import com.example.studytest.viewModel.UserAdapter

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding:ActivityMainBinding
    var i:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        mainBinding.logic = Logic
        mainBinding.userList = UserList
        initUser()
        val adapter = UserAdapter(this,R.layout.user_item,UserList)
        mainBinding.listView.adapter = adapter
        mainBinding.mainToolbar.inflateMenu(R.menu.menu_main)
        mainBinding.mainToolbar.setOnMenuItemClickListener {item->
            click(item.itemId)
            true
        }
    }
    fun initUser() {
        Logic.addUser()
    }
    fun click(itemId:Int) {
        when(itemId) {
            R.id.action_edit-> Logic.addNew()
            R.id.action_share-> Toast.makeText(this,"share", Toast.LENGTH_SHORT).show()
            R.id.action_settings-> Toast.makeText(this,"Settings", Toast.LENGTH_SHORT).show()
        }
    }
}