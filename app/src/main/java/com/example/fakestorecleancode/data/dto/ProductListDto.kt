package com.example.fakestorecleancode.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProductListItem")
data class ProductListDto(
    @PrimaryKey(autoGenerate = true)
    val productId: Int,
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val title: String
)