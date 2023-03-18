package com.ma.development.booksapp.domain.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "books_table")
data class Book(
    @PrimaryKey
    val key: Int? = null,
    val name: String? = null,
    var imageBitmap: Bitmap? = null,
    val image: String? = null,
    val url: String? = null
)