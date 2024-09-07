package com.theapplicationpad.moviebooking.Presentation

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageInfo
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PageSize.Fill.calculateMainAxisPageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.theapplicationpad.moviebooking.Core.AppRoute
import com.theapplicationpad.moviebooking.Core.AppRouteName
import com.theapplicationpad.moviebooking.Model.MovieModel
import com.theapplicationpad.moviebooking.Model.nowPlayingMovie
import com.theapplicationpad.moviebooking.Model.upcoming
import com.theapplicationpad.moviebooking.R
import com.theapplicationpad.moviebooking.ui.theme.BlueVariant
import com.theapplicationpad.moviebooking.ui.theme.Gray
import com.theapplicationpad.moviebooking.ui.theme.Yellow
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue


@Composable
fun HomeScreen(modifier: Modifier = Modifier, navHostController: NavHostController,ismymode:Boolean,onModeToggle: () -> Unit) {
    val scrollState = rememberScrollState()
    var isDarkMode by remember { mutableStateOf(ismymode) }

    //  var isDarkTheme by remember { mutableStateOf(ismymode) }  // Toggle state

    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding() + 24.dp
                )
        ) {

            Row(
                modifier=modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Welcome back, Sir!",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = modifier.padding(horizontal = 24.dp)
                )

                // Dark Mode Toggle
                DarkModeToggle(isDarkTheme = isDarkMode, onToggleChange = {
                    onModeToggle()
                    isDarkMode = !isDarkMode // Sync the toggle with the app's state
                })
            }


            Spacer(modifier = modifier.height(4.dp))
            Text(
                text = "Book your Favorite Movie Here!",
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier.padding(horizontal = 24.dp)
            )


            Spacer(modifier = modifier.height(24.dp))

            Banner()

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Category",
                    style = MaterialTheme.typography.titleMedium,
                )

                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = "Sell All",
                    )
                }

            }
            Spacer(modifier = modifier.height(4.dp))
            Categories()
            Spacer(modifier = modifier.height(16.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Now Playing Movie",
                    style = MaterialTheme.typography.titleMedium,
                )

                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = "Sell All",
                    )
                }

            }
            Spacer(modifier = modifier.height(16.dp))
            NowPlayingMovie { movie ->
                navHostController.navigate("${AppRouteName.Detail}/${movie.id}")
            }
            Spacer(modifier = modifier.height(16.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Upcoming Movie",
                    style = MaterialTheme.typography.titleMedium,
                )

                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = "Sell All",
                    )
                }

            }
            Spacer(modifier = modifier.height(16.dp))

            UpcomingMovie()

        }
    }
}

@Composable
fun UpcomingMovie(modifier: Modifier = Modifier) {
    LazyRow(
        contentPadding = PaddingValues(start = 24.dp)
    ) {
        items(count = upcoming.size) { index ->
            Box(modifier = Modifier
                .padding(end = 24.dp)
                .clickable { }) {
                Column(
                    modifier = modifier
                        .wrapContentHeight()
                        .clip(shape = RoundedCornerShape(8.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = upcoming[index].assetImage),
                        contentDescription = "Upcoming Movie",
                        contentScale = ContentScale.FillBounds,
                        modifier = modifier.size(width = 200.dp, height = 260.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = upcoming[index].title,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NowPlayingMovie(onMovieClicked: (MovieModel) -> Unit) {

    val pagerState = rememberPagerState(pageCount = { nowPlayingMovie.size })

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(start = 48.dp, end = 48.dp)
    ) { page ->
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .graphicsLayer {
                    val pageOffset = pagerState.getOffsetFractionForPage(page).absoluteValue
                    lerp(
                        start = ScaleFactor(1f, 0.85f),
                        stop = ScaleFactor(1f, 1f),
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale.scaleX
                        scaleY = scale.scaleY
                    }
                }
                .clickable {
                    onMovieClicked(nowPlayingMovie[page])
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.clip(RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    painter = painterResource(id = nowPlayingMovie[page].assetImage),
                    contentDescription = "Movie Image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.85f)
                        .height(340.dp)
                )

                Box(modifier = Modifier
                    .fillMaxWidth(fraction = 0.85f)
                    .wrapContentHeight()
                    .graphicsLayer {
                        val pageOffset = pagerState.getOffsetFractionForPage(page).absoluteValue
                        val transalation = pageOffset.coerceIn(0f, 1f)
                        translationY = transalation * 200
                    }
                    .background(
                        BlueVariant
                    )
                    .padding(vertical = 16.dp), contentAlignment = Alignment.Center) {

                    Text(
                        text = "Buy Ticket",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = Yellow,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = nowPlayingMovie[page].title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))


        }
    }


}

@Composable
fun Categories(modifier: Modifier = Modifier) {
    val categories = listOf(
        "Animation",
        "Horror",
        "Action",
        "Comedy",
        "Romance",
        "Sci-fi",
        "History",
        "Adventure",
    )
    val scrollState = rememberScrollState()

    Row(modifier = modifier.horizontalScroll(scrollState)) {
        repeat(categories.size) { index ->
            Surface(
                modifier = modifier
                    .padding(start = if (index == 0) 24.dp else 0.dp, end = 12.dp)
                    .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
                    .clickable { }
                    .padding(12.dp)
            ) {
                Text(text = categories[index], style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Banner(modifier: Modifier = Modifier) {
    val banners = listOf(
        R.drawable.banner_1,
        R.drawable.banner_2,
        R.drawable.banner_3
    )

    val pagerState = rememberPagerState(pageCount = { banners.size })
    val bannerIndex = remember {
        mutableStateOf(0)
    }



    LaunchedEffect(pagerState) {
        snapshotFlow {
            pagerState.currentPage
        }.collect { page ->
            bannerIndex.value = page
        }
    }

    // Auto scroll

    LaunchedEffect(Unit) {
        while (true) {
            delay(2_000)
            tween<Float>(1000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % pagerState.pageCount
            )
        }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(190.dp)
            .padding(horizontal = 15.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)

        ) { page ->
            Image(
                painter = painterResource(id = banners[page]), contentDescription = "Banners",
                contentScale = ContentScale.FillBounds, modifier = modifier.clip(shape = RoundedCornerShape(8.dp))
            )
        }
        Row(
            modifier = modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {

            repeat(banners.size) { index ->

                val height = 12.dp
                val width = if (index == bannerIndex.value) 28.dp else 12.dp
                val color = if (index == bannerIndex.value) Yellow else Gray

                Surface(
                    modifier = modifier
                        .padding(end = 6.dp)
                        .size(width, height)
                        .clip(RoundedCornerShape(20.dp)),
                    color = color
                ) {

                }
            }
        }


    }
}
@Composable
fun DarkModeToggle(isDarkTheme: Boolean, onToggleChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.padding(end = 20.dp)
    ) {
        Switch(
            checked = isDarkTheme,
            onCheckedChange = onToggleChange
        )
    }
}