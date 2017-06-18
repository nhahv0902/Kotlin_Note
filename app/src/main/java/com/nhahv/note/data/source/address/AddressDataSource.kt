package com.nhahv.note.data.source.address

import com.google.android.gms.maps.model.LatLng

/**
 * Created by Nhahv0902 on 6/17/2017.
 * <>
 */
interface AddressDataSource {
    interface AddressCallback {
        fun onLoadAddressed(address: String)
        fun onLoadAddressFault()
    }

    fun getAddress(latLng: LatLng, callback: AddressCallback)
}