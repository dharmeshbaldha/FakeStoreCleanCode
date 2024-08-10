package com.example.fakestorecleancode.domain.repository

import com.example.fakestorecleancode.data.dto.ProductDetailDto
import com.example.fakestorecleancode.data.dto.ProductListDto

interface ProductRepository {

    suspend fun getProductList(): List<ProductListDto>

    suspend fun getProductDetail(pageNumber: Int): ProductDetailDto

}