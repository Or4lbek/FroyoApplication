package com.sapar.froyoapplication.ui.establishment_detail

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.sapar.froyoapplication.R
import com.sapar.froyoapplication.data.menu.Category
import com.sapar.froyoapplication.data.menu.Meal
import com.sapar.froyoapplication.databinding.FragmentEstablishmentDetailBinding
import com.sapar.froyoapplication.ui.establishment_detail.adapter.CategoryAdapter
import com.sapar.froyoapplication.ui.establishment_detail.adapter.ChildMenuAdapter
import com.sapar.froyoapplication.ui.establishment_detail.adapter.ParentMenuAdapter


class EstablishmentDetailFragment : Fragment(R.layout.fragment_establishment_detail),
    ChildMenuAdapter.ChildMenuAdapterListener {

    private lateinit var menuLayoutManager: LinearLayoutManager
    private var _binding: FragmentEstablishmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EstablishmentDetailViewModel
    private val parentMenuAdapter: ParentMenuAdapter = ParentMenuAdapter(this)
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEstablishmentDetailBinding.bind(view)
        viewModel = ViewModelProvider(this)[EstablishmentDetailViewModel::class.java]
        binding.initUI()
        setObserver()

    }

    private fun setObserver() {
        viewModel.liveData.observe(viewLifecycleOwner) { result ->
            val category = result.map { categoryMeal ->
                Category(categoryMeal.category, false)
            }
            categoryAdapter.items = category
            parentMenuAdapter.items = result
            scrollListener()
        }
    }

    //SOLID
    private fun FragmentEstablishmentDetailBinding.initUI() {
        categoryAdapter = CategoryAdapter {
            onClickCategory(it)
        }
        categoriesRv.adapter = categoryAdapter
        menuRv.adapter = parentMenuAdapter

        menuLayoutManager = LinearLayoutManager(requireContext())
        menuRv.layoutManager = menuLayoutManager
    }


    var categoryScrollingPos = 0
    var isCategoryScrolling = false
    private fun onClickCategory(pos: Int) {
        categoryScrollingPos = pos
        isCategoryScrolling = true
        binding.menuRv.smoothSnapToPosition(pos)
    }

    private fun scrollListener() {
        binding.menuRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val posFirst =
                    menuLayoutManager.findFirstVisibleItemPosition()
                if (posFirst == categoryScrollingPos) {
                    isCategoryScrolling = false
                }
                if (!isCategoryScrolling) {
                    categoryAdapter.selectItem(posFirst)
                    binding.categoriesRv.scrollToPosition(posFirst)
                }
            }
        })
    }

    private fun RecyclerView.smoothSnapToPosition(
        position: Int,
        snapMode: Int = LinearSmoothScroller.SNAP_TO_START
    ) {
        val smoothScroller = object : LinearSmoothScroller(this.context) {
            override fun getVerticalSnapPreference(): Int = snapMode
            override fun getHorizontalSnapPreference(): Int = snapMode
            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
                return 500 / this@smoothSnapToPosition.computeVerticalScrollRange().toFloat()
            }
        }
        smoothScroller.targetPosition = position
        layoutManager?.startSmoothScroll(smoothScroller)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onChangeCount(meal: Meal) {
        Toast.makeText(requireContext(), "${meal.price * meal.counter}", Toast.LENGTH_SHORT).show()
        viewModel.totalPrice += meal.price * meal.counter
        binding.btnBasket.apply {
            isVisible = viewModel.totalPrice > 0
            text = viewModel.totalPrice.toString()
        }
    }
}