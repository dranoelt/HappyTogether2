package com.example.happytogether2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey val _id : String = "",
    @ColumnInfo(name = "C_NAME") var nama : String = "",
    @ColumnInfo(name = "C_EMAIL") var email : String = "",
    @ColumnInfo(name = "C_PASSWORD") var password : String = ""
)
