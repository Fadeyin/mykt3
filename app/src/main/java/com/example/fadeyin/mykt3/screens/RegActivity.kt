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
import android.view.Gravity
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.regex.Matcher
import java.util.regex.Pattern


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
            if ((email == "" || password == ""|| first_name == "" || last_name == "" || patronymic == "" || position == "" || phone_number == "" ) || !(emailValidator(email))){
                val toast = Toast.makeText(
                    applicationContext,
                    "Все поля должны быть заполнены!",
                    Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                if (!(emailValidator(email))){
                    val toast = Toast.makeText(
                        applicationContext,
                        "Неверно введен email",
                        Toast.LENGTH_LONG
                    )
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                }
            }
            else {
               // reg(email,password,first_name,last_name,patronymic,position,about_me,phone_number)
                val apiService = UserAPIinterface.createService()
                apiService.registration(
                    email,
                    password,
                    first_name,
                    last_name,
                    patronymic,
                    position,
                    about_me,
                    phone_number
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({result ->
                        val mainIntent = Intent(this, LoginActivity::class.java)
                        startActivity(mainIntent)
                        val toast = Toast.makeText(
                            applicationContext,
                            "Регистрация прошла успешно! Дождитесь подтверждения аккаунта, чтобы войти.",
                            Toast.LENGTH_LONG
                        )
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                    }, { error ->
                        error.printStackTrace()
                    })
            }
        }
    }
    fun reg(email:String,password:String,first_name:String,last_name:String,patronymic:String,position:String,about_me:String,phone_number:String){
    }
    fun emailValidator(email: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        pattern = Pattern.compile(EMAIL_PATTERN)
        matcher = pattern.matcher(email)
        return matcher.matches()
    }
    fun phoneValidator(number: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val NUMBER_PATTERN = "^[0-9]{10,11}"
        pattern = Pattern.compile(NUMBER_PATTERN)
        matcher = pattern.matcher(number)
        return matcher.matches()
    }
}
