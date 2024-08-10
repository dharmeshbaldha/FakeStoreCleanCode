package com.example.fakestorecleancode.data.repository

import com.example.fakestorecleancode.data.api.ProductApi
import com.example.fakestorecleancode.data.dto.ProductDetailDto
import com.example.fakestorecleancode.data.dto.ProductListDto
import com.example.fakestorecleancode.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productApi: ProductApi) : ProductRepository {

    override suspend fun getProductList(): List<ProductListDto> {
        return productApi.getProductList()
    }

    override suspend fun getProductDetail(pageNumber: Int): ProductDetailDto {
        return productApi.getProductDetail(pageNumber)
    }
}