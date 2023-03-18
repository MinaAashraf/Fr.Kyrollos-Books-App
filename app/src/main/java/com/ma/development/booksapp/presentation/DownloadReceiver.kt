package com.ma.development.booksapp.presentation

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import com.ma.development.booksapp.BuildConfig
import com.ma.development.booksapp.R
import com.ma.development.booksapp.presentation.booksfeed.BooksFragment
import java.io.File

class DownloadReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            val action = intent.action
              if (DownloadManager.ACTION_DOWNLOAD_COMPLETE == action) {
                  BooksFragment.dialog.dismiss()
                  displayPdf(context!!)
              }
        }
    }

    private fun displayPdf(context: Context) {
        val file = File(
            Environment.getExternalStorageDirectory(),
            "Download/${context.getString(R.string.folder_name)}/${BooksFragment.bookName}.pdf"
        )
        val path = FileProvider.getUriForFile(
            context,
            BuildConfig.APPLICATION_ID + ".provider",
            file
        )
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(path, "application/pdf")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        context.startActivity(intent)
    }
}