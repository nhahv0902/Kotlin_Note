package com.nhahv.note.screen.reminder

import com.nhahv.note.screen.BasePresenter
import com.nhahv.note.screen.BaseViewModel

/**
 * Created by Hoang Van Nha on 5/29/2017.
 * <>
 */
interface ReminderContract {
    abstract class ViewModel(activity: ReminderActivity) : BaseViewModel<Presenter>(activity)

    interface Presenter : BasePresenter
}