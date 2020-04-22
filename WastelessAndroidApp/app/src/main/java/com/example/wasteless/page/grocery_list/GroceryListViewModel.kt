package com.example.wasteless.page.grocery_list

import android.util.Log
import androidx.lifecycle.*
import com.example.wasteless.remote.GroceryProvider
import com.example.wasteless.remote.model.GroceryList
import com.example.wasteless.remote.successOr
import kotlinx.coroutines.launch

class GroceryListViewModel(private val groceryProvider: GroceryProvider) : ViewModel() {

    private val userId = 11

    init {
        getUserListsFromAPI()
    }

    private val _groceryList = MutableLiveData<List<GroceryList>>()
    val groceryList: LiveData<List<GroceryList>>
        get() = _groceryList

    private fun getUserListsFromAPI() {
        viewModelScope.launch {
            val lists = groceryProvider.getUserLists(userId)
            _groceryList.value = lists.successOr(listOf())
        }
    }



}