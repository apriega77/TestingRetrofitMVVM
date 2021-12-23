package com.apriega77.moviedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apriega77.moviedb.R
import com.apriega77.moviedb.data.model.User
import kotlinx.android.synthetic.main.rv_user.view.*


class UserAdapter(val clickListener: onItemClickListener) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    var userList = mutableListOf<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =LayoutInflater.from(parent.context).inflate(R.layout.rv_user, parent,false )


        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(userList[position])
        holder.itemView.setOnClickListener {
            clickListener.onItemClick(userList[position])
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val tvName = view.tvName
        val tvEmail = view.tvEmail
        val tvStatus = view.tvStatus


        fun bind(data : User) {
            tvName.text = data.name
            tvEmail.text = data.email
            tvStatus.text = data.status


        }

    }

    interface onItemClickListener{
        fun onItemClick(user : User)
    }
}