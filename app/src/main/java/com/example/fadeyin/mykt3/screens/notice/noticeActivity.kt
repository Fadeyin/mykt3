package com.example.fadeyin.mykt3.screens.notice


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

class noticeActivity :  BaseActivity(0) {


    var notice : ResultNotice? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)
        SetupBottomNavigation()


       /* val apiService = UserAPIinterface.createServiceWithAuth()
        apiService.getNotice()
         .subscribeOn(Schedulers.io())
         .subscribe(
            )
            val apiService2 = UserAPIinterface.createServiceWithAuth()
        apiService2.getNotice()
            .subscribeOn(Schedulers.io())
            .subscribe({
                    result ->
                Log.d("Result", "There are ${result.title}")
                notice.title = result.title
            }, { error ->
                error.printStackTrace()
            }
            )
*/


    }
}
