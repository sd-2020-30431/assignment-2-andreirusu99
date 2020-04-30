package com.example.wasteless.page.grocery_item

import com.example.wasteless.GroceryItemBinding
import com.example.wasteless.R
import com.example.wasteless.remote.model.GroceryItem
import com.example.wasteless.shared.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class GroceryItemFragment :
    BaseFragment<GroceryItemBinding, GroceryItemViewModel>(R.layout.fragment_groceryitems){

    override val viewModel by viewModel<GroceryItemViewModel>()
    private val adapter by lazy {
        GroceryItemAdapter{ handleOnCampaignItemClick(it) }
    }

    private fun handleOnCampaignItemClick(it: GroceryItem) {
        println("List " + it.itemName + " clicked")
    }

    override fun setupViews() {
        binding?.itemsRecycler?.adapter = adapter
        setupObservers()
    }

    private fun setupObservers(){

    }
}