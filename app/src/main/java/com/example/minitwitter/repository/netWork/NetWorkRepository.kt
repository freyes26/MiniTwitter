package com.example.minitwitter.repository.netWork

import com.example.minitwitter.MiniTwitterApplication
import com.example.minitwitter.repository.Repository
import com.example.minitwitter.repository.netWork.json.request.RequestLogin
import com.example.minitwitter.repository.netWork.json.request.RequestSingUp
import com.example.minitwitter.repository.netWork.json.response.ResponseAuth

class NetWorkRepository() : Repository {
    suspend fun doLogin(requestLogin: RequestLogin): ResponseAuth? {
        return MiniTwitterApplication.application.minitwitterConnection.doLogin(requestLogin)
    }
    suspend fun doSingup(requestSingUp: RequestSingUp) : ResponseAuth?{
        return MiniTwitterApplication.application.minitwitterConnection.doSingUp((requestSingUp))
    }
}