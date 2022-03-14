package com.example.studytest.model

object UserList: ArrayList<User>() {
    fun addUser(index: Int,name:String,age:Int) {
        UserList.add(index,User(name,age))
    }
    fun getUser(index:Int) = UserList[index]
    fun setUser(index: Int,user: User) {
        UserList[index] = user
    }
}