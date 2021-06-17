package com.example.happytogether2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.happytogether2.database.History
import com.example.happytogether2.database.HistoryTransaction
import com.example.happytogether2.databinding.ActivityPreLoadBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PreLoad : AppCompatActivity() {

    private lateinit var binding: ActivityPreLoadBinding
    private var order = listOf(
        History("House", "25-02-2019", "Minimalist House"),
        History("Paris House", "02-03-2020", "Luxury with Black Interior"),     History("Paris Clear House", "05-03-2021", "Minimalist and White"),
        History("Hawaian House", "18-05-2021", "Colorful"),
        History("Private House", "21-06-2021", "Just White and Black"),
        History("House of Forest", "03-06-2021", "Forest Theme"),
        History("Dream Place", "15-11-2021", "Minimalist"),
        History("Blast", "16-12-2021", "Up To You")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreLoadBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        executeLoadData()
    }

    private fun executeLoadData() {
        var myProgress = binding.progressBar
        myProgress.progress = 0
        var database = HistoryTransaction(this@PreLoad)
        doAsync {
            database.beginHistoryTransaction()
            for (meet in order) {
                database.addHistoryTransaction(meet)
                uiThread {
                    myProgress.progress += myProgress.max/order.size
                    Log.w("Progress", "${myProgress.progress}")
                }
            }
            database.successHistoryTransaction()
            database.endHistoryTransaction()
            uiThread {
                var intent = Intent(this@PreLoad, MainActivity::class.java)
                startActivity(intent)
//                this@PreLoad.finish()
            }
        }
    }

}