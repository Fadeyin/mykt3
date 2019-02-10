package com.example.fadeyin.mykt3.screens.notice

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
import com.example.fadeyin.mykt3.models.ResultNotice
import kotlinx.android.synthetic.main.notice_item.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*




class NoticeAdapter : RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {


    private var noticeList:List<ResultNotice> = ArrayList<ResultNotice>()

    fun setNoticeList(noticeList:List<ResultNotice>){
        this.noticeList = noticeList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.notice_item, p0, false)
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
        private val txtTime = itemView.findViewById<TextView>(R.id.time)
        private val txtAuthor = itemView.findViewById<TextView>(R.id.author)

        fun bind(notice:ResultNotice){
            txtTitle?.text = notice.title
            txtDescription?.text = notice.description
            txtAuthor?.text = notice.author
            txtTime?.text = getFormattedDate(notice.time_publication)
        }
        private fun getFormattedDate(rawDate: Date): String {
            val fmt = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
            return fmt.format(rawDate)

        }

    }
}
