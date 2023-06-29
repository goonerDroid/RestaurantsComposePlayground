package com.sublime.restaurants

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestaurantViewModel(private val stateHandle: SavedStateHandle) : ViewModel(){

    private var restInterface: RestaurantAPIService
    private lateinit var restaurantCall: Call<List<Restaurant>>

    companion object {
        const val FAVORITES = "favorites"
    }

    val state = mutableStateOf(emptyList<Restaurant>())

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://sample-25d04.firebaseio.com/")
            .build()


        restInterface = retrofit.create(RestaurantAPIService::class.java)

        getRestaurants()
    }

    private fun getRestaurants(){
        restaurantCall = restInterface.getRestaurants()
        restaurantCall.enqueue(object : Callback<List<Restaurant>> {
            override fun onResponse(
                call: Call<List<Restaurant>>,
                response: Response<List<Restaurant>>
            ){
                response.body()?.let { restaurants ->
                    state.value =
                        restaurants.restoreSelections()
                }
            }
            override fun onFailure(
                call: Call<List<Restaurant>>, t: Throwable
            ){
                t.printStackTrace()
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        restaurantCall.cancel()
    }

    fun toggleFavorite(id: Int){
        val restaurants = state.value.toMutableList()
        val itemIndex = restaurants.indexOfFirst { it.id == id }
        val item = restaurants[itemIndex]
        restaurants[itemIndex] =
            item.copy(isFavorite = !item.isFavorite)
        storeSelection(restaurants[itemIndex])
        state.value = restaurants
    }

    private fun storeSelection(restaurant: Restaurant) {
        val savedToggled = stateHandle.get<List<Int>?>(FAVORITES).
                            orEmpty().toMutableList()

        if (restaurant.isFavorite) savedToggled.add(restaurant.id)
        else savedToggled.remove(restaurant.id)

        stateHandle[FAVORITES] = savedToggled
    }

    private fun List<Restaurant>.restoreSelections():List<Restaurant> {
        stateHandle.get<List<Int>?>(FAVORITES)?.let {
                selectedIds ->
            val restaurantsMap = this.associateBy { it.id }
            selectedIds.forEach { id ->
                restaurantsMap[id]?.isFavorite = true
            }
            return restaurantsMap.values.toList()
        }
        return this
    }
}


