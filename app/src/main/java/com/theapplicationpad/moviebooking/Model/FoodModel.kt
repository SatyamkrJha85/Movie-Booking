package com.theapplicationpad.moviebooking.Model

import com.theapplicationpad.moviebooking.R

data class FoodModel(
    val title:String,
    val img:Int,
    val discription:String,
    val price:String
)

val foodlist = listOf(
    FoodModel(
        title = "VEGGIE MINT CHI..",
        discription = "170g + 339.4/Per 100g kcal ALiegens Milk,Wheat",
        img = R.drawable.food1,
        price = "270"
    ),
    FoodModel(
        title = "BBQ GRILLED CHICKEN..",
        discription = "150g + 339.4/Per 100g kcal ALiegens Milk,Wheat",
        img = R.drawable.food2,
        price = "525"
    ),
    FoodModel(
        title = "PANNER TIKKA SANDWICH..",
        discription = "200g + 339.4/Per 100g kcal ALiegens Milk,Wheat",
        img = R.drawable.food3,
        price = "621"
    ),
    FoodModel(
        title = "CHICKEN TIKKA..",
        discription = "562g + 339.4/Per 100g kcal ALiegens Milk,Wheat",
        img = R.drawable.food4,
        price = "325"
    ),
)