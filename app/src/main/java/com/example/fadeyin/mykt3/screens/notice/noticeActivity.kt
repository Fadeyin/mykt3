package com.example.fadeyin.mykt3.screens.notice


import android.os.Bundle
import com.example.fadeyin.mykt3.BaseActivity
import com.example.fadeyin.mykt3.R

class noticeActivity :  BaseActivity(0) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)
        SetupBottomNavigation()
    }
}
