package com.nhahv.note.util

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */


object DataUtil {
    @JvmStatic val NOTE_TAG = "NOTE_TAG"
    @JvmStatic val JPEG_FILE_PREFIX = "IMG_"
    @JvmStatic val JPEG_FILE_SUFFIX = ".jpg"
}

object TimeUtil {
    @JvmStatic val TIME_DELAY: Long = 800L
}

object Font {
    @JvmStatic val CALLIGRAPHY = "Calligraphy.ttf"
}

object BundleConstant {
    @JvmStatic val BUNDLE_SECURITY_TYPE: String = "BUNDLE_SECURITY_TYPE"
    @JvmStatic val BUNDLE_PASSWORD: String = "BUNDLE_PASSWORD"
    @JvmStatic val BUNDLE_FOLDERS: String = "BUNDLE_FOLDERS"
    @JvmStatic val BUNDLE_IMAGES: String = "BUNDLE_IMAGES"
    @JvmStatic val BUNDLE_POSITION: String = "BUNDLE_POSITION"
    @JvmStatic val BUNDLE_FILE_URI: String = "BUNDLE_FILE_URI"
    @JvmStatic val BUNDLE_FILE_NAME: String = "BUNDLE_FILE_NAME"
}

object Request {
    @JvmStatic val REQUEST_SECURITY: Int = 1
    @JvmStatic val REQUEST_REMINDER: Int = 2
    @JvmStatic val REQUEST_PICK_IMAGE: Int = 3
    @JvmStatic val REQUEST_NOTE_PREVIEW: Int = 4
    @JvmStatic val REQUEST_PLACE_ADDRESS: Int = 5
    @JvmStatic val REQUEST_CREATE_NOTE: Int = 6
}

object FirebaseKey {
    @JvmStatic val NOTEBOOK: String = "NoteBook"
}