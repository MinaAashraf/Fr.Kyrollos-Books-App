package com.ma.development.booksapp.data.remote

import com.ma.development.booksapp.domain.model.Book
import com.ma.development.booksapp.domain.utils.Result

interface BookRemoteDataSource {

    suspend fun getBooks () : Result<MutableList<Book>>
}