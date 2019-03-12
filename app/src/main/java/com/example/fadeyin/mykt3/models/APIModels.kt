package com.example.fadeyin.mykt3.models

import java.util.*
import kotlin.collections.ArrayList

data class ResultLogin (
    val status : String,
    val auth : Token,
    val message: String,
    val items: Data
)
data class ResultLogin1 (
    val key : String
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
data class ResultPlaces(
    val pk: Int,
    val name_room: String,
    val description: String
)
data class ResultBooking (
    val pk: Int,
    val room: String,
    val description: String,
    val user: Int,
    val start_date_time: Date,
    val stop_date_time: Date,
    val time_creation: Date,
    val statuses: ArrayList<StatusesList>
    )
data class StatusesList(
    val status_complaint : String,
    val time_accept_status: Date
)
// personal and residents
data class Resident(
    val pk: Int,
    val name: String,
    val description: String
)

class ComplaintRequest internal constructor(internal val title: String, internal val description: String)
class ComplaintRequestUpdate internal constructor(internal val pk: Int, internal val title: String, internal val description: String)
class ComplaintRequestDel internal constructor(internal val pk: Int)
class BookingRequest internal constructor(internal val room: Int,internal val start_date_time: String,internal val stop_date_time: String, internal val description: String)
