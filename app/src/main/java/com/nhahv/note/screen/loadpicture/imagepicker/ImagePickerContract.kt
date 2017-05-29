package com.nhahv.note.screen.loadpicture.imagepicker

import com.nhahv.note.screen.BasePresenter
import com.nhahv.note.screen.BaseViewModel

/**
 * Created by Hoang Van Nha on 5/29/2017.
 * <>
 */
interface ImagePickerContract {

    abstract class ViewModel(activity: ImagePickerActivity) : BaseViewModel<Presenter>(activity)

    interface Presenter : BasePresenter
}