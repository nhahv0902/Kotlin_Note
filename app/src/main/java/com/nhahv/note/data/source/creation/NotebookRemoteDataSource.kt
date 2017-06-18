package com.nhahv.note.data.source.creation

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.nhahv.note.data.model.Notebook
import com.nhahv.note.data.source.picture.PictureStorageRepository
import com.nhahv.note.data.source.picture.UpLoadPictureCallback
import com.nhahv.note.util.FirebaseKey.NOTEBOOK

/**
 * Created by Nhahv0902 on 5/31/2017.
 * <>
 */
class NotebookRemoteDataSource : NotebookDataSource {

    private val mUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    private val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference.child(
            NOTEBOOK)
    private val mPictureRepository = PictureStorageRepository()

    override fun addNotebook(notebook: Notebook, callback: NotebookDataSource.Callback) {
        if (mUser == null) {
            callback.onError()
            return
        }
        val key: String = mDatabase.push().key
        notebook.mKey = key
        mDatabase.child(mUser.uid).child(key).setValue(notebook) { error, databaseReference ->
            run {
                if (databaseReference != null) {
                    if (notebook.mPictures.size > 0) {
                        mPictureRepository.upLoadMultiple(notebook.mPictures, notebook,
                                object : UpLoadPictureCallback {
                                    override fun onUpLoadPictureSuccess() {}
                                    override fun onUpLoadPictureError() {
                                        callback.onError()
                                    }
                                })
                    }
                    callback.onSuccess()
                } else if (error != null) {
                    callback.onError()
                }
            }
        }
    }

    override fun getNotebooks(callback: NotebookDataSource.LoadNotebookCallback) {
        if (mUser == null) {
            callback.onDataNotAvailable()
            return
        }
        mDatabase.child(mUser.uid).addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError?) {
                        callback.onDataNotAvailable()
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot?) {
                        if (dataSnapshot != null) {
                            val notebooks: ArrayList<Notebook> = ArrayList()
                            var notebook: Notebook? = null
                            for (data in dataSnapshot.children) {
                                notebook = data.getValue(Notebook::class.java)
                                notebook?.let { notebooks.add(it) }
                            }
                            callback.onNotebooksLoaded(ArrayList(notebooks.sorted()))
                        } else {
                            callback.onDataNotAvailable()
                        }
                    }
                })
    }
}