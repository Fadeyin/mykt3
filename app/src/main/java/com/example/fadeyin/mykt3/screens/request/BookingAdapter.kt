package com.example.fadeyin.mykt3.screens.request

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import com.example.fadeyin.mykt3.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.fadeyin.mykt3.models.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.booking_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class BookingAdapter: RecyclerView.Adapter<BookingAdapter.ViewHolder>()  {
    private var noticeList:List<ResultBooking> = ArrayList<ResultBooking>()

    fun setBookingList(noticeList:List<ResultBooking>){
        this.noticeList = noticeList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.booking_item, p0, false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(noticeList[position])
    }
    override fun getItemCount(): Int {
        return noticeList.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtDescription = itemView.findViewById<TextView>(R.id.description)
        private val txtRoom = itemView.findViewById<TextView>(R.id.room)
        private val txtStartTime = itemView.findViewById<TextView>(R.id.start_time)
        private val txtEndTime = itemView.findViewById<TextView>(R.id.end_time)
        private val txtStatus = itemView.findViewById<TextView>(R.id.array_status)
        private val txtStatusNow = itemView.findViewById<TextView>(R.id.now_status)
        private var updateBtn = itemView.findViewById<Button>(R.id.update_btn)

        @SuppressLint("SetTextI18n")
        fun bind(booking: ResultBooking){
            txtRoom?.text = booking.room
            txtDescription?.text = booking.description
            txtStartTime?.text = getFormattedDate(booking.start_date_time)
            txtEndTime?.text = getFormattedDate(booking.stop_date_time)
           // txtStatus?.text = getFormattedDate(booking.time_creation) + " Создание"  + "\n" + getStringStatus(notice.statuses)
            txtStatusNow?.text = getStringStatusNow(booking.statuses)
          //  updateBtn.setOnClickListener{
               // Log.d("Result123", "del $booking ")
                /*val apiService = UserAPIinterface.createServiceWithAuth()
                apiService.deleteComplaint(ComplaintRequestDel(booking.pk))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        Log.d("Result123", "успех")
                    }, {error ->
                        Log.d("Result123", "err ${notice.pk} ")
                        error.printStackTrace()
                    })*/
          //  }
        }

        private fun getFormattedDate(rawDate: Date): String {
            if(rawDate == null)
               return "null"
            val fmt = SimpleDateFormat("dd.MM.yy HH:mm")
            return fmt.format(rawDate)
        }
        private fun getStringStatus(arrayStatuses: ArrayList<StatusesList>): String {
            var textStatuses = ""
            if(arrayStatuses.size == 0 ){
                return textStatuses
            }
            else
                for(i in arrayStatuses.indices) {
                    val txt = getFormattedDate(arrayStatuses[i].time_accept_status)+ " " + arrayStatuses[i].status_complaint + "\n"
                    textStatuses += txt
                }
            return textStatuses
        }
        private fun getStringStatusNow(arrayStatuses: ArrayList<StatusesList>): String {
            if(arrayStatuses.size == 0 ){
                //updateBtn.visibility = Button.VISIBLE
                return "Не рассмотрена"
            }
           // updateBtn.visibility = Button.INVISIBLE
            var textStatuses = ""
            for(i in arrayStatuses.indices) {
                textStatuses = arrayStatuses[i].status_complaint
            }
            return textStatuses
        }
    }

}
