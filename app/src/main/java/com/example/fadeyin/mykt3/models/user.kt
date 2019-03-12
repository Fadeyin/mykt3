package com.example.fadeyin.mykt3.models


import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import com.example.fadeyin.mykt3.screens.LoginActivity
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import org.reactivestreams.Subscriber
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET
import java.io.IOException
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.HTTP
import retrofit2.http.FormUrlEncoded



interface UserAPIinterface {

// -- user --

    @FormUrlEncoded
    @POST("users/create/")
    fun registration(@Field("email") email: String,
                     @Field("password") password: String,
                     @Field("first_name") first_name: String,
                     @Field("last_name") last_name: String,
                     @Field("patronymic") patronymic: String,
                     @Field("position") position: String,
                     @Field("resident") resident: Int,
                     @Field("phone_number") phone_number: String
    ): Single<Result>


    @FormUrlEncoded
    @POST("users/obtain_token/")
    fun auth(@Field("email") email: String,
             @Field("password") password: String
    ): Observable<ResultLogin>

    // -- Объявления --

    @GET("communication/get_visibel_events/")
    fun getNotice(
    ):Observable<ArrayList<ResultNotice>>

    // -- Жалобы --

    @GET("communication/get_all_complaints/")
    fun getComplaints(
    ):Observable<ArrayList<ResultComplaints>>
    @Headers("Content-Type: application/json")
    @POST("communication/create_new_complaint/")
    fun newComplaint(@Body body: ComplaintRequest): Single<ResultComplaints>
   /* @Headers("Content-Type: application/json")
    @PUT("communication/update_complaint/")
    fun updateComplaint(@Body body: ComplaintRequestUpdate): Single<ResultComplaints>*/
    @HTTP(method = "DELETE", path = "communication/drop_complaint/", hasBody = true)
    fun deleteComplaint(@Body body: ComplaintRequestDel): Single<ResultComplaints>

    // -- Бронирование --
    @GET("communication/get_all_rooms/")
    fun getPlaces(
    ):Observable<ArrayList<ResultPlaces>>
    @GET("communication/get_all_booking/")
    fun getBooking(
    ):Observable<ArrayList<ResultBooking>>
    @Headers("Content-Type: application/json")
    @POST("communication/create_new_booking/")
    fun newBooking(@Body body: BookingRequest): Single<ResultBooking>

    // -- Резиденты --
    @GET("users/get_all_residents/")
    fun getResidents(
    ):Observable<ArrayList<Resident>>
    // -- Мероприятия--
    /**
     *
     */
    companion object Factory {

        fun createService(): UserAPIinterface {
            val httpClient = OkHttpClient.Builder()
            val client = httpClient.build()
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(APIConfig.BASE_URL)
                .client(client)
                .build()
               return retrofit.create(UserAPIinterface::class.java)
        }

        fun  createServiceWithAuth(): UserAPIinterface {
            val interceptor = Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + APIConfig.token)
                    .build()
                chain.proceed(request)
            }
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(interceptor)
            val client = httpClient.build()
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(APIConfig.BASE_URL)
                .client(client)
                .build()
            return retrofit.create(UserAPIinterface::class.java)
        }

        fun  createServiceWithAuth1(): UserAPIinterface {
            val interceptor = Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("key", "" + APIConfig.key)
                    .build()
                chain.proceed(request)
            }
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(interceptor)
            val client = httpClient.build()
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(APIConfig.BASE_URL2)
                .client(client)
                .build()
            return retrofit.create(UserAPIinterface::class.java)
        }
    }
}


