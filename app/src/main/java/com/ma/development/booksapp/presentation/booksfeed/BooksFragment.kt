package com.ma.development.booksapp.presentation.booksfeed

import android.app.AlertDialog
import android.app.DownloadManager
import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ma.development.booksapp.BuildConfig
import com.ma.development.booksapp.R
import com.ma.development.booksapp.databinding.DialogLayoutBinding
import com.ma.development.booksapp.databinding.FragmentBooksBinding
import com.ma.development.booksapp.domain.model.Book
import com.ma.development.booksapp.presentation.utils.*
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


@AndroidEntryPoint
class BooksFragment : Fragment(), BooksAdapter.ItemClickListener,
    BooksAdapter.ItemImageCachingHandler {

    private val binding by lazy { FragmentBooksBinding.inflate(layoutInflater) }
    private val viewModel: BooksFeedViewModel by viewModels()


    companion object {
        lateinit var bookName: String
        lateinit var dialog: AlertDialog

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeOnLiveData()
    }


    private lateinit var adapter: BooksAdapter

    private fun setUpViews() {
        setUpRecyclerView()
        setUpSearchView()
    }

    private fun setUpRecyclerView() {
        adapter = BooksAdapter(requireContext(), this, this)
        binding.recyclerView.adapter = adapter
    }

    private fun setUpSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isNotEmpty())
                        viewModel.setFilterType(FilterType.SEARCH, it)
                    else
                        viewModel.setFilterType(FilterType.ALL)
                }

                return true
            }

        })
    }

    private fun observeOnLiveData() {
        viewModel.apply {
            books.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
            notDataExists.observe(viewLifecycleOwner){
                if (it){
                    if (!handleNoInternetConnection())
                        findNavController().navigate(R.id.action_booksFragment_to_noConnectionFragment)
                }
            }
        }
    }


    override fun onItemClick(book: Book) {
        val file = File(
            Environment.getExternalStorageDirectory(),
            "Download/${getString(R.string.folder_name)}/${book.name}.pdf"
        )
        if (file.exists()) {
            displayPdf(file)
            return
        }
        showDownloadDialog(book)
    }


    private fun showDownloadDialog(book: Book) {
        val customDialogViewBinding = DialogLayoutBinding.inflate(layoutInflater)
        dialog = AlertDialog.Builder(context).apply {
            setView(customDialogViewBinding.root)
        }.create()
        dialog.show()
        customDialogViewBinding.book = book

        customDialogViewBinding.downloadBtn.setOnClickListener {
            if (handleNoInternetConnection()) {
                it.hide()
                customDialogViewBinding.progrssBar.show()
                downloadPdfFileFromUrl(book)
            }
        }
    }


    private fun downloadPdfFileFromUrl(book: Book) {

        val request = DownloadManager.Request(Uri.parse(book.url)).apply {
            setTitle(book.name)
            setMimeType("application/pdf")
            setAllowedOverMetered(true)

            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "${getString(R.string.folder_name)}/${book.name}.pdf"
            )
        }
        val downloadManager =
            requireActivity().getSystemService(Service.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
        bookName = book.name!!

    }

    private fun displayPdf(file: File) {
        val path = FileProvider.getUriForFile(
            requireContext(),
            BuildConfig.APPLICATION_ID + ".provider",
            file
        )

        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(path, "application/pdf")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(intent)
    }

    private fun handleNoInternetConnection(): Boolean {
        val isConnected = checkForInternet(requireActivity())
        if (!isConnected)
            showSnackBar(
                binding.root,
                getString(R.string.internet_error_message),
                Snackbar.LENGTH_LONG
            )
        return isConnected
    }

    override suspend fun saveBookBitmap(book: Book) {
        viewModel.updateBook(book)
    }


}