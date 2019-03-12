package com.example.fadeyin.mykt3.models

import java.util.regex.Matcher
import java.util.regex.Pattern

object APIConfig {
    val BASE_URL: String = "http://90.150.146.248:8000/api/v1/"
    val BASE_URL2: String = "http://90.150.146.248:8002/api/v1/"
    var token: String? = null
    var X_Csrf_Token: String? = null
    var key: String? = null
    var data = arrayOf<String>("1","2","3","1","2","3")
    }

object AuthInfo {
    var status: String? = null
    var message: String? = null
    lateinit var auth: Auth

}

object Auth{
    var token: String? = null
}

fun emailValidator(email: String): Boolean {
    val pattern: Pattern
    val matcher: Matcher
    val EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    pattern = Pattern.compile(EMAIL_PATTERN)
    matcher = pattern.matcher(email)
    return matcher.matches()
}