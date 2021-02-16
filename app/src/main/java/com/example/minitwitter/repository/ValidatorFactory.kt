package com.example.minitwitter.repository

import com.example.minitwitter.repository.netWork.NetWorkRepository

class ValidatorFactory {
    fun getValidator() = NetWorkRepository()
}