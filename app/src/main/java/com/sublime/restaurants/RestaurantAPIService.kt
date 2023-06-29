package com.sublime.restaurants

import retrofit2.Call
import retrofit2.http.GET

interface RestaurantAPIService {

    @GET("restaurants.json")
    suspend fun getRestaurants(): List<Restaurant>
}