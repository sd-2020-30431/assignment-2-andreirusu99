package com.example.wasteless.page.login

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wasteless.page.main.MainActivity
import com.example.wasteless.page.main.MainActivityViewModel
import com.example.wasteless.remote.GroceryProvider
import com.example.wasteless.remote.model.User
import com.example.wasteless.remote.successOr
import com.example.wasteless.shared.utils.validators.ValidatorUtil
import kotlinx.coroutines.launch

class LoginViewModel(private val groceryProvider: GroceryProvider) : ViewModel() {

    private val _userId = MutableLiveData<Int>()
    val userId: LiveData<Int>
        get() = _userId

    val firstNameText = MutableLiveData<String>()
    val lastNameText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()

    val navigateToGroceryListsFragment = MutableLiveData<Boolean>().apply { value = false }

    fun attemptLoginFromAPI(firstName: String, lastName: String, password: String) {
        viewModelScope.launch {

                val userId = groceryProvider.attemptLogin(
                    User(
                        firstName = firstName,
                        lastName = lastName,
                        password = password
                    ))
                _userId.value = userId.successOr(-1)
                if (_userId.value != -1) {
                    MainActivityViewModel.setLoggedInUserId(_userId.value!!)
                    navigateToGroceryListsFragment.postValue(true)
                }

        }
    }
}