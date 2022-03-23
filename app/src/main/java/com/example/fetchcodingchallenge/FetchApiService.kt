package com.example.fetchcodingchallenge

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val FETCH_URL = "https://fetch-hiring.s3.amazonaws.com/"

/** Build the Moshi object to use with Retrofit. */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/** Builds retrofit object using moshi converter */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(FETCH_URL)
    .build()

interface FetchApiService {

    @GET("hiring.json")
    fun getListItemsAsync(
    ): Deferred<List<ListItem>>


}

object FetchApi {
    val retrofitService: FetchApiService by lazy { retrofit.create(FetchApiService::class.java) }
}