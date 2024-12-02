package com.example.daggerhiltmock.repository

import androidx.lifecycle.LiveData
import com.example.daggerhiltmock.model.data.PhoneDetailsRequest
import com.example.daggerhiltmock.model.data.PhoneDetailsResponse
import com.example.daggerhiltmock.model.localRepository.PhoneDao
import com.example.daggerhiltmock.model.localRepository.ProductDetails
import com.example.daggerhiltmock.model.remoteRepository.ApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val phoneDao: PhoneDao
) : IRepository {
    override suspend fun addPhoneDetails(
        phoneDetailsRequest: PhoneDetailsRequest
    ): Response<PhoneDetailsResponse> {
        val response = apiService.postPhoneDetails(phoneDetailsRequest)
        val productDetails = ProductDetails(
            modelName = phoneDetailsRequest.name,
            price = phoneDetailsRequest.data.price,
            cpuModel = phoneDetailsRequest.data.cpuModel,
            hardDiskSize = phoneDetailsRequest.data.hardDiskSize
        )
        if (response.isSuccessful)
            phoneDao.insertPhoneDetails(productDetails)
        return response
    }

    override fun fetchPhoneDetails(): LiveData<List<ProductDetails>> {
        return phoneDao.fetchPhoneDetails()
    }

    override suspend fun checkIfExists(modelName: String): ProductDetails? {
        return withContext(IO) {
            phoneDao.findByModelName(modelName)
        }
    }
}