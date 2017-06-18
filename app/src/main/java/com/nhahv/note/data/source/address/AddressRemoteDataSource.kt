package com.nhahv.note.data.source.address

import com.google.android.gms.maps.model.LatLng
import com.nhahv.note.networking.LocationAddressAPI
import com.nhahv.note.networking.RestClient
import com.nhahv.note.networking.model.ResultAddress
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Nhahv0902 on 6/17/2017.
 * <>
 */
class AddressRemoteDataSource : AddressDataSource {
    val mClient: LocationAddressAPI = RestClient.getLocationAddress()
    override fun getAddress(latLng: LatLng, callback: AddressDataSource.AddressCallback) {
        mClient.let {
            val latLong = "${latLng.latitude},${latLng.longitude}"
            mClient.getLocationAddress(latLong).enqueue(object : Callback<ResultAddress> {
                override fun onResponse(call: Call<ResultAddress>?,
                        response: Response<ResultAddress>?) {
                    if (response != null) {
                        if (response.isSuccessful && response.body() != null) {
                            callback.onLoadAddressed(
                                    response.body()?.mResults?.get(0)?.mFormattedAddress.toString())
                        }
                    } else callback.onLoadAddressFault()
                }

                override fun onFailure(call: Call<ResultAddress>?, t: Throwable?) {
                    callback.onLoadAddressFault()
                }

            })
        }
    }
}