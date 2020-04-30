package com.example.wasteless.page.grocery_list

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.example.wasteless.GroceryListBinding
import com.example.wasteless.R
import com.example.wasteless.remote.model.GroceryList
import com.example.wasteless.shared.base.BaseFragment
import com.example.wasteless.shared.utils.extensions.observeNonNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class GroceryListsFragment :
    BaseFragment<GroceryListBinding, GroceryListsViewModel>(R.layout.fragment_grocerylists) {

    override val viewModel by viewModel<GroceryListsViewModel>()
    private val adapter by lazy {
        GroceryListsAdapter { handleOnCampaignItemClick(it) }
    }

    private fun handleOnCampaignItemClick(it: GroceryList) {
        println("List " + it.listName + " clicked")
        viewModel.goToListItems(it.id)
    }

    override fun setupViews() {
        binding?.listsRecycler?.adapter = adapter
        setupObservers()

        viewModel.navigateToGroceryItemsFragment.observeNonNull(this) {
            if(it)
                findNavController()
                    .navigate(R.id.action_groceryListsFragment_to_groceryItemsFragment
                        , Bundle().apply {
                        putInt("LIST_ID", -1)
                    })
        }
    }

    private fun setupObservers() {
        viewModel.groceryLists.observeNonNull(this) { adapter.submitList(it) }
    }
}