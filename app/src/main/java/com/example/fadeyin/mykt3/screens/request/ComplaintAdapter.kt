package com.example.fadeyin.mykt3.screens.request

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.fadeyin.mykt3.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.fadeyin.mykt3.models.ResultComplaints
import com.example.fadeyin.mykt3.models.ResultNotice
import com.example.fadeyin.mykt3.models.StatusesList
import kotlinx.android.synthetic.main.complaint_item.view.*
import kotlinx.android.synthetic.main.notice_item.view.*
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
        private val txtStatus= itemView.findViewById<TextView>(R.id.array_status)
        private val txtStatusNow= itemView.findViewById<TextView>(R.id.now_status)
        fun bind(notice: ResultComplaints){
            txtTitle?.text = notice.title
            txtDescription?.text = notice.description
            txtStatus?.text =getFormattedDate(notice.time_creation) + " Создание"  + "\n" + getStringStatus(notice.statuses)
            txtStatusNow?.text = getStringStatusNow(notice.statuses)
            Log.d("ResultLog", "statuse --->${notice.statuses}")
            Log.d("ResultLog", " ")
        }
        private fun getFormattedDate(rawDate: Date): String {
            val fmt = SimpleDateFormat("dd.MM.yy HH:mm")
            return fmt.format(rawDate)
        }
        private fun getStringStatus(arrayStatuses: ArrayList<StatusesList>): String {
            var SC: StatusesList? = null
            var textStatuses = ""
            if(arrayStatuses.size == 0 ){
                return textStatuses
            }
            else
            for(i in arrayStatuses.indices) {
                SC = arrayStatuses[i]
                val txt = getFormattedDate(SC.time_accept_status)+ " " + SC.status_complaint + "\n"
                textStatuses = textStatuses + txt

            }
            return textStatuses
        }
        private fun getStringStatusNow(arrayStatuses: ArrayList<StatusesList>): String {
            if(arrayStatuses.size == 0 ){
                return "Не рассмотрена"
            }
            var textStatuses = ""
            for(i in arrayStatuses.indices) {
                textStatuses = arrayStatuses[i].status_complaint
            }
            return textStatuses
        }
    }
}