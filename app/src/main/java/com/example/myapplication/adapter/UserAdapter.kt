package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemUserBinding
import com.example.myapplication.model.User
import com.example.myapplication.viewmodel.MainFragmentViewModel

class UserAdapter(val mainFragmentViewModel: MainFragmentViewModel) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var mUsers: List<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemUserBinding>(
            layoutInflater,
            R.layout.item_user,
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }

    fun submitList(users: List<User>) {
        mUsers = users
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        if (holder != null) {
            val currentUser = mUsers.get(position)
            holder.binding.user = currentUser
            holder.binding.viewModel = mainFragmentViewModel
            holder.binding.executePendingBindings()
        }
    }

    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
}