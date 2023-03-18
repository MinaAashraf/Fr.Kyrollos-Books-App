package com.ma.development.booksapp.presentation

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.test.core.app.ApplicationProvider
import com.ma.development.booksapp.R
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    //  verifyStoragePermission()
    //    findViewById<Button>(R.id.view_btn).setOnClickListener { displayPdf() }

    }
/*    // Static CONSTANT VALUE
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSION_STORAGE = arrayOf<String>(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private fun displayPdf() {
        *//* val localUrl =
             Environment.DIRECTORY_DOWNLOADS + getString(R.string.folder_name) + "Book" + ".pdf"
         val intent =
             Intent(Intent.ACTION_VIEW).apply {
                 setDataAndType(Uri.parse(localUrl), "application/pdf")
             }
         startActivity(Intent.createChooser(intent, "choose"))*//*


        // Get the URI Path of file.

        // Get the URI Path of file.
        val uriPdfPath = FileProvider.getUriForFile(
            this,
            ApplicationProvider.getApplicationContext<Context>().packageName + ".provider",
            File(Environment.DIRECTORY_DOWNLOADS + getString(R.string.folder_name) + "Book" + ".pdf")
        )

        // Start Intent to View PDF from the Installed Applications.

        // Start Intent to View PDF from the Installed Applications.
        val pdfOpenIntent = Intent(Intent.ACTION_VIEW)
        pdfOpenIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        pdfOpenIntent.clipData = ClipData.newRawUri("", uriPdfPath)
        pdfOpenIntent.setDataAndType(uriPdfPath, "application/pdf")
        pdfOpenIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        try {
            startActivity(pdfOpenIntent)
        } catch (activityNotFoundException: ActivityNotFoundException) {
            Toast.makeText(this, "There is no app to load corresponding PDF", Toast.LENGTH_LONG)
                .show()
        }
    }

    //Permissions Check
    fun verifyStoragePermission() {
        val permission = ActivityCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        // Surrounded with if statement for Android R to get access of complete file.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager() && permission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    PERMISSION_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
                )

                // Abruptly we will ask for permission once the application is launched for sake demo.
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
        }
    }*/
}