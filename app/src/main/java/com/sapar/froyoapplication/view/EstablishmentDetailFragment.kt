package com.sapar.froyoapplication.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sapar.froyoapplication.R
import com.sapar.froyoapplication.viewmodel.EstablishmentDetailViewModel

class EstablishmentDetailFragment : Fragment(R.layout.establishment_detail_fragment) {

    private lateinit var viewModel: EstablishmentDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[EstablishmentDetailViewModel::class.java]
    }

}