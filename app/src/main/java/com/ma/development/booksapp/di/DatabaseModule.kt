package com.ma.development.booksapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ma.development.booksapp.data.local.BooksDao
import com.ma.development.booksapp.data.local.BooksDb
import com.ma.development.booksapp.domain.utils.Books_LOCAL_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideBooksDatabase(@ApplicationContext context: Context): BooksDb =
        Room.databaseBuilder(
            context,
            BooksDb::class.java,
            Books_LOCAL_DATABASE_NAME
        ).fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun provideBooksDao(booksDb: BooksDb): BooksDao = booksDb.getDao()
}


