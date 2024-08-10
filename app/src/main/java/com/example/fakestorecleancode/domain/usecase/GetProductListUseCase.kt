package com.example.fakestorecleancode.domain.usecase

import com.example.fakestorecleancode.common.utils.NetworkResult
import com.example.fakestorecleancode.common.utils.safeAPICall
import com.example.fakestorecleancode.data.dto.ProductListDto
import com.example.fakestorecleancode.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke(): NetworkResult<List<ProductListDto>> {
        val result = repository.getProductList()
        return safeAPICall(Dispatchers.IO, result)
    }
}