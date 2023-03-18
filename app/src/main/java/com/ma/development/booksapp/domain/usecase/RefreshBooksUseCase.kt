package com.ma.development.booksapp.domain.usecase

import com.ma.development.booksapp.domain.repository.BooksRepository
import javax.inject.Inject

class RefreshBooksUseCase @Inject constructor(private val booksRepository: BooksRepository) {

    suspend fun execute() = booksRepository.refreshBooks()
}