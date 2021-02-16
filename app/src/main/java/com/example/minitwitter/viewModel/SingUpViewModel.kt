package com.example.minitwitter.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minitwitter.Constants
import com.example.minitwitter.MiniTwitterApplication
import com.example.minitwitter.repository.ValidatorFactory
import com.example.minitwitter.repository.netWork.Connection
import com.example.minitwitter.repository.netWork.json.request.RequestSingUp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SingUpViewModel : ViewModel() {
    private val repository by lazy { ValidatorFactory() }
    var userName = MutableLiveData("")
    var pws = MutableLiveData("")
    var emal = MutableLiveData("")
    private var _accessLogin  = MutableLiveData(Constants.WITHUOTSESSION)
    val accessLogin : LiveData<Int> get() = _accessLogin


    fun doSingUp(){
        val requestSingUp = RequestSingUp(userName.value!!, emal.value!!,pws.value!!,Constants.retrofit.TOKEN)
        if(Connection.isConnected(MiniTwitterApplication.context)){
            viewModelScope.launch(){
                try{
                    val result =  withContext(Dispatchers.IO){
                        repository.getValidator().doSingup(requestSingUp)
                    }
                    _accessLogin.postValue(when  {
                        result != null -> {
                            Log.d("MINITWITTER", result.username)
                            Constants.SUCCESS
                        }
                        else ->{
                            Log.d("MINITWITTER", "Error")
                            Constants.ERROR_REQUEST
                        }
                    })
                }
                catch (cause : Throwable ){
                    Log.d("MINITWITTER", cause.message.toString())
                    _accessLogin.postValue(Constants.ERROR_REQUEST)
                }
            }
        }
        else{
            _accessLogin.postValue(Constants.WITHOUT_INTERNET)
        }
    }
}