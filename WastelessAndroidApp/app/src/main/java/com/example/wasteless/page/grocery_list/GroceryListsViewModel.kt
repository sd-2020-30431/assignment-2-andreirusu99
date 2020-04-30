package com.example.wasteless.page.grocery_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wasteless.page.main.MainActivityViewModel
import com.example.wasteless.remote.GroceryProvider
import com.example.wasteless.remote.model.GroceryList
import com.example.wasteless.remote.successOr
import kotlinx.coroutines.launch

class GroceryListsViewModel(private val groceryProvider: GroceryProvider) : ViewModel() {

    private var userId = -1

    init {
        if(MainActivityViewModel.loggedInUserId.value != -1)
            userId = MainActivityViewModel.loggedInUserId.value!!
        getUserListsFromAPI()
    }

    private val _groceryLists = MutableLiveData<List<GroceryList>>()
    val groceryLists: LiveData<List<GroceryList>>
        get() = _groceryLists

    val newListName = MutableLiveData<String>().apply { value = "" }

    val navigateToGroceryItemsFragment = MutableLiveData<Boolean>().apply { value = false }

    private fun getUserListsFromAPI() {
        viewModelScope.launch {
            val lists = groceryProvider.getUserLists(userId)
            _groceryLists.value = lists.successOr(listOf())
        }
    }

    fun addListFromAPI(listName: String) {
        viewModelScope.launch {
            groceryProvider.addList(userId, GroceryList(listName = listName, userId = userId))
            getUserListsFromAPI()
        }
    }

    fun goToListItems(listId: Int){
        navigateToGroceryItemsFragment.postValue(true)
    }

}