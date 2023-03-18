package com.ma.development.booksapp.data.local

import androidx.lifecycle.LiveData
import com.ma.development.booksapp.domain.model.Book
import javax.inject.Inject

class BooksLocalDataSourceImpl @Inject constructor(private val dao: BooksDao) : BooksLocalDataSource {
    override fun getBooks(): LiveData<List<Book>> = dao.getBooks()
    override fun getBooksByName(bookName: String): LiveData<List<Book>> = dao.getBooksByName("%$bookName%")

    override suspend fun insertBooks(books: List<Book>) {
        dao.insertBooks(books)
    }

    override suspend fun updateBook(books: Book) {
        dao.updateBook(books)
    }
}