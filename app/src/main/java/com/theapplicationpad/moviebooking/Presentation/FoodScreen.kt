package com.theapplicationpad.moviebooking.Presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.theapplicationpad.moviebooking.Model.FoodModel
import com.theapplicationpad.moviebooking.Model.foodlist
import com.theapplicationpad.moviebooking.R
import com.theapplicationpad.moviebooking.ui.theme.Gray
import com.theapplicationpad.moviebooking.ui.theme.Yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(modifier: Modifier = Modifier, navController: NavController) {
    var checked by remember { mutableStateOf(true) }
    var checked1 by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            Topappbar(navController=navController)
        }
    ) { paddingValues ->
        Column(
            modifier = modifier.padding(paddingValues)

        ) {

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "Veg", style = MaterialTheme.typography.titleMedium, fontSize = 20.sp)
                Spacer(modifier = modifier.width(10.dp))

                Switch(
                    modifier = modifier.height(35.dp),
                    colors = SwitchDefaults.colors(
                        checkedIconColor = Yellow,
                        uncheckedThumbColor = Gray,
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Yellow
                    ),
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                    }
                )
                Spacer(modifier = modifier.width(10.dp))

                Text(
                    text = "Non Veg",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp
                )
                Spacer(modifier = modifier.width(10.dp))

                Switch(
                    modifier = modifier.height(35.dp),
                    colors = SwitchDefaults.colors(
                        checkedIconColor = Yellow,
                        checkedThumbColor = Color.White,
                        uncheckedThumbColor = Gray,
                        checkedTrackColor = Yellow
                    ),
                    checked = checked1,
                    onCheckedChange = {
                        checked1 = it
                    }
                )
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Bestsellers",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()  // Set an appropriate height
                    .padding(4.dp)
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(foodlist) {
                        FoodBox(foodModel = it)
                    }
                }
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Your happy place",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 20.dp, end = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                HappyBox(img = R.drawable.showfood1)
                HappyBox(img = R.drawable.showfood2)
                HappyBox(img = R.drawable.showfood3)


            }

        }
    }
}

@Composable
fun HappyBox(modifier: Modifier = Modifier, img: Int) {
    Box(
        modifier = modifier
            .padding(5.dp)
            .width(90.dp)
            .height(130.dp)
            .border(width = 1.dp, color = Yellow, shape = RoundedCornerShape(12.dp))
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = img),
            contentDescription = null,
            modifier = modifier.size(70.dp)
        )
    }

}


@Composable
fun FoodBox(modifier: Modifier = Modifier, foodModel: FoodModel) {

    ElevatedCard(
        modifier = modifier
            .clip(shape = RoundedCornerShape(18.dp))
            .padding(5.dp),


        colors = CardDefaults.cardColors(
            containerColor =MaterialTheme.colorScheme.surface
        ),
        shape = CardDefaults.shape,

        // elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Box(
            modifier = modifier
                .size(width = 160.dp, height = 240.dp)
                .padding(5.dp), contentAlignment = Alignment.Center
        ) {
            Column(
                modifier.padding(start = 4.dp)
            ) {

                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = foodModel.img),
                        contentDescription = null,
                        modifier.size(120.dp)
                    )
                }


                Text(
                    text = foodModel.title,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
                Text(
                    text = foodModel.discription,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp
                )
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "₹${foodModel.price}.00",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Box(
                        modifier = modifier
                            .wrapContentHeight()
                            .width(60.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(Yellow)
                            .padding(4.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Add",
                            style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                        )


                    }
                }

            }
        }
    }

}

@Composable
fun Topappbar(modifier: Modifier = Modifier,navController: NavController) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Back Arrow")
        }
        Spacer(modifier = modifier.width(20.dp))

        Text(text = "Order Snacks", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = modifier.width(80.dp))
        Box(
            modifier = modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .clip(shape = RoundedCornerShape(8.dp))
                .background(Yellow)
                .padding(7.dp)
        ) {
            Text(
                text = "Continue",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun FoodPrev() {
    FoodScreen(navController = rememberNavController())
}