package com.example.wasteless.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("firstName")
    var firstName: String = "",

    @SerializedName("lastName")
    var lastName: String = "",

    @SerializedName("password")
    var password: String = "",

    @SerializedName("calorieIntake")
    var calorieIntake: Int = 0
) : Parcelable