package com.sapar.froyoapplication.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.ThreeBounce
import com.sapar.froyoapplication.R
import com.sapar.froyoapplication.databinding.MainEstablishmentsFragmentBinding
import com.sapar.froyoapplication.model.EstablishmentsItem
import com.sapar.froyoapplication.view.adapter.EstablishmentsAdapter
import com.sapar.froyoapplication.viewmodel.MainEstablishmentsViewModel

class MainEstablishmentsFragment : Fragment(R.layout.main_establishments_fragment),
    EstablishmentsAdapter.OnItemNoteListener {

    private lateinit var viewModel: MainEstablishmentsViewModel
    private var _binding: MainEstablishmentsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var establishmentsAdapter: EstablishmentsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainEstablishmentsViewModel::class.java]
        _binding = MainEstablishmentsFragmentBinding.bind(view)

        initRecyclerView()
        initProgressBar()
        initViewModel()
    }

    private fun initRecyclerView() {
        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewEstablishments.layoutManager = linearLayoutManager
        establishmentsAdapter = EstablishmentsAdapter(requireContext(), this)
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
        viewModel.liveDataEstablishments.observe(this, {
            if (it != null) {
                binding.progressBar.visibility = View.GONE
                if (establishmentsAdapter.items.isEmpty()) {
                    establishmentsAdapter.items = it
                }
            } else {
                Toast.makeText(requireContext(), "Error in getting list...", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        viewModel.loadEstablishmentsApi()
    }

    override fun onNoteClick(position: Int) {
        val establishment: EstablishmentsItem = establishmentsAdapter.items[position]
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