@file:OptIn(ExperimentalPagerApi::class)

package com.example.recipebook

import android.util.Log.w
import androidx.compose.animation.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

object ProfileTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "search"
            val icon = rememberVectorPainter(Icons.Default.Person)

            return remember {
                TabOptions(
                    2u, title, icon
                )
            }
        }

    @Composable
    override fun Content() {
        Column(modifier = Modifier.fillMaxSize()) {
            Profile()
            PagerActivity()
        }
    }
}

@Composable
fun Profile() {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
            .height(150.dp)
            .background(color = colorResource(id = R.color.blue_dark))
    ) {
        Image(
            painter = painterResource(id = R.drawable.paneer),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .padding(start = 16.dp, top = 16.dp)
                .clip(CircleShape)
                .border(4.dp, Color.Gray, CircleShape)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 4.dp)
        ) {
            Text(
                text = "Pratik patle",
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp),
                style = MaterialTheme.typography.h1,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.white)
            )

            Text(
                text = "pratik@gmail.com",
                modifier = Modifier
                    .padding(start = 8.dp, end = 4.dp, bottom = 20.dp),
                style = MaterialTheme.typography.h2,
                color = colorResource(id = R.color.white)
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun PagerActivity() {
    val tabItems = listOf("Favorite", "Watch later", "My Recipe")
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    Column() {

        TabRow(selectedTabIndex = pagerState.currentPage,
            backgroundColor = colorResource(id = R.color.red_light),
            modifier = Modifier
                .padding(20.dp)
                .background(color = Color.Transparent)
                .clip(shape = RoundedCornerShape(30.dp)),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                        .width(0.dp)
                        .height(0.dp)
                )
            }
        ) {
            tabItems.forEachIndexed { index, title ->
                val color = remember {
                    Animatable(Color(R.color.red_light))
                }
                LaunchedEffect(pagerState.currentPage == index) {
                    color.animateTo(if (pagerState.currentPage == index) Color.White else Color.Transparent)
                }

                Tab(
                    text = {
                        Text(
                            text = title,
                            style = if (pagerState.currentPage == index) TextStyle(
                                fontSize = 16.sp
                            )
                            else TextStyle(fontSize = 14.sp)
                        )
                    },
                    selected = pagerState.currentPage == index,
                    modifier = Modifier.background(
                        color = color.value,
                        shape = RoundedCornerShape(30.dp)
                    ),
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                    })
            }
        }

        HorizontalPager(
            count = tabItems.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.white))
        ) { page ->
            when (page) {
                0 -> FavoriteTab()
                1 -> WatchLaterTab()
                2 -> MyRecipeTab()
            }
        }
    }
}

@Composable
fun FavoriteTab() {
    Text(
        text = "0",
        modifier = Modifier.padding(50.dp),
        color = colorResource(id = R.color.blue_dark)
    )
}

@Composable
fun WatchLaterTab() {
    Text(
        text = "1",
        modifier = Modifier.padding(50.dp),
        color = colorResource(id = R.color.blue_dark)
    )
}

@Composable
fun MyRecipeTab() {
    Text(
        text = "2",
        modifier = Modifier.padding(50.dp),
        color = colorResource(id = R.color.blue_dark)
    )
}