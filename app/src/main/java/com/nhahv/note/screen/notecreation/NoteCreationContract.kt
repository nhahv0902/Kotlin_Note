package com.nhahv.note.screen.notecreation

import com.nhahv.note.screen.BasePresenter
import com.nhahv.note.screen.BaseViewModel

/**
 * Created by Hoang Van Nha on 5/29/2017.
 * <>
 */
interface NoteCreationContract {
    abstract class ViewModel(activity: NoteCreationActivity) : BaseViewModel<Presenter>(activity) {
        abstract fun onPickPicture()
    }

    interface Presenter : BasePresenter
}