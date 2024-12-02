package com.example.daggerhiltmock.model.remoteRepository

import com.example.daggerhiltmock.model.data.PhoneDetailsRequest
import com.example.daggerhiltmock.model.data.PhoneDetailsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("objects")
    suspend fun postPhoneDetails(
        @Body phoneDetailsRequest: PhoneDetailsRequest
    ) : Response<PhoneDetailsResponse>

    @GET("objects")
    suspend fun getPhoneDetails(
        @Query("id") ids : List<Int>
    )
}