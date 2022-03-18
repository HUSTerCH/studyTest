package com.example.studytest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import com.example.studytest.R
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.example.studytest.model.User
import com.example.studytest.viewModel.Logic
import com.example.studytest.viewModel.LogicFactory
import com.example.studytest.viewModel.UserAdapter

class MainActivity : AppCompatActivity() {
    var i:Int = 0
    private val userListViewModel by viewModels<Logic> {
        LogicFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userAdapter = UserAdapter { user -> adapterOnclick(user)  }
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = userAdapter
        userListViewModel.userLiveData.observe(this) {
            it?.let {
                userAdapter.submitList(it as MutableList<User>)
            }
        }
    }

    fun click(itemId:Int) {
        when(itemId) {
            R.id.action_edit-> {}
            R.id.action_share ->{
                val intent = Intent(this,EditActivity::class.java)
                startActivity(intent)
            }
            R.id.action_settings-> Toast.makeText(this,"Settings", Toast.LENGTH_SHORT).show()
        }
    }

    fun adapterOnclick (user:User) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}