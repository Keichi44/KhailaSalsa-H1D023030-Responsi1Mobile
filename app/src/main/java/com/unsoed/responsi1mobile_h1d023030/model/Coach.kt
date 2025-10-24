package com.unsoed.responsi1mobile_h1d023030.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coach(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("nationality")
    val nationality: String,

    @SerializedName("dateOfBirth")
    val dateOfBirth: String?

) : Parcelable