package com.nhahv.note.widget

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ProgressBar

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>>
 */

class NoteProgress : ProgressDialog {
    constructor(context: Context) : super(context)

    constructor(context: Context, theme: Int) : super(context, theme)

    override fun show() {
        super.show()
        if (window != null) {
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        setContentView(ProgressBar(context))
    }
}
