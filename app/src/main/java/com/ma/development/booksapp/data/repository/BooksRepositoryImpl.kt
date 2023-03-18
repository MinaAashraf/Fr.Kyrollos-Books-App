package com.ma.development.booksapp.data.repository

import androidx.lifecycle.LiveData
import com.ma.development.booksapp.data.local.BooksLocalDataSource
import com.ma.development.booksapp.data.remote.BookRemoteDataSource
import com.ma.development.booksapp.domain.model.Book
import com.ma.development.booksapp.domain.repository.BooksRepository
import com.ma.development.booksapp.domain.utils.Result
import com.ma.development.booksapp.domain.utils.onSuccess
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val bookRemoteDataSource: BookRemoteDataSource,
    private val booksLocalDataSource: BooksLocalDataSource
) : BooksRepository {

    override suspend fun refreshBooks(): Result<MutableList<Book>> {
        val result = bookRemoteDataSource.getBooks()
        result.onSuccess {
            booksLocalDataSource.insertBooks(it)
        }
        return result
    }

    override fun getBooks(): LiveData<List<Book>> = booksLocalDataSource.getBooks()
    override fun getBooksByName(bookName: String): LiveData<List<Book>> = booksLocalDataSource.getBooksByName(bookName)

    override suspend fun updateBook(book: Book) {
        booksLocalDataSource.updateBook(book)
    }





}