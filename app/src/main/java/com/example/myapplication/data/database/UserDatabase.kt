package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.utils.MyApp
import com.example.myapplication.data.dao.UserDAO
import com.example.myapplication.model.User


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao() :UserDAO

    companion object{
        @Volatile
        private var instance : UserDatabase? = null

        fun getInstance():UserDatabase{
            if (instance == null){
                instance = Room.databaseBuilder(MyApp.getInstance(), UserDatabase::class.java,"UserDatabase").build()
            }
            return instance!!
        }
    }
}