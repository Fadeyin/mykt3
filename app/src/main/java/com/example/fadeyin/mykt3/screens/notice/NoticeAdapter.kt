package com.example.fadeyin.mykt3.screens.notice

import android.support.v7.widget.RecyclerView
import com.example.fadeyin.mykt3.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fadeyin.mykt3.models.ResultNotice
import kotlinx.android.synthetic.main.notice_item.view.*

class NoticeAdapter(val noticeList: List<ResultNotice>): RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.notice_item, p0, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.txtTitle?.text = noticeList[p1].title
        p0.txtDescription?.text = noticeList[p1].description
        p0.txtAuthor?.text = noticeList[p1].author
        p0.txtTime?.text = noticeList[p1].time_publication
    }



    override fun getItemCount(): Int {
        return noticeList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtDescription = itemView.findViewById<TextView>(R.id.description)
        val txtTitle = itemView.findViewById<TextView>(R.id.title)
        val txtTime = itemView.findViewById<TextView>(R.id.time)
        val txtAuthor = itemView.findViewById<TextView>(R.id.author)

    }
}