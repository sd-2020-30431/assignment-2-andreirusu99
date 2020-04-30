package com.example.wasteless.page.grocery_list

import androidx.recyclerview.widget.DiffUtil
import com.example.wasteless.ItemGroceryListBinding
import com.example.wasteless.R
import com.example.wasteless.remote.model.GroceryList
import com.example.wasteless.shared.base.BaseAdapter

class GroceryListsAdapter(private val onItemClick: (GroceryList) -> Unit) :
    BaseAdapter<GroceryList, ItemGroceryListBinding>(
        R.layout.item_grocery_list,
        object : DiffUtil.ItemCallback<GroceryList>() {
            override fun areItemsTheSame(oldItem: GroceryList, newItem: GroceryList) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GroceryList, newItem: GroceryList) =
                oldItem == newItem
        }) {

    override fun bind(binding: ItemGroceryListBinding, item: GroceryList) {
        with(binding) {
            groceryList = item
            layoutGroceryLists.setOnClickListener { onItemClick(item) }
        }
    }
}
