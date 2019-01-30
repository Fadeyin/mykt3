package com.example.fadeyin.mykt3.models

data class ResultLogin (
    val status : String,
    val auth : Token,
    val message: String,
    val items: Data
)

data class Token (
    val token: String
)

data class Data(
    val id: String,
    val username: String
)

data class ResultNotice (
    val pk: Int,
    val title: String,
    val description: String,
    val author: String,
    val time_publication: String,
    val hide: Boolean
)