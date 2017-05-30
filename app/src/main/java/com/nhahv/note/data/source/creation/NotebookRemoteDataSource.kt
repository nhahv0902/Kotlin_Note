package com.nhahv.note.data.source.creation

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nhahv.note.data.model.Notebook
import com.nhahv.note.util.FirebaseKey.NOTEBOOK

/**
 * Created by Nhahv0902 on 5/31/2017.
 * <>
 */
class NotebookRemoteDataSource : NotebookDataSource {

    val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference.child(NOTEBOOK)

    override fun addNotebook(notebook: Notebook) {
        notebook.let {
            val key: String = mDatabase.push().key
            notebook.mId = key
            mDatabase.child(key).setValue(notebook) { error, databaseReference ->
                run {
                    println(error?.toString())
                    println(databaseReference?.toString())
                }
            }
        }
    }
}