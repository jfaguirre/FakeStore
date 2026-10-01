package com.example.fakestore.network

import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType

object RetrofitInstance {

    private const val BASE_URL = "https://fakestoreapi.com/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    val api: FakeStoreApi by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(FakeStoreApi::class.java)
    }
}