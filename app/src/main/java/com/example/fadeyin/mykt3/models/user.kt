package com.example.fadeyin.mykt3.models


import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

interface UserAPIinterface {

    @FormUrlEncoded
    @POST("users/create/")
    fun registration(@Field("email") email: String,
                     @Field("password") password: String,
                     @Field("first_name") first_name: String,
                     @Field("last_name") last_name: String,
                     @Field("patronymic") patronymic: String,
                     @Field("position") position: String,
                     @Field("about_me") about_me: String,
                     @Field("phone_number") phone_number: Long
    ): Single<Result>


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