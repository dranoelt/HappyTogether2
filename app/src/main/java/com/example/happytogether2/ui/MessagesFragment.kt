package com.example.happytogether2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.happytogether2.R
import com.example.happytogether2.databinding.FragmentCartBinding
import com.example.happytogether2.databinding.FragmentMessagesBinding

class MessagesFragment : Fragment() {

    private lateinit var binding: FragmentMessagesBinding

    private lateinit var viewModel : FragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMessagesBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }
}