package com.ma.development.booksapp.presentation.booksfeed

import androidx.lifecycle.*
import com.ma.development.booksapp.domain.model.Book
import com.ma.development.booksapp.domain.usecase.GetBooksByNameUseCase
import com.ma.development.booksapp.domain.usecase.GetBooksUseCase
import com.ma.development.booksapp.domain.usecase.RefreshBooksUseCase
import com.ma.development.booksapp.domain.usecase.UpdateBookUseCase
import com.ma.development.booksapp.presentation.utils.FilterType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksFeedViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val refreshBooksUseCase: RefreshBooksUseCase,
    private val updateBookUseCase: UpdateBookUseCase,
    private val getBooksByNameUseCase: GetBooksByNameUseCase
) :
    ViewModel() {

    private val filter = MutableLiveData(FilterType.ALL)
    val notDataExists = MutableLiveData<Boolean>()

    val books: LiveData<List<Book>> = Transformations.switchMap(filter) {
        return@switchMap when (it) {
            FilterType.SEARCH -> getBooksByNameUseCase.execute(searchName)
            else -> {
                val books = getBooksUseCase.execute()
                notDataExists.value = books.value?.isEmpty() ?: true
                books
            }
        }
    }

    init {
        refreshBooks()
    }

    private fun refreshBooks() {
        viewModelScope.launch {
            refreshBooksUseCase.execute()
        }
    }

    fun updateBook(book: Book) {
        viewModelScope.launch {
            updateBookUseCase.execute(book)
        }
    }

    private var searchName = ""
    fun setFilterType(filterType: FilterType, name: String = "") {
        searchName = name
        filter.value = filterType
    }

}