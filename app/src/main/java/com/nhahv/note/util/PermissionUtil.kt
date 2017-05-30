package com.nhahv.note.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.nhahv.note.R


/**
 * Created by Hoang Van Nha on 5/29/2017.
 * <>
 */


val REQUEST_READ_EXTERNAL_STORAGE = 1
val REQUEST_CAMERA = 2

val mHashPermission = mapOf(
        Manifest.permission.READ_EXTERNAL_STORAGE to REQUEST_READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA to REQUEST_CAMERA)


fun readStoragePermission(context: Context, message: Int, activity: AppCompatActivity): Boolean {
    if (hashPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
        return true
    } else {
        if (showRequestPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            showExplainPermission(activity, message, Manifest.permission.READ_EXTERNAL_STORAGE)
        } else {
            requestPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE,
                    mHashPermission[Manifest.permission.READ_EXTERNAL_STORAGE]!!)
        }
    }
    return false
}

fun cameraPermission(context: Context, message: Int, activity: AppCompatActivity): Boolean {
    if (hashPermission(context, Manifest.permission.CAMERA)) {
        return true
    } else {
        if (showRequestPermission(activity, Manifest.permission.CAMERA)) {
            showExplainPermission(activity, message, Manifest.permission.CAMERA)
        } else {
            requestPermission(activity, Manifest.permission.CAMERA,
                    mHashPermission[Manifest.permission.CAMERA]!!)
        }
    }
    return false
}


fun showExplainPermission(activity: AppCompatActivity, message: Int, permission: String) {
    AlertDialog.Builder(activity)
            .setMessage(message)
            .setPositiveButton(R.string.action_agree) { _, _ ->
                run {
                    requestPermission(activity, permission, mHashPermission[permission]!!)
                }
            }
            .setNegativeButton(R.string.action_disagree, null)
            .show()
}

fun hashPermission(context: Context, permission: String): Boolean {
    try {
        return ContextCompat.checkSelfPermission(context,
                permission) == PackageManager.PERMISSION_GRANTED
    } catch (t: RuntimeException) {
        return false
    }
}

fun showRequestPermission(activity: AppCompatActivity, permission: String): Boolean {
    return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
}

fun requestPermission(activity: AppCompatActivity, permission: String, requestPermission: Int) {
    ActivityCompat.requestPermissions(activity, arrayOf(permission), requestPermission)
}
