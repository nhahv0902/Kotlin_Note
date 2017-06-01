package com.nhahv.note.data.source.picture

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.nhahv.note.data.model.Notebook
import com.nhahv.note.util.DataUtil.NOTE_TAG
import com.nhahv.note.util.FirebaseKey.NOTEBOOK
import java.io.File


/**
 * Created by Nhahv0902 on 5/31/2017.
 * <>
 */
class PictureStorageRemoteDataSource : PictureStorageDataSource {

    private val mStorage: StorageReference = FirebaseStorage.getInstance().reference
    private val mUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    private val mDatabase = FirebaseDatabase.getInstance().reference.child(NOTEBOOK)


    @SuppressLint("VisibleForTests")
    override fun upLoadPicture(pathPicture: String, isLast: Boolean, notebook: Notebook,
            callback: UpLoadPictureCallback) {
        if (mUser == null) {
            callback.onUpLoadPictureError()
            return
        }
        val uri: Uri = Uri.fromFile(File(pathPicture))
        notebook.mId?.let {
            mStorage.child(mUser.uid).child(it).child(uri.lastPathSegment).putFile(
                    uri).addOnSuccessListener(
                    { taskSnapshot ->
                        run {
                            mDatabase.child(
                                    "${mUser?.uid}/${notebook.mId}/mpictures").push().setValue(
                                    taskSnapshot.downloadUrl)
                            Log.d(NOTE_TAG,
                                    "data = ${taskSnapshot.downloadUrl?.path} + ${taskSnapshot.downloadUrl}")
                            if (isLast) {
                                callback.onUpLoadPictureSuccess()
                            }
                        }
                    })
                    .addOnFailureListener({ exception ->
                        run {
                            Log.d(NOTE_TAG, "path = ${exception.message}")
                            callback.onUpLoadPictureError()
                        }
                    })
        }
    }

    override fun upLoadMultiple(pathPictures: ArrayList<String>, notebook: Notebook,
            callback: UpLoadPictureCallback) {
        if (mUser == null) {
            callback.onUpLoadPictureError()
            return
        }

        for (index in 0 until pathPictures.size) {
            upLoadPicture(pathPictures[index], index == pathPictures.size - 1, notebook, callback)
        }
    }
}