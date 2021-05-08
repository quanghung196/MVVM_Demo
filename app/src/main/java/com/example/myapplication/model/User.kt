package com.example.myapplication.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_table")
data class User(
    @ColumnInfo(name = "user_name_col") var userName: String = "",
    @ColumnInfo(name = "user_email_col") var userEmail: String = "",
    @ColumnInfo(name = "user_age_col") var userAge: Int = 0
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id_col")
    var userID: Int = 0
}
