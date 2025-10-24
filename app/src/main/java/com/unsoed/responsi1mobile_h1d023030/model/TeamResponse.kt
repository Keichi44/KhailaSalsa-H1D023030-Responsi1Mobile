package com.unsoed.responsi1mobile_h1d023030.model

import com.google.gson.annotations.SerializedName


data class TeamResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("crest")
    val crest: String, // Ini link ke logo klub

    @SerializedName("headCoach")
    val headCoach: Coach?, // Dibuat nullable (?)

    @SerializedName("squad")
    val squad: List<Player>
)