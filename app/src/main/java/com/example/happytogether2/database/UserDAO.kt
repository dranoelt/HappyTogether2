package com.example.happytogether2.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDAO {
    @Query("SELECT * FROM User")
    fun getAllData() : List<User>
    @Insert
    fun insertAll(vararg user: User)
    @Query("DELETE FROM User")
    fun deleteAll()
    @Query("DELETE FROM User WHERE _id = :username")
    fun deleteById(username: String)
    @Query("SELECT * FROM User WHERE _id = :username AND C_PASSWORD = :password")
    fun checkId(username: String, password: String): List<User>

}