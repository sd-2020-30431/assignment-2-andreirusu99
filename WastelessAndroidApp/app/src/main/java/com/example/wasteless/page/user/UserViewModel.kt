package com.example.wasteless.page.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wasteless.remote.GroceryProvider
import com.example.wasteless.remote.model.User
import kotlinx.coroutines.launch

class UserViewModel(private val groceryProvider: GroceryProvider) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user : LiveData<User>
        get() = _user

    var loggedInUserId : Int = 0

    private fun getUserDataFromAPI(){
        viewModelScope.launch {
            val user =
            _user.value
        }
    }

}