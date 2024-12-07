package com.medbuddy.network

import com.medbuddy.model.MainModelResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("f2840390-bd94-4c7e-976e-ad6ca65ad91e")
    suspend fun getMedicines(): Response<MainModelResponse>
}