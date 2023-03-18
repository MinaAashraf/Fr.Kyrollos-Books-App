package com.ma.development.booksapp.data.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.ma.development.booksapp.domain.model.Book
import com.ma.development.booksapp.domain.utils.Result
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class BookBookRemoteDataSourceImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : BookRemoteDataSource {

    override suspend fun getBooks(): Result<MutableList<Book>> {
        val books = mutableListOf<Book>()
        return try {
            val snapshot = fireStore.collection("books").get().await()
            snapshot.documents.forEach {
                Log.d("firebase result: ", it.data.toString() )
                it.toObject(Book::class.java)?.let {
                    books.add(it)
                }
            }
            Log.d("firebase: ", "firebase eeee")
            return Result.Success(books)
        } catch (e: Exception) {
            Log.d("firebase error: ", e.message.toString())
            Result.Failure(e)
        }

    }
}