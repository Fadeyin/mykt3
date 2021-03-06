package com.example.fadeyin.mykt3.screens.request

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import com.example.fadeyin.mykt3.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.fadeyin.mykt3.models.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.complaint_item.view.*
import kotlinx.android.synthetic.main.notice_item.view.*
import org.jetbrains.anko.alert
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ComplaintAdapter: RecyclerView.Adapter<ComplaintAdapter.ViewHolder>()  {
    private var noticeList:List<ResultComplaints> = ArrayList<ResultComplaints>()

    fun setComplaintsList(noticeList:List<ResultComplaints>){
        this.noticeList = noticeList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.complaint_item, p0, false)
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
        private val txtTitle = itemView.findViewById<TextView>(R.id.title)
        private val txtStatus = itemView.findViewById<TextView>(R.id.array_status)
        private val txtStatusNow = itemView.findViewById<TextView>(R.id.now_status)
        private var updateBtn = itemView.findViewById<Button>(R.id.update_btn)

        @SuppressLint("SetTextI18n")
        fun bind(notice: ResultComplaints){
            txtTitle?.text = notice.title
            txtDescription?.text = notice.description
            txtStatus?.text = getFormattedDate(notice.time_creation)
            txtStatusNow?.text = getStringStatusNow(notice.statuses)
           /* updateBtn.setOnClickListener{
                Log.d("Result123", "del ${notice.pk} ")
                val apiService = UserAPIinterface.createServiceWithAuth()
                apiService.deleteComplaint(ComplaintRequestDel(notice.pk))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        Log.d("Result123", "успех")
                    }, {error ->
                        Log.d("Result123", "err ${notice.pk} ")
                        error.printStackTrace()
                    })
            }*/
        }

        private fun getFormattedDate(rawDate: Date): String {
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
            //updateBtn.visibility = Button.INVISIBLE
            var textStatuses = ""
            for(i in arrayStatuses.indices) {
                textStatuses = arrayStatuses[i].status_complaint
            }
            return textStatuses
        }
    }

}
