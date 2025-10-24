package com.unsoed.responsi1mobile_h1d023030.network

import com.unsoed.responsi1mobile_h1d023030.model.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {

    @GET("v4/teams/{id}")
    fun getTeamDetail(
        @Path("id") teamId: Int,
        @Header("X-Auth-Token") token: String = " 7fda5a75b97844e196156677e7eb0f21"
    ): Call<TeamResponse>
}