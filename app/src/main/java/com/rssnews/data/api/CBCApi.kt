package com.rssnews.data.api

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by Vladyslav Ulianytskyi on 30.11.2018.
 */

interface CBCApi {
    @GET
    fun getRssData(@Url link: String): Call<RssResponse>

    companion object {
        fun create(url: String): CBCApi {
            val retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .client(OkHttpClient())
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build()
            return  retrofit.create(CBCApi::class.java)
        }
    }
}