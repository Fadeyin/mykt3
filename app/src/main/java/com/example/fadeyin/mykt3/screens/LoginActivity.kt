package com.example.fadeyin.mykt3.screens

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.fadeyin.mykt3.screens.notice.noticeActivity
import com.example.fadeyin.mykt3.R
import com.example.fadeyin.mykt3.models.UserAPIinterface

class LoginActivity : AppCompatActivity() {

    private var editTextEmail: EditText? = null
    private var editTextPassword: EditText? = null
    private lateinit var loginBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.email) as EditText
        editTextPassword = findViewById(R.id.password) as EditText
        loginBtn = findViewById(R.id.btnLogin)
        val email = "faded@gmail.com"
        val password = "abcd1234"
        val first_name = "asd"
        val last_name = "asd"
        val patronymic = "asd"
        val position = "Proger"
        val about_me= "asd"
        val phone_number = 2333321322

        loginBtn.setOnClickListener {
            val email1 = editTextEmail?.text.toString()
            val password2 = editTextPassword?.text.toString()

            val apiService = UserAPIinterface.create()
            apiService.registration(email,password,first_name,last_name,patronymic,position,about_me,phone_number)


            val mainIntent = Intent(this,noticeActivity::class.java)
            startActivity(mainIntent)
        }
    }


}
