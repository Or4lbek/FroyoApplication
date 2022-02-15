package com.sapar.froyoapplication.ui.my_orders

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Pulse
import com.sapar.froyoapplication.R
import com.sapar.froyoapplication.data.my_orders.MyOrdersItem
import com.sapar.froyoapplication.databinding.FragmentMyOrdersBinding
import com.sapar.froyoapplication.ui.my_orders.adapter.MyOrdersAdapter

class MyOrdersFragment : Fragment(R.layout.fragment_my_orders) {

    private var _binding: FragmentMyOrdersBinding? = null
    private val binding get() = _binding!!
    private lateinit var myOrdersAdapter: MyOrdersAdapter
    private lateinit var viewModel: MyOrdersViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMyOrdersBinding.bind(view)
        viewModel = ViewModelProvider(this)[MyOrdersViewModel::class.java]

        initRecyclerView()
        initProgressBar()
        initViewModel()
    }

    private fun initRecyclerView() {

        myOrdersAdapter = MyOrdersAdapter { onNoteClick(it) }
        binding.recyclerViewMyOrders.adapter = myOrdersAdapter

        if (myOrdersAdapter.items.isEmpty()) {
            binding.progressBarMyOrders.visibility = View.VISIBLE
        }
    }

    private fun initProgressBar() {
        val doubleBounce: Sprite = Pulse()
        binding.progressBarMyOrders.setIndeterminateDrawable(doubleBounce)
    }

    private fun initViewModel() {
        viewModel.liveDataMyOrders.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.progressBarMyOrders.visibility = View.GONE
                if (myOrdersAdapter.items.isEmpty()) {
                    myOrdersAdapter.items = it
                }
            } else {
                Toast.makeText(requireContext(), "Error in getting list...", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        viewModel.loadMyOrdersApi()
    }

    private fun onNoteClick(it: MyOrdersItem) {
        val action = MyOrdersFragmentDirections.actionMyOrdersFragmentToMyOrdersDetailFragment(
            it.order_name,
            it
        )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}