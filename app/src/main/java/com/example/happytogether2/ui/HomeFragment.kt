package com.example.happytogether2.ui

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.happytogether2.Login.EXTRA_DATA
import com.example.happytogether2.MainActivity
import com.example.happytogether2.R
import com.example.happytogether2.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: FragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.userName.text = arguments?.getString(EXTRA_DATA)
        var user_name = binding.userName.text
        binding.deleteAcc.setOnClickListener {
            (activity as MainActivity).del(user_name.toString())
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

