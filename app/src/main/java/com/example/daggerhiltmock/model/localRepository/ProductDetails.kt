package com.example.daggerhiltmock.model.localRepository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phoneDetails")
data class ProductDetails(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val modelName: String,
    val price: Double,
    val cpuModel: String,
    val hardDiskSize: String
)