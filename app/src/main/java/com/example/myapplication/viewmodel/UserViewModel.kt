package com.example.myapplication.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.interfaces.iMainFragment
import com.example.myapplication.model.User
import kotlinx.coroutines.launch


class UserViewModel() : ViewModel() {
    private val userRepository: UserRepository = UserRepository()


    private lateinit var iMainFragment: iMainFragment

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

    fun setiMainFragment(iMainFragment: iMainFragment) {
        this.iMainFragment = iMainFragment
    }


    fun onUserClicked(user: User) {
        iMainFragment.onUserClicked(user)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "onCleared: ")
    }
}