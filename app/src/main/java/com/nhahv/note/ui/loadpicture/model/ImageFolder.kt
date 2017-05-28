package com.nhahv.note.ui.loadpicture.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.nhahv.note.BR

class ImageFolder : BaseObservable {

    @get : Bindable
    var mFolderName: String? = null
	set(value) {
	    mFolderName = value
	    notifyPropertyChanged(BR.mFolderName)
	}

    @get : Bindable
    var mFolderPath: String? = null
	set(value) {
	    mFolderPath = value
	    notifyPropertyChanged(BR.mFolderPath)
	}

    @get:Bindable
    var mListImage: ArrayList<ImagePickerItem> = ArrayList()
	set(listImage) {
	    field = listImage
	    notifyPropertyChanged(BR.mListImage)
	}

    constructor(folderName: String) {
	mFolderName = folderName
    }

    constructor(folderName: String, folderPath: String) {
	mFolderName = folderName
	mFolderPath = folderPath
    }

    constructor(folderName: String, images: ArrayList<ImagePickerItem>) {
	mFolderName = folderName
	mListImage = images
    }


}
