package com.ma.development.booksapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ma.development.booksapp.domain.model.Book

@Dao
interface BooksDao {

    @Query("select * from books_table order by `key`")
    fun getBooks(): LiveData<List<Book>>

    @Query("select * from books_table where name like :bookName order by `key`")
    fun getBooksByName(bookName: String): LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBooks(books: List<Book>)

    @Update
    suspend fun updateBook(book: Book)


}