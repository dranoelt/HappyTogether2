package com.example.happytogether2

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.room.Room
import com.example.happytogether2.Login.LoginFragment
import com.example.happytogether2.database.MyDBRoomHelper
import com.example.happytogether2.database.User
import com.example.happytogether2.database.UserDAO
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val db = Room.databaseBuilder(
            applicationContext,
            MyDBRoomHelper::class.java,
            "myData.db"
        ).build()

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<LoginFragment>(R.id.container)
            }
        }
    }

    fun insert(a: String, b: String, c: String, d: String) {
        var db = Room.databaseBuilder(
            applicationContext,
            MyDBRoomHelper::class.java,
            "myData.db"
        ).build()

        var hasil =""
        doAsync {
            db.userDao().insertAll(
                User(a, b, c, d)
            )
            for(allData in db.userDao().getAllData()){
                hasil += "${allData._id} ${allData.nama} ${allData.email} ${allData.password}\n"
            }
            uiThread {
                Log.w("Hasil",hasil)
            }
        }
    }

    fun check(a: String, b: String):Boolean {
        var db = Room.databaseBuilder(
            applicationContext,
            MyDBRoomHelper::class.java,
            "myData.db"
        ).allowMainThreadQueries()
            .build()

        if (db.userDao().checkId(a,b).count() == 1) {
            Toast.makeText(this,"Logic Success", Toast.LENGTH_SHORT).show()
            return true
        }
        else
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            return false
    }


}