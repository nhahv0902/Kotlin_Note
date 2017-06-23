package com.nhahv.note.data.source.picture

import com.nhahv.note.data.model.Notebook

/**
 * Created by Nhahv0902 on 5/31/2017.
 * <>
 */
interface PictureStorageDataSource {

    fun upLoadPicture(pathPicture: String, isLast: Boolean, notebook: Notebook,
                      callback: UpLoadPictureCallback)

    fun upLoadMultiple(pathPictures: ArrayList<String>, notebook: Notebook,
                       callback: UpLoadPictureCallback)
}
