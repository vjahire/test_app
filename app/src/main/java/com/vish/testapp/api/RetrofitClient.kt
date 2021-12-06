package com.vish.testapp.api

import com.vish.testapp.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
//        fun create() : ApiInterface {
//
//            val retrofit = Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(BASE_URL)
//                .build()
//            return retrofit.create(ApiInterface::class.java)
//        }


        // Second way
        private val retrofit by lazy {
            val client = OkHttpClient.Builder()
//                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(ApiInterface::class.java)
        }
    }


}