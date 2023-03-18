package com.ma.development.booksapp.domain.usecase

import androidx.lifecycle.LiveData
import com.ma.development.booksapp.domain.model.Book
import com.ma.development.booksapp.domain.repository.BooksRepository
import javax.inject.Inject


class GetBooksUseCase @Inject constructor(private val booksRepository: BooksRepository) {

    fun execute(): LiveData<List<Book>> = booksRepository.getBooks()
}