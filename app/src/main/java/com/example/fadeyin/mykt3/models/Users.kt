package com.example.fadeyin.mykt3.models

    data class Users(
        val id: Int,
        val email: String,
        val first_name: String,
        val last_name: String,
        val patronymic: String,
        val position: String,
        val about_me: String,
        val phone_number: String,
        val date_of_birthh: String,
        val resident: String
    )

    data class Result (val items: List<Users>)
