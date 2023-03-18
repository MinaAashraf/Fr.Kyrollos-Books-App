package com.ma.development.booksapp.presentation.booksfeed


import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.ma.development.booksapp.domain.model.Book


@BindingAdapter("image")
fun bindImage(imageView: ImageView, image: String?) {
    image?.let {
        imageView.load(image)
    }
}
