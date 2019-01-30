package com.example.fadeyin.mykt3.models


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
                     @Field("phone_number") phone_number: String
    ): Single<Result>

    @FormUrlEncoded
    @POST("users/obtain_token/")
    fun auth(
             @Field("email") email: String,
             @Field("password") password: String


    ): Observable<ResultLogin>

    @FormUrlEncoded
    @GET("communication/get_visibel_events/")
    fun getNotice(): Observable<ResultNotice>

    /**
     *
     */
    companion object Factory {
        fun create(): UserAPIinterface {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(APIConfig.BASE_URL)
                .build()
            return retrofit.create(UserAPIinterface::class.java);
        }


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
                    .addHeader("Authorization:", "Baerer"+ APIConfig.token)
                    .build()

                chain.proceed(request)
            }
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(interceptor)
            val client = httpClient.build()

            val retrofit = Retrofit.Builder()
                .baseUrl(APIConfig.BASE_URL)
                .client(client)
                .build()
            return retrofit.create(UserAPIinterface::class.java)

       }
    }


}

