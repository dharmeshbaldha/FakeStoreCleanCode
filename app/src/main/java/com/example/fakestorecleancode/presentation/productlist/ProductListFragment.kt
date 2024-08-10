package com.example.fakestorecleancode.presentation.productlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.fakestorecleancode.R
import com.example.fakestorecleancode.common.utils.Constant
import com.example.fakestorecleancode.common.utils.NetworkResult
import com.example.fakestorecleancode.data.dto.ProductListDto
import com.example.fakestorecleancode.databinding.ActivityMainBinding
import com.example.fakestorecleancode.databinding.FragmentProductListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding

    private val viewModel by viewModels<ProductListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.productList.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {}
                is NetworkResult.Success -> {

                    it.data?.let { data -> binding.listView.adapter = ProductAdapter(data, ::onItemClickListener) }

                    Log.d(Constant.TAG, "SUCCESS \n ${it.data?.size.toString()}")
                }

                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun onItemClickListener(itemData: ProductListDto) {
        Toast.makeText(requireContext(), itemData.title, Toast.LENGTH_SHORT).show()
    }

}