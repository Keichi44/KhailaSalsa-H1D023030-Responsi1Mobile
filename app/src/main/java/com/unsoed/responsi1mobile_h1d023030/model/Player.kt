package com.unsoed.responsi1mobile_h1d023030.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,

    @SerializedName("position")
    val position: String?, // Dibuat nullable (?) untuk jaga-jaga

    @SerializedName("nationality")
    val nationality: String,

    @SerializedName("dateOfBirth")
    val dateOfBirth: String?

) : Parcelable