package com.sapar.froyoapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sapar.froyoapplication.R
import com.sapar.froyoapplication.databinding.RaqmetFragmentBinding
import com.sapar.froyoapplication.model.const.RAQMET_URL
import com.sapar.froyoapplication.viewmodel.RaqmetViewModel

class RaqmetFragment : Fragment(R.layout.raqmet_fragment) {

    private var _binding: RaqmetFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RaqmetViewModel

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RaqmetViewModel::class.java]
        _binding = RaqmetFragmentBinding.bind(view)

        binding.webView.apply {
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    url: String
                ): Boolean {
                    view.loadUrl(url)
                    return true
                }
            }
            loadUrl(RAQMET_URL)
            settings.javaScriptEnabled = true
            settings.allowContentAccess = true
            settings.domStorageEnabled = true
            settings.useWideViewPort = true
        }
    }

    // to avoid memory leak
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}