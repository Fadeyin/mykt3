package com.example.fadeyin.mykt3.screens.notice


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.example.fadeyin.mykt3.BaseActivity
import com.example.fadeyin.mykt3.R
import com.example.fadeyin.mykt3.models.APIConfig
import com.example.fadeyin.mykt3.models.AuthInfo
import com.example.fadeyin.mykt3.models.ResultNotice
import com.example.fadeyin.mykt3.models.UserAPIinterface
import com.example.fadeyin.mykt3.screens.LoginActivity
import io.reactivex.schedulers.Schedulers
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences



class noticeActivity :  BaseActivity(0) {


    var notice : String? = null
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prfs = getSharedPreferences("AUTHENTICATION", Context.MODE_PRIVATE)
        val token = prfs.getString("AccessToken", null )

        if (token == null ) {
            val LoginIntent = Intent(this, LoginActivity::class.java)
            startActivity(LoginIntent)
        }
        Log.d("Result", "There are ${token}")
        setContentView(R.layout.activity_notice)
        SetupBottomNavigation()




       /**/
            APIConfig.token = token
            val apiService2 = UserAPIinterface.createServiceWithAuth()
            apiService2.getNotice()
            .subscribeOn(Schedulers.io())
            .subscribe({
                    result ->
                    Log.d("Result", "There are ${result[0].title}")
            }, { error ->
                error.printStackTrace()
            }
            )



    }
}
