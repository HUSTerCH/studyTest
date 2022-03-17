package com.example.studytest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.studytest.R
import com.example.studytest.databinding.ActivityMainBinding
import com.example.studytest.viewModel.Logic
import com.example.studytest.viewModel.UserAdapter

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding:ActivityMainBinding
    var i:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        mainBinding.logic = Logic
        val
        mainBinding.recycleView.adapter = adapter
        mainBinding.mainToolbar.inflateMenu(R.menu.menu_main)
        mainBinding.mainToolbar.setOnMenuItemClickListener {item->
            click(item.itemId)
            true
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
}