package com.example.daggerhiltmock.model.data

import com.google.gson.annotations.SerializedName

data class DataXX(
    @SerializedName("capacity GB")
    val capacityGb: Int,
    val color: String
)