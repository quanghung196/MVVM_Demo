package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.model.User
import kotlinx.coroutines.launch

class ExecuteFragmentViewModel : ViewModel() {

    private val mUserRepository: UserRepository = UserRepository()

    fun insertUser(user: User) = viewModelScope.launch {
        mUserRepository.insertUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        mUserRepository.updateUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        mUserRepository.deleteUser(user)
    }

    fun getUserByID(userID: Int): User = mUserRepository.getUserByID(userID)
}