package com.ma.development.booksapp.di

import com.ma.development.booksapp.data.local.BooksLocalDataSource
import com.ma.development.booksapp.data.local.BooksLocalDataSourceImpl
import com.ma.development.booksapp.data.remote.BookBookRemoteDataSourceImpl
import com.ma.development.booksapp.data.remote.BookRemoteDataSource
import com.ma.development.booksapp.data.repository.BooksRepositoryImpl
import com.ma.development.booksapp.domain.repository.BooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun provideBooksRemoteDataSource(bookRemoteDataSourceImpl: BookBookRemoteDataSourceImpl): BookRemoteDataSource

    @Singleton
    @Binds
    abstract fun provideBooksLocalDataSource(booksLocalDataSourceImpl: BooksLocalDataSourceImpl): BooksLocalDataSource


    @Singleton
    @Binds
    abstract fun provideBooksRepository(booksRepositoryImpl: BooksRepositoryImpl): BooksRepository


}