package com.example.daggerhiltmock.model.localRepository

import androidx.room.TypeConverter
import com.example.daggerhiltmock.model.data.Data
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromData(data: Data): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toData(json: String): Data {
        val type = object : TypeToken<Data>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun fromList(valueList : List<String>) : String {
        return valueList.joinToString(",")
    }

    @TypeConverter
    fun toList(value : String) : List<String> {
        return value.split(",")
    }
}