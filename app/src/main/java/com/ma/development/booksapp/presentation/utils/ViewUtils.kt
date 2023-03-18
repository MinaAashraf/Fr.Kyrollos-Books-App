package com.ma.development.booksapp.presentation.utils

import android.view.View
import androidx.core.view.ViewCompat
import com.google.android.material.snackbar.Snackbar


fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}


fun showSnackBar(view: View, message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    val snackbar = Snackbar.make(
        view,
        message,
        duration
    )
    ViewCompat.setLayoutDirection(snackbar.view, ViewCompat.LAYOUT_DIRECTION_RTL)
    snackbar.show()
}
