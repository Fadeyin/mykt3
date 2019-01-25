package com.example.fadeyin.mykt3.models

    data class Users(
        val id: Int,
        val email: String,
        val password: String,
        val first_name: String,
        val last_name: String,
        val patronymic: String,
        val position: String,
        val about_me: String,
        val phone_number: String
    )

    data class Result (val total_count: Int, val incomplete_results: Boolean, val items: List<Users>)
