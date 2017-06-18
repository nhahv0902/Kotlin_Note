package com.nhahv.note.data.source.address

import com.google.android.gms.maps.model.LatLng

/**
 * Created by Nhahv0902 on 6/17/2017.
 * <>
 */
class AddressRepository : AddressDataSource {

    private val mDataSource = AddressRemoteDataSource()

    override fun getAddress(latLng: LatLng, callback: AddressDataSource.AddressCallback) {
        mDataSource.let {
            mDataSource.getAddress(latLng, callback)
        }
    }
}