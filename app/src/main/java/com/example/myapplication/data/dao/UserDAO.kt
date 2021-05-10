package com.example.myapplication.data.dao

import androidx.lifecycle.LiveData

import androidx.room.*
import com.example.myapplication.model.User


@Dao
interface UserDAO {

    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("select * from user_table")
    fun getAllUser(): LiveData<List<User>>

    @Query("select * from user_table where user_id_col=:userID")
    fun getUserByID(userID: Int): User

    @Query("delete from user_table")
    suspend fun deleteAllUser()

    @Insert
    suspend fun synchronizeAllUserFromServer(users: List<User>)
}