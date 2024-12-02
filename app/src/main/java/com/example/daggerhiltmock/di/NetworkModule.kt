package com.example.daggerhiltmock.di

import android.content.Context
import com.example.daggerhiltmock.model.data.Constants
import com.example.daggerhiltmock.model.interceptor.NetworkInterceptor
import com.example.daggerhiltmock.model.remoteRepository.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    @Singleton
    fun provideLoggingInterceptor() : HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideClient(loggingInterceptor: HttpLoggingInterceptor, networkInterceptor: NetworkInterceptor) : OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(networkInterceptor)
            .build()
        return client
    }

    @Provides
    @Singleton
    @Named("product")
    fun provideRetrofitForProduct(okHttpClient: OkHttpClient) : Retrofit {
       val retrofit = Retrofit.Builder()
           .baseUrl(Constants.PRODUCT_BASE_URL)
           .client(okHttpClient)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
        return retrofit
    }

    @Provides
    @Singleton
    @Named("json")
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.JSON_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideApiService(@Named("product") retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }
}