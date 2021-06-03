package com.example.happytogether2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.happytogether2.databinding.FragmentCartBinding
import com.example.happytogether2.rv.RecyclerViewAdapter


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    private lateinit var viewModel: FragmentViewModel

    val test: ArrayList<String> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCartBinding.inflate(inflater,container, false)
        context ?: return binding.root

        addTest()
        binding.recyclerView.layoutManager = LinearLayoutManager(this?.activity)
        binding.recyclerView.adapter = RecyclerViewAdapter(test, this.requireActivity())

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentViewModel::class.java)
        // TODO: Use the ViewModel
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
}