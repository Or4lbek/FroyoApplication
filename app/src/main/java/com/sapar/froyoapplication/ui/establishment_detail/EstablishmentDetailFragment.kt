package com.sapar.froyoapplication.ui.establishment_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import com.sapar.froyoapplication.R
import com.sapar.froyoapplication.databinding.EstablishmentDetailFragmentBinding
import com.sapar.froyoapplication.model.menu.Category
import com.sapar.froyoapplication.ui.establishment_detail.adapter.CategoryAdapter
import com.sapar.froyoapplication.ui.establishment_detail.adapter.CategoryContainerAdapter
import com.sapar.froyoapplication.ui.establishment_detail.adapter.ParentMenuAdapter

class EstablishmentDetailFragment : Fragment(R.layout.establishment_detail_fragment) {

    private var _binding: EstablishmentDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EstablishmentDetailViewModel
    private val parentMenuAdapter: ParentMenuAdapter = ParentMenuAdapter()
    private lateinit var categoryContainerAdapter: CategoryContainerAdapter
    private val categoryAdapter = CategoryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = EstablishmentDetailFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this)[EstablishmentDetailViewModel::class.java]
        binding.initUI()
        setObserver()
    }

    private fun setObserver() {
        viewModel.liveData.observe(viewLifecycleOwner) { result ->
            val category = result.mapIndexed { index, categoryMeal ->
                if (index == 0) {
                    Category(categoryMeal.category, true)
                } else Category(categoryMeal.category, false)
            }
            categoryAdapter.items = category
            parentMenuAdapter.items = result
        }
    }

    private fun EstablishmentDetailFragmentBinding.initUI() {
        categoryContainerAdapter = CategoryContainerAdapter(categoryAdapter)
        val concatAdapter = ConcatAdapter(categoryContainerAdapter, parentMenuAdapter)
        menuRv.adapter = concatAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}