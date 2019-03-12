package com.example.fadeyin.mykt3.screens.request

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.fadeyin.mykt3.R
import com.example.fadeyin.mykt3.models.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CreateComplaint : AppCompatActivity() {

    private var editTextTitle: EditText? = null
    private var editTextDes: EditText? = null
    private lateinit var createBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_complaint)
        editTextTitle = findViewById(R.id.title_complaints)
        editTextDes = findViewById(R.id.description_complaints)
        createBtn = findViewById(R.id.create_button)
        createBtn.setOnClickListener {
            val title = editTextTitle?.text.toString()
            val description = editTextDes?.text.toString()
            if (title == "" || description == ""){
                val toast = Toast.makeText(
                    applicationContext,
                    "Все поля должны быть заполнены!",
                    Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
            else {
                val progressBar = findViewById<ProgressBar>(R.id.progressBar3)
                progressBar.visibility = ProgressBar.VISIBLE
                val apiService = UserAPIinterface.createServiceWithAuth()
                apiService.newComplaint(ComplaintRequest(title,description))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
                progressBar.visibility = ProgressBar.INVISIBLE
                val toast = Toast.makeText(
                    applicationContext,
                    "Жалоба отправлена.",
                    Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.BOTTOM, 0, 150)
                toast.show()
                val complaintIntent = Intent(this, ComplaintsActivity::class.java)
                startActivity(complaintIntent)
            }
        }
    }


}
