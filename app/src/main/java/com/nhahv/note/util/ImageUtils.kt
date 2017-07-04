package com.nhahv.note.util

import android.content.Context
import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Nhahv0902 on 6/16/2017.
 * <>
 */
fun createImageFile(context: Context): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        storageDir      /* directory */
    )
    return image
}

/*
fun dispatchTakePictureIntent(context: Context) {
    val takePictureIntent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    // Ensure that there's a camera activity to handle the intent
    if (takePictureIntent.resolveActivity(context.packageManager) != null) {
        // Create the File where the photo should go
        var photoFile: File? = null
        try {
            photoFile = createImageFile(context)
        } catch (ex: IOException) {
            // Error occurred while creating the File
        }
        // Continue only if the File was successfully created
        if (photoFile != null) {
            val photoURI: Uri = FileProvider.getUriForFile(context,
                    context.packageName,
                    photoFile)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            startActivityForResult(takePictureIntent, REQUEST_CAMERA)
        }
    }
}*/
