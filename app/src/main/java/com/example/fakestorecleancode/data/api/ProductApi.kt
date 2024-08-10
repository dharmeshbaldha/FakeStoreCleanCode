package com.example.fakestorecleancode.data.api

import com.example.fakestorecleancode.data.dto.ProductDetailDto
import com.example.fakestorecleancode.data.dto.ProductListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("/products")
    suspend fun getProductList(): List<ProductListDto>

    @GET("/products/{pageNumber}")
    suspend fun getProductDetail(@Path("pageNumber") pageNumber: Int): ProductDetailDto

}