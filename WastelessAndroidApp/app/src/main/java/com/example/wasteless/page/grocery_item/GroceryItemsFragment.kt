package com.example.wasteless.page.grocery_item

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.wasteless.GroceryItemBinding
import com.example.wasteless.R
import com.example.wasteless.remote.model.GroceryItem
import com.example.wasteless.shared.base.BaseFragment
import com.example.wasteless.shared.utils.extensions.observeNonNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class GroceryItemsFragment :
    BaseFragment<GroceryItemBinding, GroceryItemsViewModel>(R.layout.fragment_groceryitems){

    private val TAG = "GroceryItemsFragment"
    override val viewModel by viewModel<GroceryItemsViewModel>()
    private val adapter by lazy {
        GroceryItemsAdapter{ handleOnCampaignItemClick(it) }
    }

    private fun handleOnCampaignItemClick(it: GroceryItem) {
        println("Item " + it.itemName + " clicked")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("LIST_ID")?.let {
            Log.d("$TAG:onViewCreated:LISTD_ID argument: ", it.toString())
            viewModel.getListItemsFromAPIById(it)
        }
    }

    override fun setupViews() {
        binding?.itemsRecycler?.adapter = adapter
        setupObservers()
    }

    private fun setupObservers(){
        viewModel.groceryItems.observeNonNull(this){ adapter.submitList(it) }
    }
}