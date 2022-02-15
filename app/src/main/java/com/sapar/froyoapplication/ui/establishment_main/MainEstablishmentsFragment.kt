package com.sapar.froyoapplication.ui.establishment_main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.ThreeBounce
import com.sapar.froyoapplication.R
import com.sapar.froyoapplication.data.establishment.EstablishmentsItem
import com.sapar.froyoapplication.databinding.FragmentMainEstablishmentsBinding
import com.sapar.froyoapplication.ui.establishment_main.adapter.EstablishmentsAdapter

class MainEstablishmentsFragment : Fragment(R.layout.fragment_main_establishments) {

    private var _binding: FragmentMainEstablishmentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var establishmentsAdapter: EstablishmentsAdapter
    private lateinit var viewModel: MainEstablishmentsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainEstablishmentsViewModel::class.java]
        _binding = FragmentMainEstablishmentsBinding.bind(view)

        initRecyclerView()
        initProgressBar()
        initViewModel()
    }

    private fun initRecyclerView() {
        establishmentsAdapter = EstablishmentsAdapter {
            onNoteClick(it)
        }
        binding.recyclerViewEstablishments.adapter = establishmentsAdapter

        if (establishmentsAdapter.items.isEmpty()) {
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    private fun initProgressBar() {
        val doubleBounce: Sprite = ThreeBounce()
        binding.progressBar.setIndeterminateDrawable(doubleBounce)
    }

    private fun initViewModel() {
        val viewModel: MainEstablishmentsViewModel =
            ViewModelProvider(this)[MainEstablishmentsViewModel::class.java]
        viewModel.liveDataEstablishments.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.progressBar.visibility = View.GONE
                if (establishmentsAdapter.items.isEmpty()) {
                    establishmentsAdapter.items = it
                }
            } else {
                Toast.makeText(requireContext(), "Error in getting list...", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        viewModel.loadEstablishmentsApi()
    }

    private fun onNoteClick(establishment: EstablishmentsItem) {
        val action =
            MainEstablishmentsFragmentDirections.actionMainEstablishmentsFragmentToEstablishmentDetailFragment(
                establishment.name,
                establishment
            )
        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}