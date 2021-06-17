package com.example.happytogether2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.happytogether2.database.History
import com.example.happytogether2.database.HistoryTransaction
import com.example.happytogether2.databinding.FragmentCartBinding
import com.example.happytogether2.rv.RecyclerViewAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    private lateinit var viewModel: FragmentViewModel

    private var myDB: HistoryTransaction? = null

    val test: ArrayList<String> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCartBinding.inflate(inflater,container, false)
        context ?: return binding.root

        addTest()
        updateAdapter()
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentViewModel::class.java)



    }

    fun addTest() {
        test.add("dog")
        test.add("cat")
        test.add("owl")
        test.add("cheetah")
        test.add("raccoon")
        test.add("bird")
        test.add("snake")
        test.add("lizard")
        test.add("hamster")
    }

    private fun updateAdapter() {
        doAsync {
            var myRecycler = binding.recyclerView
            myRecycler.layoutManager = LinearLayoutManager(this@CartFragment.context)
            myDB = HistoryTransaction(this@CartFragment.requireContext())
            var allHistory: List<History> = myDB!!.viewAllHistory()
            uiThread {
                if (allHistory.size > 0) {
                    myRecycler.adapter = RecyclerViewAdapter(allHistory)
                } else {
                    Toast.makeText(this@CartFragment.activity, "There is no history in the database. Start adding now", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}