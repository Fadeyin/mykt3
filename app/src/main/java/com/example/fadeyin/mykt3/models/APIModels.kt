package com.example.fadeyin.mykt3.models

class UserInfoResponse(val data: user)

class TokenResponse(
    val status : String,
    val auth : token,
    val data: usernameresp)

data class token (
    val token: String?
)

data class usernameresp(
    val id: String,
    val username: String?
)

data class user(
    val id: Int,
    val email: String,
    val password: String,
    val first_name: String,
    val last_name: String,
    val patronymic: String,
    val position: String,
    val about_me: String,
    val phone_number: Long?
)
