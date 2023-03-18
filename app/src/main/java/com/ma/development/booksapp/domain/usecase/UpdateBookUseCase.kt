package com.ma.development.booksapp.domain.usecase

import com.ma.development.booksapp.domain.model.Book
import com.ma.development.booksapp.domain.repository.BooksRepository
import javax.inject.Inject

class UpdateBookUseCase @Inject constructor(private val booksRepository: BooksRepository) {

    suspend fun execute(book: Book) = booksRepository.updateBook(book)
}