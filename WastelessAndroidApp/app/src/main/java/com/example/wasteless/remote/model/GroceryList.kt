package com.example.wasteless.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GroceryList(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("listName")
    val listName: String = "",
    @SerializedName("userId")
    val userId: Int = 0
) : Parcelable
