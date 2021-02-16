package com.example.minitwitter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minitwitter.Constants
import com.example.minitwitter.MiniTwitterApplication
import com.example.minitwitter.repository.ValidatorFactory
import com.example.minitwitter.repository.netWork.Connection
import com.example.minitwitter.repository.netWork.json.request.RequestLogin

class LoginViewModel  : ViewModel(){

    private val repository by lazy { ValidatorFactory() }
    var userName = MutableLiveData("")
    var pws = MutableLiveData("")
    private var _loginAccess : MutableLiveData<Int> = MutableLiveData(Constants.WITHUOTSESSION)
    val loginAccess : LiveData<Int> get() = _loginAccess


    fun doLogin(){
        val requestLogin = RequestLogin(userName.value!!, pws.value!!)
        if(Connection.isConnected(MiniTwitterApplication.context)){
            /*viewModelScope.launch {
                try {
                    val result = withContext(Dispatchers.IO) {
                        repository.getValidator().doLogin(requestLogin)
                    }
                    _loginAccess.postValue(when {
                        result != null -> {
                            Log.d("MINITWITTER", "${result.toString()}")
                            MiniTwitterApplication.SharePreferences.edit()
                                .putString(Constants.preferences.PREF_TOKEN, result.token)
                                .commit()
                            Constants.SUCCESS
                        }
                        else -> {
                            Log.d("MINITWITTER", "RespuestaVacia")
                            Constants.ERROR_REQUEST
                        }
                    })
                }catch (e : Throwable) {
                    _loginAccess.postValue(Constants.ERROR_REQUEST)
                }
            }*/
            _loginAccess.postValue(Constants.SUCCESS)
            MiniTwitterApplication.SharePreferences.edit()
                .putString(Constants.preferences.PREF_TOKEN, "12345678900987654321234567890")
                .commit()
        }
        else{
            _loginAccess.postValue(Constants.WITHOUT_INTERNET)
        }
    }
}