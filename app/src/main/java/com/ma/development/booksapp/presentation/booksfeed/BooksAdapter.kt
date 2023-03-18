package com.ma.development.booksapp.presentation.booksfeed

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.ma.development.booksapp.databinding.ItemLayoutBinding
import com.ma.development.booksapp.domain.model.Book
import kotlinx.coroutines.*

class BooksAdapter(
    private val context: Context,
    private val itemClickListener: ItemClickListener,
    private val itemImageCachingHandler: ItemImageCachingHandler
) : ListAdapter<Book, BooksAdapter.MyViewHolder>(DiffUtilsCallBack()) {
    inner class MyViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(book: Book) {
            binding.book = book

           // Log.d("bitmap", book.imageBitmap.toString())

           /* book.imageBitmap?.let {
                Log.d("cashing", "cached")
                binding.bookImg.setImageBitmap(it)
            } ?: run {
                Log.d("caching", "not cached")
                CoroutineScope(Dispatchers.IO).launch {
                    val bitmap = async { getBookBitmapFromUrl(book) }
                    bitmap.await()?.let {
                        withContext(Dispatchers.Main) {
                            binding.bookImg.setImageBitmap(it)
                        }
                        book.imageBitmap = it
                        itemImageCachingHandler.saveBookBitmap(book)
                    }
                }
            }*/


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MyViewHolder(binding)
        binding.root.setOnClickListener { itemClickListener.onItemClick(getItem(holder.adapterPosition)) }
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    interface ItemClickListener {
        fun onItemClick(book: Book)
    }

    interface ItemImageCachingHandler {
        suspend fun saveBookBitmap(book: Book)
    }

    private suspend fun getBookBitmapFromUrl(book: Book): Bitmap? {
        return book.image?.let {
            val request = ImageRequest.Builder(context).data(it).build()
            val result = (ImageLoader(context).execute(request) as SuccessResult).drawable
            (result as BitmapDrawable).bitmap
        }
    }

    class DiffUtilsCallBack : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
            newItem.key == oldItem.key

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean = newItem == oldItem

    }
}
