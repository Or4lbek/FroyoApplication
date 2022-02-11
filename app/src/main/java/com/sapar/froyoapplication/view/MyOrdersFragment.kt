package com.sapar.froyoapplication.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Pulse
import com.sapar.froyoapplication.R
import com.sapar.froyoapplication.databinding.MyOrdersFragmentBinding
import com.sapar.froyoapplication.model.MyOrdersItem
import com.sapar.froyoapplication.view.adapter.MyOrdersAdapter
import com.sapar.froyoapplication.viewmodel.MyOrdersViewModel

class MyOrdersFragment : Fragment(R.layout.my_orders_fragment),
    MyOrdersAdapter.OnItemNoteListener {

    private var _binding: MyOrdersFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var myOrdersAdapter: MyOrdersAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var viewModel: MyOrdersViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MyOrdersFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this)[MyOrdersViewModel::class.java]

        initRecyclerView()
        initProgressBar()
        initViewModel()
    }

    private fun initRecyclerView() {
        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewMyOrders.layoutManager = linearLayoutManager
        myOrdersAdapter = MyOrdersAdapter(requireContext(), this)
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
        viewModel.liveDataMyOrders.observe(this, {
            if (it != null) {
                binding.progressBarMyOrders.visibility = View.GONE
                if (myOrdersAdapter.items.isEmpty()) {
                    myOrdersAdapter.items = it
                }
            } else {
                Toast.makeText(requireContext(), "Error in getting list...", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        viewModel.loadMyOrdersApi()
    }

    override fun onNoteClick(position: Int) {
        val myOrder: MyOrdersItem = myOrdersAdapter.items[position]
        val action = MyOrdersFragmentDirections.actionMyOrdersFragmentToMyOrdersDetailFragment(
            myOrder.order_name,
            myOrder
        )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}