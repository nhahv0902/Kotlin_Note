package com.nhahv.note.data.database

import android.arch.persistence.room.TypeConverter
import android.graphics.Picture
import com.google.gson.Gson

/**
 * Created by nhahv0902 on 6/26/17.
 * <>
 */

class Converters{
    companion object{
        @TypeConverter
        fun pictureToValue(picture: Picture): String{
            return Gson().toJson(picture)
        }

        @TypeConverter
        fun  valueToPicture(value : String) : Picture{
            return Gson().fromJson(value, Picture::class.java)
        }
    }
}
