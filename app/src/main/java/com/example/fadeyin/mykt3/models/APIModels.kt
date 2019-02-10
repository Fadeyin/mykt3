package com.example.fadeyin.mykt3.models

import java.util.*
import kotlin.collections.ArrayList

data class ResultLogin (
    val status : String,
    val auth : Token,
    val message: String,
    val items: Data
)
data class Token (
    val token: String
)
data class Data(
    val id: String,
    val username: String
)
data class ResultNotice (
    val pk: Int,
    val title: String,
    val description: String,
    val author: String,
    val time_publication: Date,
    val hide: Boolean
)
data class ResultComplaints (
    val pk: Int,
    val title: String,
    val description: String,
    val author: Int,
    val time_creation: Date,
    val statuses: ArrayList<StatusesList>
)
data class StatusesList(
    val status_complaint : String,
    val time_accept_status: Date
)

class ComplaintRequest internal constructor(internal val title: String, internal val description: String)
class ComplaintRequestUpdate internal constructor(internal val pk: Int, internal val title: String, internal val description: String)
class ComplaintRequestDel internal constructor(internal val pk: Int)
