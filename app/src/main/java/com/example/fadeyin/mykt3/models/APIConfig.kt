package com.example.fadeyin.mykt3.models

object APIConfig {
    val BASE_URL: String = "http://90.150.146.248:8000/api/v1/"
    var token: String = "no auth"
    }

object AuthInfo {
    var status: String? = null
    var message: String? = null
    var auth: Auth? = null

}

object Auth{
    var token: String? = null
}

