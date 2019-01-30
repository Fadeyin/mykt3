package com.example.fadeyin.mykt3.models

object APIConfig {
    val BASE_URL: String = "http://90.150.146.248:8000/api/v1/"
    var token: String? = null
    }

object AuthInfo {
    var status: String? = null
    var message: String? = null
    lateinit var auth: Auth

}

object Auth{
    var token: String? = null
}

