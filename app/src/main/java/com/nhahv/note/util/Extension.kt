package com.nhahv.note.util

import android.app.Fragment
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by Hoang Van Nha on 5/28/2017.
 * <
 */

fun Fragment.toast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
  context.let { Toast.makeText(context, message, duration).show() }
}

fun AppCompatActivity.toast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
  context.let { Toast.makeText(context, message, duration).show() }
}

fun Context.toast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
  context.let { Toast.makeText(context, message, duration).show() }
}

fun Context.toast(context: Context, message: Int, duration: Int = Toast.LENGTH_SHORT) {
  context.let { Toast.makeText(context, message, duration).show() }
}