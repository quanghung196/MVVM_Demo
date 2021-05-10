package com.example.myapplication.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.ui.listener.UserListener
import com.example.myapplication.model.User
import kotlinx.coroutines.launch


class UserViewModel() : ViewModel() {
    private val userRepository: UserRepository = UserRepository()


    private lateinit var userListener: UserListener

    fun insertUser(user: User) = viewModelScope.launch {
        userRepository.insertUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        userRepository.updateUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        userRepository.deleteUser(user)
    }

    fun getAllUser(): LiveData<List<User>> = userRepository.getAllUser()

    fun getUserByID(userID: Int): User = userRepository.getUserByID(userID)


    fun setUserListener(userListener: UserListener) {
        this.userListener = userListener
    }


    fun onUserClicked(user: User) {
        userListener  .onUserClicked(user)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "onCleared: ")
    }
}