package com.sublime.restaurants

import retrofit2.Call
import retrofit2.http.GET

interface RestaurantAPIService {

    @GET("restaurants.json")
    fun getRestaurants(): Call<List<Restaurant>>
}