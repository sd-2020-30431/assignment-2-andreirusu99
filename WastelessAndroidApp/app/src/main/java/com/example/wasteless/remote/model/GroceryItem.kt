package com.example.wasteless.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class GroceryItem (
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("item_name")
    var itemName: String = "",

    @SerializedName("item_quantity")
    var itemQuantity: Int = 0,

    @SerializedName("calorie_value")
    var calorieValue: Int = 0,

    @SerializedName("purchase_date")
    var purchaseDate: Date,

    @SerializedName("consumption_date")
    var consumptionDate: Date,

    @SerializedName("expiration_date")
    var expirationDate: Date,

    @SerializedName("list_id")
    var listId: Int = 0
): Parcelable