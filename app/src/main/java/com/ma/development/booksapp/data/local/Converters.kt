package com.ma.development.booksapp.data.local

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class Converters {
    @TypeConverter
    fun toBitmap(byteArray: ByteArray?): Bitmap? {
        return byteArray?.let {
            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        }
    }

    @TypeConverter
    fun fromBitmap(btm: Bitmap?): ByteArray? {
        return btm?.let {
            val outputStream = ByteArrayOutputStream()
            btm.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.toByteArray()
        }
    }
}