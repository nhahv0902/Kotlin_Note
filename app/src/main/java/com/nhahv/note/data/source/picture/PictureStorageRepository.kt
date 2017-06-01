package com.nhahv.note.data.source.picture

import com.nhahv.note.data.model.Notebook

/**
 * Created by Nhahv0902 on 5/31/2017.
 * <>
 */
class PictureStorageRepository : PictureStorageDataSource {

    private val mDataSource = PictureStorageRemoteDataSource()

    override fun upLoadPicture(pathPicture: String, isLast: Boolean, notebook: Notebook,
            callback: UpLoadPictureCallback) {
        mDataSource.upLoadPicture(pathPicture, isLast, notebook, callback)
    }

    override fun upLoadMultiple(pathPictures: ArrayList<String>, notebook: Notebook,
            callback: UpLoadPictureCallback) {
        mDataSource.upLoadMultiple(pathPictures, notebook, callback)
    }
}