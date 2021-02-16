package com.example.minitwitter

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.minitwitter.repository.netWork.ApiRetrofit
import com.example.minitwitter.repository.netWork.RestApi
import com.facebook.stetho.Stetho
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MiniTwitterApplication : Application(){

    companion object{
        lateinit var application : MiniTwitterApplication
        lateinit var context : Context
        lateinit var SharePreferences: SharedPreferences
    }

    private val retrofitConfig : Retrofit by lazy { Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.retrofit.URL)
        .build()
    }

    val minitwitterConnection by lazy { ApiRetrofit(application.retrofitConfig).create(RestApi::class.java) }

    override fun onCreate() {
        super.onCreate()
        application = this
        context =  this.applicationContext
        SharePreferences = getSharedPreferences(Constants.SHAREDPREFERENCES, Context.MODE_PRIVATE)
        Stetho.initializeWithDefaults(this)
    }
}