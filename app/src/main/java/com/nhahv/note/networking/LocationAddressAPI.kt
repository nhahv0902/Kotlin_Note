package com.nhahv.note.networking

import com.nhahv.note.networking.model.ResultAddress
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Nhahv0902 on 6/17/2017.
 * <>
 */
interface LocationAddressAPI {
    //http://maps.googleapis.com/maps/api/geocode/json?latlng=21.0396805,105.7777889&sensor=true
    @GET("maps/api/geocode/json")
    fun getLocationAddress(@Query("latlng") latlng: String,
            @Query("sensor") sensor: Boolean = true): Call<ResultAddress>
}