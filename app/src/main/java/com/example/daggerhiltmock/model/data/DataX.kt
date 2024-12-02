package com.example.daggerhiltmock.model.data

import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("CPU model")
    val cpuModel: String,
    @SerializedName("Hard disk size")
    val hardDiskSize: String,
    val price: Double,
    val year: Int
)