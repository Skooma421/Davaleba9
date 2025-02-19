package com.example.davaleba9

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.davaleba9.databinding.ItemProductsBinding

class ProductAdapter(
    private val context: Context,
    private var itemList: List<Item>

) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = itemList.size

    fun updateData(newList: List<Item>) {
        itemList = newList
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(private val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                productImage.setImageResource(item.image)
                productDescription.text = item.title
                productPrice.text = "${item.price}$"
            }
        }
    }
}
