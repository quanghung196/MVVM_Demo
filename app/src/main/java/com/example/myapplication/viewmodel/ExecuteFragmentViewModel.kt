package com.example.myapplication.viewmodel

import android.app.Application

import android.content.ContentValues.TAG
import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.model.User
import kotlinx.coroutines.launch

class ExecuteFragmentViewModel() : ViewModel() {

    private val userRepository: UserRepository = UserRepository()

    fun insertUser(user: User) = viewModelScope.launch {
        userRepository.insertUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        userRepository.updateUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        userRepository.deleteUser(user)
    }

    fun getUserByID(userID: Int): User = userRepository.getUserByID(userID)


    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "onCleared: ")
    }
}