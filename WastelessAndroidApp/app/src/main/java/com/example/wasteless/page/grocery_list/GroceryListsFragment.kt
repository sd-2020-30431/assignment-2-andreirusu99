package com.example.wasteless.page.grocery_list

import com.example.wasteless.GroceryListBinding
import com.example.wasteless.R
import com.example.wasteless.remote.model.GroceryList
import com.example.wasteless.shared.base.BaseFragment
import com.example.wasteless.shared.utils.extensions.observeNonNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class GroceryListsFragment :
    BaseFragment<GroceryListBinding, GroceryListViewModel>(R.layout.fragment_grocerylists) {

    override val viewModel by viewModel<GroceryListViewModel>()
    private val adapter by lazy {
        GroceryListAdapter { handleOnCampaignItemClick(it) }
    }


    private fun handleOnCampaignItemClick(it: GroceryList) {

    }

    override fun setupViews() {
        binding?.listsRecycler?.adapter = adapter
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.groceryLists.observeNonNull(this) { adapter.submitList(it) }
    }
}