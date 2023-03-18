package com.ma.development.booksapp.domain.repository

import androidx.lifecycle.LiveData
import com.ma.development.booksapp.domain.model.Book
import com.ma.development.booksapp.domain.utils.Result

interface BooksRepository {

    suspend fun refreshBooks(): Result<MutableList<Book>>
    fun getBooks(): LiveData<List<Book>>
    fun getBooksByName(bookName:String): LiveData<List<Book>>
    suspend fun updateBook(book: Book)

}