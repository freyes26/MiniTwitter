package com.example.minitwitter.repository.netWork

import com.example.minitwitter.Constants
import com.example.minitwitter.MiniTwitterApplication
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = MiniTwitterApplication.SharePreferences.getString(Constants.preferences.PREF_TOKEN, "")
        val request = chain.request()
            .newBuilder()
            .addHeader(Constants.retrofit.HEAD_AUTH, "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}