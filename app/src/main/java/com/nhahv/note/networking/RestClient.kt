package com.nhahv.note.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Nhahv0902 on 6/17/2017.
 * <>
 */
class RestClient private constructor() {


    val BASE_URL = "http://maps.googleapis.com/"
    var mHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    val mBuilder: Retrofit.Builder =
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
    var mRetrofit: Retrofit? = null

    init {
        mRetrofit = mBuilder.client(mHttpClient.build()).build()
    }

    companion object {
        var sInstances: RestClient = RestClient()

        fun getLocationAddress(): LocationAddressAPI {
            return sInstances.mRetrofit?.create(
                    LocationAddressAPI::class.java) as LocationAddressAPI
        }
    }
}