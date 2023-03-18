package com.ma.development.booksapp.data.local

import androidx.lifecycle.LiveData
import com.ma.development.booksapp.domain.model.Book

interface BooksLocalDataSource {
    fun getBooks(): LiveData<List<Book>>
    fun getBooksByName(bookName:String): LiveData<List<Book>>
    suspend fun insertBooks(books: List<Book>)
    suspend fun updateBook(books: Book)
}