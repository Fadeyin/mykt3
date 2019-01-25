package com.example.fadeyin.mykt3.screens.resident


import android.os.Bundle
import com.example.fadeyin.mykt3.R
import com.example.fadeyin.mykt3.BaseActivity

class ResidentActivity :  BaseActivity(2) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resident)
        SetupBottomNavigation()
    }
}
