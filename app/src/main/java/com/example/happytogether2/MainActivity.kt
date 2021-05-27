package com.example.happytogether2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.room.Room
import com.example.happytogether2.Login.EXTRA_DATA
import com.example.happytogether2.database.MyDBRoomHelper
import com.example.happytogether2.ui.HomeFragment
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            var d = intent.getStringExtra(EXTRA_DATA)
            val transaction = supportFragmentManager.beginTransaction()
            val myBundle= Bundle()
            myBundle.putString(EXTRA_DATA, d)
            val homeFragment = HomeFragment.newInstance()
            homeFragment.arguments = myBundle
            transaction.add(R.id.container, homeFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    fun del(a: String) {
        var db = Room.databaseBuilder(
            applicationContext,
            MyDBRoomHelper::class.java,
            "myData.db"
        ).build()
        doAsync {
            db.userDao().deleteById(a)
        }

        var intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Account Deleted", Toast.LENGTH_SHORT).show()
    }
}