package com.example.minitwitter.repository.netWork

import android.content.Context
import android.net.ConnectivityManager

object Connection {
    fun isConnected(context: Context) :  Boolean{
        val connectivityManager  =  context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork  = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

}