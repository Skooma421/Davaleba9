import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.davaleba9.CategoryTypes
import com.example.davaleba9.R
import com.example.davaleba9.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val categories: List<CategoryTypes>,
    private val onCategorySelected: (CategoryTypes) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedCategory: CategoryTypes = categories[0]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category, category == selectedCategory)
    }

    override fun getItemCount(): Int = categories.size

    fun setSelectedCategory(category: CategoryTypes) {
        selectedCategory = category
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: CategoryTypes, isSelected: Boolean) {
            binding.categoryText.text = category.name

            val backgroundDrawable =
                ContextCompat.getDrawable(itemView.context, R.drawable.rounded_corner)?.mutate()

            if (isSelected) {
                backgroundDrawable?.setTint(
                    ContextCompat.getColor(itemView.context, R.color.highlightGreen)
                )
            } else {
                backgroundDrawable?.setTint(
                    ContextCompat.getColor(itemView.context, R.color.backgroundGrey)
                )
            }

            itemView.background = backgroundDrawable

            val textColor = if (isSelected) {
                ContextCompat.getColor(itemView.context, R.color.white)
            } else {
                ContextCompat.getColor(itemView.context, R.color.textColor)
            }

            binding.categoryText.setTextColor(textColor)

            itemView.setOnClickListener {
                onCategorySelected(category)
                setSelectedCategory(category)
            }
        }
    }

}

