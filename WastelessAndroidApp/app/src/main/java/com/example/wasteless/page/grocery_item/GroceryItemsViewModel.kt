package com.example.wasteless.page.grocery_item

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

class GroceryItemsViewModel (private val groceryProvider: GroceryProvider) : ViewModel(){
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    var listId = -1

    init{
        getListItemsFromAPI()
    }

    private val _groceryItems = MutableLiveData<List<GroceryItem>>()
    val groceryItems: LiveData<List<GroceryItem>>
        get() = _groceryItems

    private fun getListItemsFromAPI(){
        viewModelScope.launch {
            val items = groceryProvider.getListItems(listId)
            _groceryItems.value = items.successOr(listOf())
        }
    }

    val newItemName = MutableLiveData<String>().apply { value = "" }
    val newItemQuantity = MutableLiveData<String>().apply { value = "" }
    val newItemCalories = MutableLiveData<String>().apply { value = "" }
    val newItemPurchase = MutableLiveData<String>().apply { value = "" }
    val newItemConsumption = MutableLiveData<String>().apply { value = "" }
    val newItemExpiration = MutableLiveData<String>().apply { value = "" }

    fun addItemFromAPI(){
        viewModelScope.launch {

            if(ValidatorUtil.isNameValid(newItemName.value)
                && ValidatorUtil.isNumberValid(newItemQuantity.value)
                && ValidatorUtil.isNumberValid(newItemCalories.value)
                && ValidatorUtil.isDateValid(newItemPurchase.value)
                && ValidatorUtil.isDateValid(newItemConsumption.value)
                && ValidatorUtil.isDateValid(newItemExpiration.value)) {

                val quantity = newItemQuantity.value?.toInt()
                val calories = newItemCalories.value?.toInt()
                val purchaseDate = dateFormat.parse(newItemPurchase.value!!)
                val consumptionDate = dateFormat.parse(newItemConsumption.value!!)
                val expirationDate = dateFormat.parse(newItemExpiration.value!!)

                groceryProvider.addItem(
                    listId, GroceryItem(
                        itemName = newItemName.value!!,
                        itemQuantity = quantity!!,
                        calorieValue = calories!!,
                        purchaseDate = purchaseDate,
                        consumptionDate = consumptionDate,
                        expirationDate = expirationDate
                    )
                )
                getListItemsFromAPI()
            } else {
                println("Invalid input data")
            }

        }
    }

}