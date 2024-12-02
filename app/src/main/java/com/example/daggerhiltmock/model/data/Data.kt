package com.example.daggerhiltmock.model.data

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("CPU model")
    @ColumnInfo("CPU model")
    val cpuModel: String,
    @SerializedName("Hard disk size")
    @ColumnInfo("Hard disk size")
    val hardDiskSize: String,
    val price: Double,
    val year: Int
)