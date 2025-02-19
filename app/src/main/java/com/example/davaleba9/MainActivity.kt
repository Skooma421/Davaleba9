package com.example.davaleba9

import CategoryAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.davaleba9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productList: MutableList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productList = generateData()
        productAdapter = ProductAdapter(this, productList)

        val categories = CategoryTypes.values().toList()
        categoryAdapter = CategoryAdapter(categories) { selectedCategory ->
            Toast.makeText(
                this,
                "Selected Category ${selectedCategory.name}",
                Toast.LENGTH_SHORT
            ).show()
            updateProductList(selectedCategory)
        }

        binding.categoryRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecycler.adapter = categoryAdapter

        binding.itemRecycler.layoutManager = GridLayoutManager(this, 2)
        binding.itemRecycler.adapter = productAdapter
    }

    private fun generateData(): MutableList<Item> {
        return mutableListOf(
            Item(R.drawable.ic_launcher_background, "Blazer", 120.99, CategoryTypes.CAMPING),
            Item(R.drawable.ic_launcher_background, "Blazer", 120.99, CategoryTypes.CATEGORY1),
            Item(R.drawable.ic_launcher_background, "Blazer", 120.99, CategoryTypes.CATEGORY2),
            Item(R.drawable.ic_launcher_background, "Pantalones", 120.99, CategoryTypes.PARTY),
            Item(R.drawable.ic_launcher_background, "Pantalones", 120.99, CategoryTypes.CATEGORY3),
            Item(R.drawable.ic_launcher_background, "Pantalones", 120.99, CategoryTypes.CATEGORY3),
            Item(R.drawable.ic_launcher_background, "Pantalones", 120.99, CategoryTypes.CATEGORY3),
        )
    }

    private fun updateProductList(category: CategoryTypes) {
        val filteredList = if(category == CategoryTypes.ALL){
            productList
        }else{
            productList.filter { it.categoryTypes == category }
        }
        productAdapter.updateData(filteredList)
    }
}
