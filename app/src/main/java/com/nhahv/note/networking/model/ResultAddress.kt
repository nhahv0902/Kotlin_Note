package com.nhahv.note.networking.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Nhahv0902 on 6/17/2017.
 * <>>
 */

class ResultAddress {
    @SerializedName("results")
    var mResults: List<Results>? = null
    @SerializedName("status")
    var mStatus: String? = null

    class Results {
        @SerializedName("formatted_address")
        var mFormattedAddress: String? = null
    }
}
