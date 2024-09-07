package com.theapplicationpad.moviebooking.Presentation

import android.adservices.adid.AdId
import android.icu.text.CaseMap.Title
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.theapplicationpad.moviebooking.Core.AppRoute
import com.theapplicationpad.moviebooking.Core.AppRouteName
import com.theapplicationpad.moviebooking.Model.MovieModel
import com.theapplicationpad.moviebooking.Model.nowPlayingMovie
import com.theapplicationpad.moviebooking.R
import com.theapplicationpad.moviebooking.ui.theme.Yellow

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    movieModel: MovieModel
) {
    val scrollState = rememberScrollState()

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            
            Row {
                Button(modifier = modifier
                    .wrapContentWidth()
                    .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Yellow),
                    shape = RoundedCornerShape(32.dp),
                    onClick = {
                        navController.navigate(AppRouteName.SeatSelector)
                    }) {
                    Text(text = "Booking Now")
                }
                
                Spacer(modifier = Modifier.width(10.dp))

                Button(modifier = modifier
                    .wrapContentWidth()
                    .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Yellow),
                    shape = RoundedCornerShape(32.dp),
                    onClick = {
                        navController.navigate(AppRouteName.Food)
                    }) {
                    Text(text = "Booking Food")
                }
            }
            
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
             //   .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Row(
                modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Button")
                }

                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Movie Details", style = MaterialTheme.typography.titleLarge)
            }
            Row(
                modifier = modifier
                    .height(320.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Image(
                    painter = painterResource(id = movieModel.assetImage),
                    contentDescription = movieModel.title,
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier
                        .weight(0.7f)
                        .height(320.dp)
                        .clip(
                            RoundedCornerShape(16.dp)
                        )
                )

                Spacer(modifier = Modifier.width(24.dp))
                Column(
                    modifier = modifier
                        .height(320.dp)
                        .weight(0.3f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    MovieInfo(
                        painterResourceId = R.drawable.baseline_videocam,
                        title = "Genre",
                        value = movieModel.type
                    )
                    MovieInfo(
                        painterResourceId = R.drawable.baseline_access_time_filled,
                        title = "Duration",
                        value = movieModel.duration
                    )
                    MovieInfo(
                        painterResourceId = R.drawable.baseline_stars,
                        title = "Rating",
                        value = movieModel.rating
                    )

                }

            }

            Text(
                text = movieModel.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            )

            Text(
                text = "Synopsis",
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            )

            Text(
                text = movieModel.synopsis,
                style = MaterialTheme.typography.titleSmall,
                modifier = modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            )

            Spacer(modifier = Modifier.height(64.dp))


        }
    }
}

@Composable
fun MovieInfo(
    modifier: Modifier = Modifier,
    @DrawableRes painterResourceId: Int,
    title: String,
    value: String
) {
    Column(
        modifier = modifier
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable { }
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(painter = painterResource(id = painterResourceId), contentDescription = title)
        Spacer(modifier = modifier.height(4.dp))
        Text(text = title, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = modifier.height(4.dp))
        Text(text = value, style = MaterialTheme.typography.bodyMedium)

    }
}


@Preview
@Composable
private fun Detials() {
//    DetailScreen(navController = rememberNavController(), movieModel = MovieModel() )
}