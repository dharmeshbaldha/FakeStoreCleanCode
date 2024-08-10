package com.example.fakestorecleancode.presentation.productlist

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakestorecleancode.R
import com.example.fakestorecleancode.data.dto.ProductListDto
import com.example.fakestorecleancode.databinding.RawProductListBinding

class ProductAdapter(private val data: List<ProductListDto>, val itemClickListener: (ProductListDto) -> Unit) : RecyclerView.Adapter<ProductAdapter
    .ProductHolder>
() {

    private lateinit var binding: RawProductListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        binding = RawProductListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class ProductHolder(private val binding: RawProductListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemData: ProductListDto) {
            binding.apply {
                Glide.with(this.root.context)
                    .load(itemData.image)
                    .fitCenter()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(icon)

                title.text = itemData.title

                root.setOnClickListener {
                    itemClickListener(itemData)
                }
            }
        }
    }
}