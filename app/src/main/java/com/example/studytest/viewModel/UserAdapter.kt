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
                private var currentUser:User?=null
                init {
                    itemView.setOnClickListener{
                        currentUser?.let{

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

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item,parent,false)
        return UserViewHolder(view,onclick)
    }
    object UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.userId == newItem.userId
        }
    }
 }

