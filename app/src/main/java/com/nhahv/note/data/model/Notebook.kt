package com.nhahv.note.data.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.nhahv.note.BR

/**
 * Created by Hoang Van Nha on 5/27/2017.
 * <.
 */

class Notebook : BaseObservable() {

    @get:Bindable
    var mId: String? = ""
        set(id) {
            field = id
            notifyPropertyChanged(BR.mId)
        }

    @get: Bindable
    var mTitle: String? = null
        set(title) {
            field = title
            notifyPropertyChanged(BR.mTitle)
        }

    @get: Bindable
    var mContent: String? = null
        set(title) {
            field = title
            notifyPropertyChanged(BR.mContent)
        }

    @get: Bindable
    var mPlace: String? = null
        set(title) {
            field = title
            notifyPropertyChanged(BR.mPlace)
        }

    @get: Bindable
    var mPictures: ArrayList<String>? = null
        set(title) {
            field = title
            notifyPropertyChanged(BR.mPictures)
        }

    @get: Bindable
    var mDate: Long? = null
        set(title) {
            field = title
            notifyPropertyChanged(BR.mDate)
        }
    @get: Bindable
    var mTime: String? = null
        set(title) {
            field = title
            notifyPropertyChanged(BR.mTime)
        }
}
