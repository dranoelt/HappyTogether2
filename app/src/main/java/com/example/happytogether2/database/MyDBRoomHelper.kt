package com.example.happytogether2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(User::class), version = 1)
abstract class MyDBRoomHelper: RoomDatabase() {
    abstract fun userDao() : UserDAO
//    abstract val userDao: UserDAO

//    companion object {
//
//        @Volatile
//        private var INSTANCE: MyDBRoomHelper? = null
//
//        fun getInstance(context: Context): MyDBRoomHelper {
//            synchronized(this) {
//                var instance = INSTANCE
//
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                            context.applicationContext,
//                            MyDBRoomHelper::class.java,
//                            "myData.db"
//                    )
//                            .fallbackToDestructiveMigration()
//                            .build()
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
//    }
}