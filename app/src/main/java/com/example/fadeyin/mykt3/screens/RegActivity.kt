package com.example.fadeyin.mykt3.screens


import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.fadeyin.mykt3.R
import com.example.fadeyin.mykt3.models.UserAPIinterface
import com.example.fadeyin.mykt3.screens.notice.noticeActivity
import io.reactivex.schedulers.Schedulers
import android.view.Gravity
import android.view.View
import android.widget.*
import com.example.fadeyin.mykt3.models.APIConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.db.NULL
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

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        editTextEmail = findViewById(R.id.emailReg)
        editTextPassword = findViewById(R.id.passwordReg)
        editfirst_name = findViewById(R.id.first_name)
        editlast_name = findViewById(R.id.last_name)
        editpatronymic = findViewById(R.id.patronymic)
        editposition = findViewById(R.id.position)
        editphone_number = findViewById(R.id.phone_number)
        var resident = 0
        val apiService2 = UserAPIinterface.createService()
        apiService2.getResidents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                //var resident_list = arrayListOf<String>(result.size,{})
                val resident_list=Array(result.size) { i -> (result[i].name)}
                val resident_id=Array(result.size) { i -> (result[i].pk)}
              /*  for(i in result.indices) {
                    resident_list.add(result[i].name)
                }*/
                Log.d("Result123", "$resident_list")
                val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, resident_list)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                val spinner = findViewById<Spinner>(R.id.spinner2)
                spinner.adapter = adapter
                // заголовок
                spinner.prompt = "Room"
                // выделяем элемент
                spinner.setSelection(0)
                // устанавливаем обработчик нажатия
                spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View,
                                                position:Int, id:Long) {
                        // показываем позиция нажатого элемента
                        resident = resident_id[position]
                        // Toast.makeText(baseContext, "Position = $position", Toast.LENGTH_SHORT).show()
                    }

                    override fun onNothingSelected(arg0: AdapterView<*>) {}
                }
                    }, { error ->
                Toast.makeText(baseContext, "Ошибка сервера! пожалуйста обновите страницу", Toast.LENGTH_SHORT).show()
                Log.d("Result123", "$error")
                error.printStackTrace()
            }
            )
       // Log.d("Result123", "${resident_list[0]}")
        val asc = arrayListOf<String>("1","2","3")
        Log.d("Result123", "$asc")
        //val asc = Array<String>(resident_list.size) { i -> resident_list[i] }
       // Log.d("Result123", "$resident_list")

        regBtn = findViewById(R.id.btnReg)
        regBtn.setOnClickListener {
            val email = editTextEmail?.text.toString()
            val password = editTextPassword?.text.toString()
            val first_name = editfirst_name?.text.toString()
            val last_name = editlast_name?.text.toString()
            val patronymic = editpatronymic?.text.toString()
            val position = editposition?.text.toString()
            val phone_number = editphone_number?.text.toString()

            if ((!emailValidator(email))&&(email != "")){
            val toast = Toast.makeText(
                applicationContext,
                "Неверно введен email",
                Toast.LENGTH_LONG
            )
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
            }
            if (resident == 0){
            val toast = Toast.makeText(
                applicationContext,
                "Выберите место работы",
                Toast.LENGTH_LONG
            )
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
            }
            if ((email == "" || password == ""|| first_name == "" || last_name == "" || patronymic == "" || position == "" || phone_number == "" ) || !(emailValidator(email))){
                val toast = Toast.makeText(
                    applicationContext,
                    "Все поля должны быть заполнены!",
                    Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
            else {
                val apiService = UserAPIinterface.createService()
                apiService.registration(
                    email,
                    password,
                    first_name,
                    last_name,
                    patronymic,
                    position,
                    resident,
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
                        val toast = Toast.makeText(
                            applicationContext,
                            "Ошибка сервера проверьте подключение к интернету",
                            Toast.LENGTH_LONG
                        )
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                        error.printStackTrace()
                    })
            }
        }
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
