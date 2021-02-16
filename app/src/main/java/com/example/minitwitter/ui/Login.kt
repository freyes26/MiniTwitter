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
import com.example.minitwitter.databinding.FragmentLoginBinding
import com.example.minitwitter.ui.dialog.ErrorDialog
import com.example.minitwitter.ui.dialog.WithOutInternetDialog
import com.example.minitwitter.viewModel.LoginViewModel


class Login : Fragment() {
    private lateinit var _binding : FragmentLoginBinding
    private val binding : FragmentLoginBinding get() = _binding
    private val loginViewModel : LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        _binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = loginViewModel
        makeApiCall()
        event()
    }

    private fun makeApiCall(){
        loginViewModel.loginAccess.observe(requireActivity(),{ value ->
            value?.let {
                doLogin(it)
            }
        })
    }

    private fun event(){
        binding.materialButton.setOnClickListener {
            onClickSingIn()
        }

        binding.register.setOnClickListener{
            onClickSingUo()
        }
    }

    private fun onClickSingUo(){
        val singUp = LoginDirections.actionLoginToSingUp()
        findNavController().navigate(singUp)
    }

    private fun onClickSingIn(){
        val username = binding.username.text.toString()
        val pws = binding.pws.text.toString()
        if (check(username) && check(pws)) {
            loginViewModel.userName.postValue(username)
            loginViewModel.pws.postValue(pws)
            loginViewModel.doLogin()
        }
        else{
            binding.textError.visibility = View.VISIBLE
            if(!check(username)) {
                setOnBlack(getString(R.string.required_email))
            }
            else {
                setOnBlack(getString(R.string.requeired_pws))
            }
        }

    }

    private fun doLogin(status : Int) {
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

    private fun setOnBlack(error: String){
        binding.textError.text = error
    }

    private fun check(value :  String) = value != ""

}