package com.example.minitwitter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.minitwitter.Constants
import com.example.minitwitter.R
import com.example.minitwitter.databinding.FragmentSingUpBinding
import com.example.minitwitter.ui.dialog.ErrorDialog
import com.example.minitwitter.ui.dialog.WithOutInternetDialog
import com.example.minitwitter.viewModel.SingUpViewModel


class SingUp : Fragment() {
    private lateinit var _binding : FragmentSingUpBinding
    private val binding : FragmentSingUpBinding get() = _binding

    private val singupViewModel : SingUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_sing_up,null,false)
        _binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        makeApiCall()
        event()
    }

    private fun makeApiCall(){
        singupViewModel.accessLogin.observe(requireActivity(),{ value ->
            value?.let {
                createAcount(it)
            }
        })
    }

    fun event(){
        binding.materialButton.setOnClickListener {
            validateacount()
        }

        binding.login.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    fun validateacount(){
        val user = binding.username.text.toString()
        val pws = binding.pws.text.toString()
        val emal = binding.email.text.toString()
        if (checkBlanck(user) && checkBlanck(pws) && checkBlanck(emal)){
            singupViewModel.userName.postValue(user)
            singupViewModel.pws.postValue(pws)
            singupViewModel.emal.postValue(emal)
            singupViewModel.doSingUp()
        }
        else {
            binding.textError.visibility = View.VISIBLE
            if(!checkBlanck(user)){
                binding.textError.text = getString(R.string.requeired_username)
            }
            else if(!checkBlanck(emal)){
                binding.textError.text = getString(R.string.required_email)
            }
            else{
                binding.textError.text = getString(R.string.requeired_pws)
            }
        }

    }

    fun createAcount(status : Int){
        if (status != Constants.WITHUOTSESSION) {
            when (status) {
                Constants.SUCCESS -> {
                    val action = LoginDirections.actionLoginToHomeMinitwitter()
                    findNavController().navigate(action)
                    activity?.finish()
                }
                Constants.WITHOUT_INTERNET -> {
                    val dialog = WithOutInternetDialog()
                    dialog.show(requireActivity().supportFragmentManager, Constants.DIALOG_TAG)

                }
                else -> {
                    val dialog = ErrorDialog()
                    dialog.show(requireActivity().supportFragmentManager, Constants.DIALOG_TAG)
                }
            }
        }
    }

    fun checkBlanck(value :String) = value != ""
}