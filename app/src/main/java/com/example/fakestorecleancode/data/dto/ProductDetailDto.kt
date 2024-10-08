package com.example.fakestorecleancode.data.dto

data class ProductDetailDto(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val title: String
)