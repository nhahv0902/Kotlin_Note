package com.nhahv.note.ui.loadpicture.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.nhahv.note.BR

class ImagePickerItem(path: String) : BaseObservable() {

    @get: Bindable
    var mImagePath: String? = path
	set(value) {
	    field = value
	    notifyPropertyChanged(BR.mImagePath)
	}

    @get: Bindable
    var mChecked: Boolean = false
	set(value) {
	    field = value
	    notifyPropertyChanged(BR.mChecked)
	}

    /*companion object {
      @JvmField val CREATOR: Parcelable.Creator<ImagePickerItem> = object : Parcelable.Creator<ImagePickerItem> {
	override fun createFromParcel(source: Parcel): ImagePickerItem = ImagePickerItem(source)
	override fun newArray(size: Int): Array<ImagePickerItem?> = arrayOfNulls(size)
      }
    }

    constructor(source: Parcel) : this()

    constructor() : super()

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
      dest.writeString(mImagePath)
      dest.writeByte((if (mChecked) 1 else 0).toByte())
    }
  */
}
