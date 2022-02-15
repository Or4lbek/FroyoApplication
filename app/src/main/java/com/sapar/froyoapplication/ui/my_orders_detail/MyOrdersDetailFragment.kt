package com.sapar.froyoapplication.ui.my_orders_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.sapar.froyoapplication.R
import com.sapar.froyoapplication.data.my_orders.MyOrdersItem
import com.sapar.froyoapplication.databinding.FragmentMyOrdersDetailBinding

class MyOrdersDetailFragment : Fragment(R.layout.fragment_my_orders_detail) {

    private var _binding: FragmentMyOrdersDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyOrdersDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMyOrdersDetailBinding.bind(view)
        viewModel = ViewModelProvider(this)[MyOrdersDetailViewModel::class.java]
        val args: MyOrdersDetailFragmentArgs by navArgs()
        val myOrder: MyOrdersItem = args.myOrder
        updateUi(myOrder)
    }

    private fun updateUi(myOrder: MyOrdersItem) {
        with(binding) {
            textViewMyOrderName.text = myOrder.order_name
            textViewMyOrderAddress.text = myOrder.order_address
            textViewMyOrderDate.text = myOrder.order_date
            textViewMyOrderNumber.text = myOrder.order_number
            textViewMyOrderStatus.text = myOrder.order_status
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}