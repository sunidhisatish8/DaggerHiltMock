package com.example.daggerhiltmock.repository

import androidx.lifecycle.LiveData
import com.example.daggerhiltmock.model.data.PhoneDetailsRequest
import com.example.daggerhiltmock.model.data.PhoneDetailsResponse
import com.example.daggerhiltmock.model.localRepository.ProductDetails
import retrofit2.Response

interface IRepository {
    suspend fun addPhoneDetails(phoneDetailsRequest: PhoneDetailsRequest) : Response<PhoneDetailsResponse>
    fun fetchPhoneDetails(): LiveData<List<ProductDetails>>
    suspend fun checkIfExists(modelName: String): ProductDetails?
}