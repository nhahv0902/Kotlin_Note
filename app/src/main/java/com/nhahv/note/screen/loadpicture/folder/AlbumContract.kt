package com.nhahv.note.screen.loadpicture.folder

import com.nhahv.note.screen.BasePresenter
import com.nhahv.note.screen.BaseViewModel

/**
 * Created by Hoang Van Nha on 5/29/2017.
 * <>
 */
interface AlbumContract {
    abstract class ViewModel(activity: AlbumActivity) : BaseViewModel<Presenter>(activity)

    interface Presenter : BasePresenter
}