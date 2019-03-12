package com.example.fadeyin.mykt3.screens.resident

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fadeyin.mykt3.R
import com.example.fadeyin.mykt3.models.Resident
import com.example.fadeyin.mykt3.models.ResultNotice
import com.example.fadeyin.mykt3.screens.notice.NoticeAdapter
import java.text.SimpleDateFormat
import java.util.*

class ResidentAdapter : RecyclerView.Adapter<ResidentAdapter.ViewHolder>() {


    private var residentList:List<Resident> = ArrayList<Resident>()

    fun setResidentList(residentList:List<Resident>){
        this.residentList = residentList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.resident_item, p0, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(residentList[position])
    }

    override fun getItemCount(): Int {
        return residentList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtDescription = itemView.findViewById<TextView>(R.id.description)
        private val txtName = itemView.findViewById<TextView>(R.id.resident_name)
        private val txtTime = itemView.findViewById<TextView>(R.id.time)
        private val txtAuthor = itemView.findViewById<TextView>(R.id.author)

        fun bind(resident: Resident){
            txtName?.text = resident.name
            txtDescription?.text = resident.description
        }
        private fun getFormattedDate(rawDate: Date): String {
            val fmt = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
            return fmt.format(rawDate)

        }

    }
}