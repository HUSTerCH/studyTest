# RecyclerView学习笔记

recyclerView是在listView上更强大的滚动控件，学习listView所写demo地址：

[StudyTest]: https://github.com/HUSTerCH/studyTest

该demo结合了viewModel的设计思想，主要包含了data（数据层）、adapter以及在activity中实现，主要代码如下：

## 数据层：

User.kt

```kotlin
package com.example.studytest.model


data class User(
    val userName:String,
    val userAge:Int,
    val userId:Long)

```

UserList.kt

```kotlin
package com.example.studytest.model

import android.content.res.Resources
import com.example.studytest.R

fun userList(resources:Resources): List<User> {
    return listOf(
        User(
            userName = resources.getString(R.string.user_name1),
            userAge = 10,
            userId = 20210001
        ),
        User(
            userName = resources.getString(R.string.user_name2),
            userAge = 11,
            userId = 20210002
        ),
        User(
            userName = resources.getString(R.string.user_name3),
            userAge = 12,
            userId = 20210003
        ),
        User(
            userName = resources.getString(R.string.user_name4),
            userAge = 13,
            userId = 20210004
        ),
        User(
            userName = resources.getString(R.string.user_name5),
            userAge = 14,
            userId = 20210005
        ),
        User(
            userName = resources.getString(R.string.user_name6),
            userAge = 15,
            userId = 20210006
        ),
        User(
            userName = resources.getString(R.string.user_name7),
            userAge = 16,
            userId = 20210007
        ),
        User(
            userName = resources.getString(R.string.user_name8),
            userAge = 17,
            userId = 20210008
        ),
        User(
            userName = resources.getString(R.string.user_name10),
            userAge = 18,
            userId = 20210009
        ),
        User(
            userName = resources.getString(R.string.user_name1),
            userAge = 19,
            userId = 20210010
        ),
        User(
            userName = resources.getString(R.string.user_name2),
            userAge = 20,
            userId = 20210011
        ),
        User(
            userName = resources.getString(R.string.user_name10),
            userAge = 18,
            userId = 20210009
        ),
        User(
            userName = resources.getString(R.string.user_name1),
            userAge = 19,
            userId = 20210010
        ),
        User(
            userName = resources.getString(R.string.user_name2),
            userAge = 20,
            userId = 20210011
        ),

    )
}
```

Data.kt

```kotlin
package com.example.studytest.model

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Data(resources: Resources) {
    private val initUserList = userList(resources)
    private val usersLivedata = MutableLiveData(initUserList)

    fun addUser(user: User) {
        val currentUserList = usersLivedata.value
        if (currentUserList != null) {
            val updatedList = currentUserList.toMutableList()
            updatedList.add(0,user)
            usersLivedata.postValue(updatedList)
        } else {
            usersLivedata.postValue(listOf(user))
        }
    }

    fun removeUser(user: User) {
        val currentList = usersLivedata.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(user)
            usersLivedata.postValue(updatedList)
        }
    }
    fun getUserList():LiveData<List<User>>{
        return usersLivedata
    }

    companion object {
        private var INSTANCE:Data? = null
        fun getData(resources: Resources):Data {
            return synchronized(Data::class) {
                val newInstance = INSTANCE ?:Data(resources)
                INSTANCE = newInstance
                newInstance

            }
        }
    }
}
```

## adapter和viewModel层：

Logic.kt

```kotlin
package com.example.studytest.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studytest.model.Data
import javax.sql.DataSource

class Logic(val data: Data) : ViewModel() {
    val userLiveData = data.getUserList()

    fun insertFlower() {

    }
}


class LogicFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Logic::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return Logic(
                data = Data.getData(context.resources)
            ) as T
        }
    throw IllegalArgumentException("UNknown")
    }
}

```

UserAdapter.kt

```kotlin
package com.example.studytest.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studytest.R
import com.example.studytest.model.User

class UserAdapter(private val onclick: (User) -> Unit) :
    ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback) {

            class UserViewHolder(itemView: View, val onClick: (User) -> Unit):
            RecyclerView.ViewHolder(itemView) {
                private val userNameText:TextView = itemView.findViewById(R.id.view_userName)
                private val userAgeText:TextView = itemView.findViewById(R.id.view_userAge)
                private val userIDText:TextView = itemView.findViewById(R.id.view_userID)
                private var currentUser:User? = null
                init {
                    itemView.setOnClickListener{
                        currentUser?.let{
                            onClick(it)
                        }
                    }
                }
                fun bind(user:User) {
                    currentUser = user
                    userNameText.text = user.userName
                    userAgeText.text = user.userAge.toString()
                    userIDText.text = user.userId.toString()
                }

            }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item,parent,false)
        return UserViewHolder(view, onclick)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

 }
object UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.userId == newItem.userId
    }
}


```

## view层（在activity中实现）

MainActivity.kt

```kotlin
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
        val mainToolbar: Toolbar = findViewById(R.id.main_toolbar)
        recyclerView.adapter = userAdapter
        mainToolbar.inflateMenu(R.menu.menu_main)
        mainToolbar.setOnMenuItemClickListener {item->
            click(item.itemId)
            true
        }
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
```

view层的xml文件：

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
<androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_marginTop="52dp"
        app:layoutManager="LinearLayoutManager"
        tools:layout_editor_absoluteX="0dp" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

个人理解很重要的点：

1.在xml文件中的

```kotlin
app:layoutManager="LinearLayoutManager"
```

在网上查到得知这是一个用于管理子元素排列方式的方法，主要有LinearLayoutManager、GridLayoutManager和StaggeredGridLayoutManager三种排列方式

2.adapter作为沟通view层和数据层的桥梁，其重要性不言而喻

2.1 在adapter类中，打头有如下代码：

```kotlin
class UserAdapter(private val onclick: (User) -> Unit) :
    ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback) {

            class UserViewHolder(itemView: View, val onClick: (User) -> Unit):
        RecyclerView.ViewHolder(itemView) {
```

外部类UserAdapter实现构造方法onclick，为后续加入子项的点击事件准备，继承自ListAdapter

内部还有一个viewHolder类，用于recyclerView滚动的时候快速设置值，而不必每次都重新创建很多对象，继承自recyclerView，在其内部对布局进行绑定和赋值。

2.2 UserAdapter继承自ListAdapter,其内部对onCreateViewHolder和onBindView两个函数进行重写，这两个函数是实现recyclerView的关键:

```kotlin
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item,parent,false)
        return UserViewHolder(view, onclick)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }
```

2.3 在UserAdapter中，还有一个object：

```kotlin
object UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.userId == newItem.userId
    }
}
```

在网上查阅得知这个类是用于判断列表重建时时候和之前的列表一样，如果一样就直接返回旧的，反之则进行更新

DiffCallback和viewHolder都是用于提升recyclerView的效率的

3.数据

此demo的model层分为了user、userList、data三个类，其中user只是一个数据类，userList初始化了一部分数据，data则提供了对数据进行增删改查的方法，在data类中，还加入了livedata的使用，并且还将data单例化

由于本demo并没有加入增删改查以及详细编辑的功能，所以logic类中并没有构造方法