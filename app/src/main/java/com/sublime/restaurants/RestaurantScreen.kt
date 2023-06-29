package com.sublime.restaurants

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sublime.restaurants.ui.theme.RestaurantsTheme

@Composable
fun RestaurantScreen(){
    val restaurantViewModel: RestaurantViewModel = viewModel()
    LazyColumn() {
        items(restaurantViewModel.state.value){ restaurant->
            RestaurantItem(restaurant){ id ->
                restaurantViewModel.toggleFavorite(id)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    RestaurantsTheme {
        RestaurantScreen()
    }
}

@Composable
fun RestaurantItem(item: Restaurant, onClick: (id:Int) -> Unit) {

    val icon = if (item.isFavorite)
        Icons.Filled.Favorite
    else
        Icons.Filled.FavoriteBorder

    Card(elevation = 4.dp,
        modifier = Modifier.padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)) {

            RestaurantIcon(Icons.Filled.Place,Modifier.weight(0.15f))
            RestaurantDetails(item.title,item.description,Modifier.weight(0.70f))
            RestaurantIcon(icon,Modifier.weight(0.15f)){
                onClick(item.id)
            }
        }
    }
}

@Composable
fun FavoriteIcon(icon: ImageVector,modifier: Modifier,onClick: ()-> Unit) {
    Image(
        imageVector = icon,
        contentDescription = "Favorite Restaurant Icon",
        modifier = modifier
            .padding(8.dp)
            .clickable {
                onClick()
            }
    )
}

@Composable
fun RestaurantDetails(title: String, description: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = title,
            style = MaterialTheme.typography.h6)

        CompositionLocalProvider(
            LocalContentAlpha provides
                    ContentAlpha.medium) {
            Text(
                text = description,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun RestaurantIcon(icon: ImageVector, modifier: Modifier,onClick: ()-> Unit ={}) {
    Image(imageVector = icon, contentDescription = "Restaurant Icon",
    modifier = modifier
        .padding(8.dp)
        .clickable {
            onClick()
        })
}
