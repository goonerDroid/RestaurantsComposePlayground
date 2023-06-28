package com.sublime.restaurants

import com.google.gson.annotations.SerializedName

data class Restaurant (
    @SerializedName("r_id")
    val id: Int,
    @SerializedName("r_title")
    val title: String,
    @SerializedName("r_description")
    val description: String,
    var isFavorite: Boolean = false)

val dummyRestaurantList = listOf(
    Restaurant(0,"The Savory Spoon","Enjoy a delightful culinary experience at The Savory Spoon."),
    Restaurant(1,"Spice Junction","Step into the world of flavors at Spice Junction."),
    Restaurant(2,"Bella Italia","Experience the taste of Italy at Bella Italia."),
    Restaurant(3,"Ocean Breeze Seafood Grill","Savor the freshest catch of the day at Ocean Breeze Seafood Grill."),
    Restaurant(4,"The Green Garden","Embrace a healthy and sustainable dining experience at The Green Garden."),
    Restaurant(5,"Flame Steakhouse","Indulge in a carnivorous feast at Flame Steakhouse."),
    Restaurant(6,"Thai Orchid","Embark on a culinary journey to Thailand at Thai Orchid."),
    Restaurant(7,"Fusion Street","Experience the fusion of flavors at Fusion Street."),
    Restaurant(8,"The Sweet Spot","Indulge your sweet tooth at The Sweet Spot."),
    Restaurant(9,"Café La Vie","Relax and unwind at Café La Vie."),
)