package com.example.wasteless.page.grocery_item

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wasteless.page.main.MainActivityViewModel
import com.example.wasteless.remote.GroceryProvider
import com.example.wasteless.remote.model.GroceryItem
import com.example.wasteless.remote.successOr
import com.example.wasteless.shared.utils.validators.ValidatorUtil
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class GroceryItemsViewModel(private val groceryProvider: GroceryProvider) : ViewModel() {
    private val TAG = "GroceryItemsViewModel"
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    private val _listId = MutableLiveData<Int>().apply { value = -1 }

    fun getListItemsFromAPIById(listId: Int) {
        viewModelScope.launch {
            Log.d("$TAG:getListItemsFromAPI:listId: ", listId.toString())
            _listId.postValue(listId)
            val items = groceryProvider.getListItems(listId)
            _groceryItems.value = items.successOr(listOf())
        }
    }

    private val _groceryItems = MutableLiveData<List<GroceryItem>>()
    val groceryItems: LiveData<List<GroceryItem>>
        get() = _groceryItems

    val newItemName = MutableLiveData<String>().apply { value = "" }
    val newItemQuantity = MutableLiveData<String>().apply { value = "" }
    val newItemCalories = MutableLiveData<String>().apply { value = "" }
    val newItemPurchase = MutableLiveData<String>().apply { value = "" }
    val newItemConsumption = MutableLiveData<String>().apply { value = "" }
    val newItemExpiration = MutableLiveData<String>().apply { value = "" }

    fun addItemFromAPI() {
        viewModelScope.launch {
            Log.d("$TAG:addItemFromAPI:listId: ", _listId.toString())

            if (ValidatorUtil.isNameValid(newItemName.value)
                && ValidatorUtil.isNumberValid(newItemQuantity.value)
                && ValidatorUtil.isNumberValid(newItemCalories.value)
                && ValidatorUtil.isDateValid(newItemPurchase.value)
                && ValidatorUtil.isDateValid(newItemConsumption.value)
                && ValidatorUtil.isDateValid(newItemExpiration.value)
            ) {

                val quantity = newItemQuantity.value?.toInt()
                val calories = newItemCalories.value?.toInt()
                val purchaseDate = dateFormat.parse(newItemPurchase.value!!)
                val consumptionDate = dateFormat.parse(newItemConsumption.value!!)
                val expirationDate = dateFormat.parse(newItemExpiration.value!!)

                Log.d("$TAG: addItemFromAPI: ", purchaseDate.toString())

                groceryProvider.addItem(
                    _listId.value!!, GroceryItem(
                        itemName = newItemName.value!!,
                        itemQuantity = quantity!!,
                        calorieValue = calories!!,
                        purchaseDate = purchaseDate!!,
                        consumptionDate = consumptionDate!!,
                        expirationDate = expirationDate!!
                    )
                )
                getListItemsFromAPIById(_listId.value!!)
            } else {
                println("Invalid input data")
            }
        }
    }

}