package com.example.myapplication.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.myapplication.data.dao.UserDAO
import com.example.myapplication.data.database.UserDatabase
import com.example.myapplication.model.User

class UserRepository() {

    private val userDAO: UserDAO

    init {
        val userDatabase: UserDatabase = UserDatabase.getInstance()
        userDAO = userDatabase.getUserDao()
    }

    suspend fun insertUser(user: User) = userDAO.insertUser(user)
    suspend fun updateUser(user: User) = userDAO.updateUser(user)
    suspend fun deleteUser(user: User) = userDAO.deleteUser(user)

    fun getAllUser(): LiveData<List<User>> = userDAO.getAllUser()
    fun getUserByID(userID: Int): User = userDAO.getUserByID(userID)
}