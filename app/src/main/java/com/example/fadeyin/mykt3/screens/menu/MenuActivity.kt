package com.example.fadeyin.mykt3.screens.menu

import android.os.Bundle
import com.example.fadeyin.mykt3.BaseActivity
import com.example.fadeyin.mykt3.R

class MenuActivity :  BaseActivity(4) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        SetupBottomNavigation()
    }
}
