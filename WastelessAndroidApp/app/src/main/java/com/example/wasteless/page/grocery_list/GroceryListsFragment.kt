package com.example.wasteless.page.grocery_list

import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.example.wasteless.GroceryListBinding
import com.example.wasteless.R
import com.example.wasteless.remote.model.GroceryList
import com.example.wasteless.shared.base.BaseFragment
import com.example.wasteless.shared.utils.extensions.observeNonNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class GroceryListsFragment :
    BaseFragment<GroceryListBinding, GroceryListsViewModel>(R.layout.fragment_grocerylists) {
    private val TAG = "GroceryListFragment"
    override val viewModel by viewModel<GroceryListsViewModel>()
    private val adapter by lazy {
        GroceryListsAdapter { handleOnCampaignItemClick(it) }
    }

    private var selectedListId = -1

    private fun handleOnCampaignItemClick(it: GroceryList) {
        println("List " + it.listName + " clicked")
        selectedListId = it.id

        Log.d("$TAG:setupViews:selectedListId: ", selectedListId.toString())

        findNavController()
            .navigate(R.id.action_groceryListsFragment_to_groceryItemsFragment
                , Bundle().apply {
                    putInt("LIST_ID", selectedListId)
                })
    }

    override fun setupViews() {
        binding?.listsRecycler?.adapter = adapter
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.groceryLists.observeNonNull(this) { adapter.submitList(it) }
    }
}