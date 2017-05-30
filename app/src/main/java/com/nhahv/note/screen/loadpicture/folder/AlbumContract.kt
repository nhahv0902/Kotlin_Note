package com.nhahv.note.screen.loadpicture.folder

import android.content.Intent
import com.nhahv.note.screen.BasePresenter
import com.nhahv.note.screen.BaseViewModel

/**
 * Created by Hoang Van Nha on 5/29/2017.
 * <>
 */
interface AlbumContract {
    abstract class ViewModel(activity: AlbumActivity) : BaseViewModel<Presenter>(activity) {
        abstract fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
        abstract fun openCamera()
    }

    interface Presenter : BasePresenter
}