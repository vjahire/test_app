package com.vish.testapp.api

import com.vish.testapp.models.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/users")
    suspend fun getUsers(): Response<List<User>>


}