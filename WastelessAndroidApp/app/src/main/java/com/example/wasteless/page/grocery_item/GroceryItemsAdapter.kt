package com.example.wasteless.page.grocery_item

import com.example.wasteless.shared.base.BaseAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.wasteless.ItemGroceryItemBinding
import com.example.wasteless.R
import com.example.wasteless.remote.model.GroceryItem

class GroceryItemsAdapter (private val onItemClick: (GroceryItem) -> Unit) :
    BaseAdapter<GroceryItem, ItemGroceryItemBinding>(
        R.layout.item_grocery_list,
        object : DiffUtil.ItemCallback<GroceryItem>() {
            override fun areItemsTheSame(oldItem: GroceryItem, newItem: GroceryItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GroceryItem, newItem: GroceryItem) =
                oldItem == newItem
        }
    ){

    override fun bind(binding: ItemGroceryItemBinding, item: GroceryItem) {
        with(binding) {
            groceryItem = item
            layoutGroceryItems.setOnClickListener { onItemClick(item) }
        }
    }

}