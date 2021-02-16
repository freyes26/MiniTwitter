package com.example.minitwitter.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minitwitter.BR
import com.example.minitwitter.R
import com.example.minitwitter.databinding.FragmentHomeBinding
import com.example.minitwitter.repository.netWork.json.Twit
import com.example.minitwitter.ui.adapter.HomeItemAdapter
import com.example.minitwitter.ui.adapter.TwitListener
import com.example.minitwitter.viewModel.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var _binding : FragmentHomeBinding
    val binding : FragmentHomeBinding get() = _binding
    val homeViewModel : HomeViewModel by viewModels()
    private lateinit var adapter: HomeItemAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        adapter =  HomeItemAdapter(TwitListener { twit -> mylistener(twit) })
        _binding.lifecycleOwner = viewLifecycleOwner
        makeApiCall()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel, homeViewModel)
        binding.executePendingBindings()
        binding.homeRecyclerview.adapter = adapter
        binding.homeRecyclerview.layoutManager= LinearLayoutManager(requireContext())
        binding.homeRecyclerview.setHasFixedSize(false)
        binding.viewModel = homeViewModel
    }

    fun makeApiCall(){
        homeViewModel.username.observe(requireActivity(), {
            it.let {
                Log.d("MINITWITTER", "CAMBIO EL NOMBRE")
            }
        })
    }

    fun mylistener(twit: Twit){

    }



}