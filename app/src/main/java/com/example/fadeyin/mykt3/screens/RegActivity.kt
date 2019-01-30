package com.example.fadeyin.mykt3.screens

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.fadeyin.mykt3.R
import com.example.fadeyin.mykt3.models.UserAPIinterface
import com.example.fadeyin.mykt3.screens.notice.noticeActivity
import io.reactivex.schedulers.Schedulers

class RegActivity : AppCompatActivity() {
    private var editTextEmail: EditText? = null
    private var editTextPassword: EditText? = null
    private lateinit var regBtn : Button
    private var editfirst_name: EditText? = null
    private var editlast_name: EditText? = null
    private var editpatronymic: EditText? = null
    private var editposition: EditText? = null
    private var editphone_number: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        editTextEmail = findViewById(R.id.emailReg) as EditText
        editTextPassword = findViewById(R.id.passwordReg) as EditText
        editfirst_name = findViewById(R.id.first_name) as EditText
        editlast_name = findViewById(R.id.last_name) as EditText
        editpatronymic = findViewById(R.id.patronymic) as EditText
        editposition = findViewById(R.id.position) as EditText
        editphone_number = findViewById(R.id.phone_number) as EditText
        regBtn = findViewById(R.id.btnReg)

        regBtn.setOnClickListener {
            val email = editTextEmail?.text.toString()
            val password = editTextPassword?.text.toString()
            val first_name = editfirst_name?.text.toString()
            val last_name = editlast_name?.text.toString()
            val patronymic = editpatronymic?.text.toString()
            val position = editposition?.text.toString()
            val about_me = "обо мне"
            val phone_number = editphone_number?.text.toString()

            val apiService = UserAPIinterface.createService()
            apiService.registration(email,password,first_name,last_name,patronymic,position,about_me,phone_number)
                .subscribeOn(Schedulers.io())
                .subscribe()

            val mainIntent = Intent(this, LoginActivity::class.java)
            startActivity(mainIntent)
        }

    }

}
