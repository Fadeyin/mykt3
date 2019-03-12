package com.example.fadeyin.mykt3.screens.request


import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import com.example.fadeyin.mykt3.R
import com.example.fadeyin.mykt3.models.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.text.format.DateUtils


import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*


class CreateBooking : AppCompatActivity() {
    lateinit var startTime: String
    lateinit var endTime: String
    lateinit var currentDateTime: TextView
    lateinit var currentDateTime2: TextView
    private var editTextDes: EditText? = null
    private lateinit var createBtn : Button
    var dateAndTime = Calendar.getInstance()
    @SuppressLint("CheckResult")
    public override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.activity_create_booking)
        currentDateTime = findViewById(R.id.currentDateTime)
        currentDateTime2 = findViewById(R.id.currentDateTime2)
        editTextDes = findViewById(R.id.description_booking)
        setInitialDateTime()
        setInitialDateTime2()

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        var room = 1
        val apiService2 = UserAPIinterface.createServiceWithAuth()
        apiService2.getPlaces()
                .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe({ result ->
                  Log.d("Result123", "$result")

                     val data=Array(result.size) { i -> result[i].name_room}
                     val pk =Array(result.size) { i -> result[i].pk}
                     val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data)
                     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                     val spinner = findViewById<Spinner>(R.id.spinner)
                     spinner.adapter = adapter
                     // заголовок
                     spinner.prompt = "Room"
                     // выделяем элемент
                     spinner.setSelection(0)
                     // устанавливаем обработчик нажатия
                     spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                         override fun onItemSelected(parent:AdapterView<*>, view:View,
                                                     position:Int, id:Long) {
                             // показываем позиция нажатого элемента
                             room = pk[position]
                             //Toast.makeText(baseContext, "Position = $room", Toast.LENGTH_SHORT).show()
                         }
                         override fun onNothingSelected(arg0:AdapterView<*>) {}
                     }
                 }, { error ->
                Log.d("Result123", "error")
                error.printStackTrace()
    }
    )

      //  Log.d("Result123", "time: " + startTime + " " + endTime)
// адаптер

        createBtn = findViewById(R.id.create_button)
        createBtn.setOnClickListener {
            val description = editTextDes?.text.toString()

               val progressBar = findViewById<ProgressBar>(R.id.progressBar3)
                progressBar.visibility = ProgressBar.VISIBLE
                Log.d("Result123", "_"+ room + startTime + endTime + description )
                val apiService = UserAPIinterface.createServiceWithAuth()
                apiService.newBooking(BookingRequest(room,startTime,endTime,description))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({result ->
                        val toast = Toast.makeText(
                        applicationContext,
                        "Бронь отправлена.",
                        Toast.LENGTH_LONG
                    )
                        toast.setGravity(Gravity.BOTTOM, 0, 150)
                        toast.show()
                        progressBar.visibility = ProgressBar.INVISIBLE
                        val mainIntent = Intent(this, BookingActivity::class.java)
                        startActivity(mainIntent)
                    }, { error ->
                        val toast = Toast.makeText(
                            applicationContext,
                            "Ошибка сервера проверьте подключение к интернету",
                            Toast.LENGTH_LONG
                        )
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                        Log.d("Result123", "$error")
                        error.printStackTrace()
                    })


        }
    }















    // отображаем диалоговое окно для выбора даты
    fun setDate(v: View) {
        DatePickerDialog(
            this@CreateBooking, d,
            dateAndTime.get(Calendar.YEAR),
            dateAndTime.get(Calendar.MONTH),
            dateAndTime.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }
    fun setDate2(v: View) {
        DatePickerDialog(
            this@CreateBooking, d2,
            dateAndTime.get(Calendar.YEAR),
            dateAndTime.get(Calendar.MONTH),
            dateAndTime.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    // отображаем диалоговое окно для выбора времени
    fun setTime(v: View) {
        TimePickerDialog(
            this@CreateBooking, t,
            dateAndTime.get(Calendar.HOUR_OF_DAY),
            dateAndTime.get(Calendar.MINUTE), true
        )
            .show()
    }
    fun setTime2(v: View) {
        TimePickerDialog(
            this@CreateBooking, t2,
            dateAndTime.get(Calendar.HOUR_OF_DAY),
            dateAndTime.get(Calendar.MINUTE), true
        )
            .show()
    }

    // установка начальных даты и времени
    private fun setInitialDateTime() {
        val fmt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")

        startTime = fmt.format(Date(dateAndTime.timeInMillis))
        currentDateTime.text = DateUtils.formatDateTime(
            this,
            dateAndTime.getTimeInMillis(),
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
                    or DateUtils.FORMAT_SHOW_TIME or DateUtils.FORMAT_24HOUR

        )

    }
    private fun setInitialDateTime2() {
        val fmt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        endTime = fmt.format(Date(dateAndTime.timeInMillis))
        currentDateTime2.text = DateUtils.formatDateTime(
            this,
            dateAndTime.getTimeInMillis(),
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
                    or DateUtils.FORMAT_SHOW_TIME or DateUtils.FORMAT_24HOUR
        )
    }

    // установка обработчика выбора времени
    var t: TimePickerDialog.OnTimeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
        dateAndTime.set(Calendar.MINUTE, minute)
        setInitialDateTime()

    }

    // установка обработчика выбора даты
    var d: DatePickerDialog.OnDateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            dateAndTime.set(Calendar.MONTH, monthOfYear)
            dateAndTime.set(Calendar.YEAR, year)
            setInitialDateTime()

        }
    var t2: TimePickerDialog.OnTimeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
        dateAndTime.set(Calendar.MINUTE, minute)
        setInitialDateTime2()

    }

    // установка обработчика выбора даты
    var d2: DatePickerDialog.OnDateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            dateAndTime.set(Calendar.MONTH, monthOfYear)
            dateAndTime.set(Calendar.YEAR, year)
            setInitialDateTime2()

        }
}

