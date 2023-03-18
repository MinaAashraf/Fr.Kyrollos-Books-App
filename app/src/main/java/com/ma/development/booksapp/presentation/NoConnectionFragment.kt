package com.ma.development.booksapp.presentation

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ma.development.booksapp.R
import com.ma.development.booksapp.databinding.FragmentNoConnectionBinding
import com.ma.development.booksapp.presentation.utils.checkForInternet
import com.ma.development.booksapp.presentation.utils.showSnackBar


class NoConnectionFragment : Fragment() {
    private val binding by lazy { FragmentNoConnectionBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.tryAgainBtn.setOnClickListener {
            if (tryAgainOnClick()) {
                findNavController().navigate(
                    R.id.action_noConnectionFragment_to_booksFragment
                )
            }
        }
        return binding.root
    }

    private fun tryAgainOnClick(): Boolean {
        val isConnected = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkForInternet(requireActivity())
        } else
            false
        if (!isConnected)
            showSnackBar(
                binding.root,
                getString(R.string.internet_error_message),
                Snackbar.LENGTH_LONG
            )
        return isConnected
    }

}