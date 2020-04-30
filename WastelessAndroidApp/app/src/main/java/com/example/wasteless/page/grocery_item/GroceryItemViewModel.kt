package com.example.wasteless.page.grocery_item

import androidx.lifecycle.ViewModel
import com.example.wasteless.page.main.MainActivityViewModel
import com.example.wasteless.remote.GroceryProvider

class GroceryItemViewModel (private val groceryProvider: GroceryProvider) : ViewModel(){

    private var listId = -1

    init{
        listId = MainActivityViewModel.selectedListId.value!!
    }

}