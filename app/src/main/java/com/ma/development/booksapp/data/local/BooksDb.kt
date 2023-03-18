package com.ma.development.booksapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ma.development.booksapp.domain.model.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BooksDb : RoomDatabase() {
    abstract fun getDao() : BooksDao

}