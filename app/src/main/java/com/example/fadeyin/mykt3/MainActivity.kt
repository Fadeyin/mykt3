package com.example.fadeyin.mykt3

import android.app.AlertDialog
import com.example.fadeyin.mykt3.R
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.example.fadeyin.mykt3.models.APIConfig
import com.example.fadeyin.mykt3.models.UserAPIinterface
import com.example.fadeyin.mykt3.screens.LoginActivity
import com.example.fadeyin.mykt3.screens.notice.NoticeAdapter
import com.example.fadeyin.mykt3.screens.notice.noticeActivity
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prfs = getSharedPreferences("AUTHENTICATION", Context.MODE_PRIVATE)
        val token = prfs.getString("AccessToken", null)
        if (token == null) {
            val LoginIntent = Intent(this, LoginActivity::class.java)

            startActivity(LoginIntent)
        }
        APIConfig.token = token
        setContentView(R.layout.activity_main)
        val navigation : BottomNavigationView = findViewById(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }
    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Сообщение")
            .setMessage("Вы действительно хотите выйти из приложения?")
            .setNegativeButton("НЕТ", null)
            .setPositiveButton(
                "ДА"
            ) { dialogInterface, i -> finish()
            }.create().show()

    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_item_events -> {
              //      loadFragment(noticeActivity.newInstance())
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }

    private fun loadFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fl_content, fragment)
        ft.commit()
    }


}
