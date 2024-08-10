package com.example.fakestorecleancode.presentation.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestorecleancode.common.utils.NetworkResult
import com.example.fakestorecleancode.data.dto.ProductListDto
import com.example.fakestorecleancode.domain.usecase.GetProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val useCase: GetProductListUseCase): ViewModel() {

    private val _productList = MutableLiveData<NetworkResult<List<ProductListDto>>>()
    val productList: LiveData<NetworkResult<List<ProductListDto>>>
        get() = _productList


    init {
        getProductList()
    }

    private fun getProductList() {
        viewModelScope.launch {
            _productList.postValue(useCase.invoke())
        }
    }

}