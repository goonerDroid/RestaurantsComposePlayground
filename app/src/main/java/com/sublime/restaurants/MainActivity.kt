package com.sublime.restaurants

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sublime.restaurants.ui.theme.RestaurantsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantsTheme {
                RestaurantApp() //Main Entry
            }
        }
    }

    @Composable
    private fun RestaurantApp(){
        val navController = rememberNavController()

        NavHost(navController, startDestination = "restaurants") {
            composable(route = "restaurants"){
                RestaurantScreen{id ->
                    navController.navigate("restaurants/$id")
                }
            } //Restaurant List Screen

            composable(route = "restaurants/{restaurant_id}",
                        arguments = listOf(navArgument("restaurant_id"){
                            type = NavType.IntType
                        })
            ){
                RestaurantDetailScreen()
            } // Restaurant Detail Screen
        }
    }
}