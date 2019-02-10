package com.example.fadeyin.mykt3.screens

import android.util.Log
import io.reactivex.schedulers.Schedulers
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import com.example.fadeyin.mykt3.screens.notice.noticeActivity
import com.example.fadeyin.mykt3.R
import android.view.Gravity
import android.widget.Toast
import android.content.Context
import com.example.fadeyin.mykt3.models.*
import io.reactivex.android.schedulers.AndroidSchedulers


class LoginActivity : AppCompatActivity() {
    private var editTextEmail: EditText? = null
    private var editTextPassword: EditText? = null
    private lateinit var loginBtn : Button
    private lateinit var toRegBtn : Button
    var logininfo : ResultLogin? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editTextEmail = findViewById(R.id.email) as EditText
        editTextPassword = findViewById(R.id.password) as EditText
        loginBtn = findViewById(R.id.btnLogin)
        toRegBtn = findViewById(R.id.btnToReg)
        toRegBtn.setOnClickListener {
            val mainIntent = Intent(this, RegActivity::class.java)
            startActivity(mainIntent)
        }
        loginBtn.setOnClickListener {
            val email = editTextEmail?.text.toString()
            val password = editTextPassword?.text.toString()
            if ((email == "" || password == "") || (!(emailValidator(email)))) {
                if (email == "" || password == "") {
                    viewToast("Все поля должны быть заполнены!")
                }
                if (!(emailValidator(email)) && email != "" && password != "") {
                    viewToast("Неверно введен email")
                }
            } else {
                val apiService = UserAPIinterface.createService()
                apiService.auth(email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        Log.d("Result", "There are ${result.status}")
                        APIConfig.token = result.auth.token
                        logininfo = result
                        AuthInfo.message = result.message
                        val preferences = getSharedPreferences("AUTHENTICATION", Context.MODE_PRIVATE)
                        val editor = preferences.edit()
                        editor.putString("AccessToken", result.auth.token)
                        editor.apply()
                        val mainIntent = Intent(this, noticeActivity::class.java)
                        startActivity(mainIntent)
                    }, { error ->
                        error.printStackTrace()
                        viewToast("Неверно введен email или пароль")
                    })
            }
        }
    }
    private fun viewToast(txt:String) {
        val toast2 = Toast.makeText(
            applicationContext,
            txt,
            Toast.LENGTH_LONG
        )
        toast2.setGravity(Gravity.CENTER, 0, 0)
        toast2.show()
    }
}
