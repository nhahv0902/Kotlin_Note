package com.nhahv.note.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.nhahv.note.data.model.Notebook

/**
 * Created by nhahv0902 on 6/26/17.
 * <>
 */
@Dao
interface NotebookDao {
    @Query("SELECT * FROM notebook")
    fun getAllNotebook(): ArrayList<Notebook>

}