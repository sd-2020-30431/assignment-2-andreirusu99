package com.example.wasteless.page.grocery_list

import androidx.lifecycle.*
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.wasteless.page.login.LoginViewModel
import com.example.wasteless.remote.GroceryProvider
import com.example.wasteless.remote.model.GroceryList
import com.example.wasteless.remote.successOr
import kotlinx.coroutines.launch
import androidx.navigation.fragment.navArgs
import com.example.wasteless.page.main.MainActivityViewModel
import com.example.wasteless.shared.utils.validators.ValidatorUtil
import java.lang.invoke.MethodHandles

class GroceryListViewModel(private val groceryProvider: GroceryProvider) : ViewModel() {

    private var userId = 2

    init {
        getUserListsFromAPI()
        if(MainActivityViewModel.loggedInUserId.value != -1)
            userId = MainActivityViewModel.loggedInUserId.value!!
    }

    private val _groceryLists = MutableLiveData<List<GroceryList>>()
    val groceryLists: LiveData<List<GroceryList>>
        get() = _groceryLists

    val newListName = MutableLiveData<String>().apply { value = "" }

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

}