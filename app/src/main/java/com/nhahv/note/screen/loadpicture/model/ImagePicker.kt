package com.nhahv.note.screen.loadpicture.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.os.Parcel
import android.os.Parcelable
import com.nhahv.note.BR

class ImagePicker(mImagePath: String, mChecked: Boolean) : BaseObservable(), Parcelable {
    @get: Bindable
    var mImagePath: String? = mImagePath
        set(value) {
            field = value
            notifyPropertyChanged(BR.mImagePath)
        }

    @get: Bindable
    var mChecked: Boolean = mChecked
        set(value) {
            field = value
            notifyPropertyChanged(BR.mChecked)
        }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<ImagePicker> = object : Parcelable.Creator<ImagePicker> {
            override fun createFromParcel(source: Parcel): ImagePicker = ImagePicker(source)
            override fun newArray(size: Int): Array<ImagePicker?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(mImagePath)
        dest.writeInt((if (mChecked) 1 else 0))
    }
}
