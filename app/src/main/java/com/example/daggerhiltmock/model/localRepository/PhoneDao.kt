package com.example.daggerhiltmock.model.localRepository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.daggerhiltmock.model.data.PhoneDetailsRequest

@Dao
interface PhoneDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertPhoneDetails(productDetails: ProductDetails)

    @Query("Select * from phoneDetails")
    fun fetchPhoneDetails() : LiveData<List<ProductDetails>>

    @Query("SELECT * FROM phoneDetails WHERE modelName = :modelName")
    suspend fun findByModelName(modelName: String): ProductDetails?

}