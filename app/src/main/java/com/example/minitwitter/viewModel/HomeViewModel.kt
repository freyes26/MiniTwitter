package com.example.minitwitter.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minitwitter.Constants
import com.example.minitwitter.MiniTwitterApplication

class HomeViewModel : ViewModel(){
    val username = MutableLiveData(MiniTwitterApplication.SharePreferences.getString(Constants.preferences.PREF_TOKEN, ""))

}