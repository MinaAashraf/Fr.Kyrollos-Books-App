package com.ma.development.booksapp.presentation.splach

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ma.development.booksapp.R
import com.ma.development.booksapp.databinding.FragmentSplachBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lifecycleScope.launch{
            delay(2000)
            findNavController().navigate(R.id.action_splashFragment_to_booksFragment)
        }
        return FragmentSplachBinding.inflate(layoutInflater).root
    }

}