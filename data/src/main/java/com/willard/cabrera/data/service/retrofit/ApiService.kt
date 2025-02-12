package com.willard.cabrera.data.service.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun get(@Url url: String): Response<Any>

    @POST
    suspend fun post(@Url url: String, @Body body: Any): Response<Any>

    @PUT
    suspend fun put(@Url url: String, @Body body: Any): Response<Any>

    @DELETE
    suspend fun delete(@Url url: String): Response<Any>
}

