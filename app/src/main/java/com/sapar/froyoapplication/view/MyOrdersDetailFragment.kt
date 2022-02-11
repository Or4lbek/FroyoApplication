package com.sapar.froyoapplication.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.sapar.froyoapplication.R
import com.sapar.froyoapplication.databinding.MyOrdersDetailFragmentBinding
import com.sapar.froyoapplication.model.MyOrdersItem
import com.sapar.froyoapplication.viewmodel.MyOrdersDetailViewModel

class MyOrdersDetailFragment : Fragment(R.layout.my_orders_detail_fragment) {

    private var _binding: MyOrdersDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: MyOrdersDetailFragmentArgs by navArgs()
    private lateinit var viewModel: MyOrdersDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MyOrdersDetailFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this)[MyOrdersDetailViewModel::class.java]

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