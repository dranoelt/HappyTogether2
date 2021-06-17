package com.example.happytogether2

import android.content.Intent
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.room.Room
import com.example.happytogether2.Login.EXTRA_DATA
import com.example.happytogether2.database.MyDBRoomHelper
import com.example.happytogether2.database.User
import com.example.happytogether2.database.UserDAO
import com.example.happytogether2.databinding.ActivityMainBinding
import com.example.happytogether2.ui.CartFragment
import com.example.happytogether2.ui.HomeFragment
import com.example.happytogether2.ui.MessagesFragment
import com.example.happytogether2.ui.ProfileFragment
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.anko.doAsync


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                var d = intent.getStringExtra(EXTRA_DATA)
                val myBundle= Bundle()
                myBundle.putString(EXTRA_DATA, d)
                setReorderingAllowed(true)
                add<HomeFragment>(R.id.container, args = myBundle)
            }
        }
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener)
    }

    private val mNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item -> when (item.itemId) {
        R.id.nav_home -> {
            val homeFragment = HomeFragment()
            openFragment(homeFragment)
            return@OnNavigationItemSelectedListener true
        }
        R.id.nav_cart -> {
            val cartFragment = CartFragment()
            openFragment(cartFragment)
            return@OnNavigationItemSelectedListener true
        }
        R.id.nav_messages -> {
            val messagesFragment = MessagesFragment()
            openFragment(messagesFragment)
            return@OnNavigationItemSelectedListener true
        }
        R.id.nav_profile -> {
            val profileFragment = ProfileFragment()
            openFragment(profileFragment)
            return@OnNavigationItemSelectedListener true
        }
        }
        false
    }

    private fun openFragment(fragment: Fragment) =
            supportFragmentManager.beginTransaction().apply {
                var d = intent.getStringExtra(EXTRA_DATA)
                val myBundle= Bundle()
                myBundle.putString(EXTRA_DATA, d)
                fragment.arguments = myBundle
                replace(R.id.container, fragment)
                addToBackStack(null)
                commit()
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