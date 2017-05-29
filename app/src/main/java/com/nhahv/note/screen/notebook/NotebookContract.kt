package com.nhahv.note.screen.notebook

import com.nhahv.note.screen.BaseActivity
import com.nhahv.note.screen.BasePresenter
import com.nhahv.note.screen.BaseViewModel

/**
 * Created by Hoang Van Nha on 5/29/2017.
 * <>
 */
interface NotebookContract {
    abstract class ViewModel(activity: BaseActivity) : BaseViewModel<Presenter>(activity)

    interface Presenter : BasePresenter
}