package com.example.minitwitter.repository.netWork

import com.example.minitwitter.repository.netWork.json.request.RequestLogin
import com.example.minitwitter.repository.netWork.json.request.RequestSingUp
import com.example.minitwitter.repository.netWork.json.response.ResponseAuth
import retrofit2.http.Body
import retrofit2.http.POST


interface RestApi {
    @POST("auth/login")
    suspend fun doLogin(@Body requestLogin: RequestLogin): ResponseAuth?

    @POST("auth/signup")
    suspend fun doSingUp(@Body requestSingUp: RequestSingUp) : ResponseAuth?
}