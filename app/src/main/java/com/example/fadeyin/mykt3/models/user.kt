package com.example.fadeyin.mykt3.models

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Call

interface UserAPIinterface {
    @POST("users/create/")
    fun registration(@Query("email") email: String,
               @Query("password") password: String,
               @Query("first_name") first_name: String,
               @Query("last_name") last_name: String,
               @Query("patronymic") patronymic: String,
               @Query("position") position: String,
               @Query("about_me") about_me: String,
               @Query("phone_number") phone_number: Long
    ): Observable<Result>

    /**
     *
     */
    companion object Factory {
        fun create(): UserAPIinterface {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://90.150.146.248:8000/api/v1/")
                .build()

            return retrofit.create(UserAPIinterface::class.java);
        }
    }
}