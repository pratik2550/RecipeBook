@file:OptIn(ExperimentalFoundationApi::class)

package com.example.recipebook

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object HomeTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Home"
            val icon = rememberVectorPainter(Icons.Default.Home)

            return remember {
                TabOptions(
                    0u, title, icon
                )
            }

        }

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopBarHome()
            WelcomeNote(name = "Pratik")
            CategoryRecipe()
            TopSearch()
        }
    }
}

@Composable
fun TopBarHome() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
            )
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu"
                )
            }
        },
        backgroundColor = White,
        contentColor = colorResource(id = R.color.blue_dark),
        elevation = 8.dp
    )
}

@Composable
fun WelcomeNote(name: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 0.dp)
    ) {
        Text(
            text = "Hello, $name",
            style = MaterialTheme.typography.h1,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.blue_dark)
        )
        Text(
            text = "Good Afternoon!",
            style = MaterialTheme.typography.h4,
            color = colorResource(id = R.color.blue_dark)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Prev() {
    Column {
        TopBarHome()
        WelcomeNote(name = "Pratik")
        CategoryRecipe()
        TopSearch()
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryRecipe() {
    val list = listOf("Veg", "Non-veg", "Vegan", "Egg", "Veg", "Non-veg", "Vegan", "Egg")
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 0.dp)
    ) {
        items(items = list, itemContent = { item ->
            Card(
                modifier = Modifier
                    .size(100.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 0.dp),
                elevation = 8.dp,
//                shape = CircleShape,
//                        RoundedCornerShape(
//                    topStart = 40.dp,
//                    topEnd = 40.dp,
//                    bottomStart = 40.dp,
//                    bottomEnd = 40.dp
//                ),
                backgroundColor = colorResource(id = R.color.red_light)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        Icons.Default.AccountBox, contentDescription = null, modifier = Modifier
                            .fillMaxWidth()
                            .size(70.dp)
                            .padding(top = 4.dp)
                    )
                    Text(
                        text = item,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(4.dp), textAlign = TextAlign.Center
                    )
                }
            }
        })
    }
}

@Composable
fun TopSearch() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Best pick for you",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 15.dp, bottom = 8.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h2
        )
        val list = listOf("Veg", "Non-veg", "Vegan", "Egg", "Veg", "Non-veg", "Vegan", "Egg")
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp)
//        ) {
//            items(items = list, itemContent = { item ->
//                Card(
//                    modifier = Modifier
//                        .height(100.dp)
//                        .fillMaxWidth()
//                        .padding(6.dp),
//                    elevation = 8.dp,
//                    shape = RoundedCornerShape(
//                        topStart = 8.dp,
//                        topEnd = 8.dp,
//                        bottomStart = 8.dp,
//                        bottomEnd = 8.dp
//                    ),
//                    backgroundColor = colorResource(id = R.color.red_light)
//                ) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            Icons.Default.AccountBox,
//                            contentDescription = null,
//                            modifier = Modifier
//                                .fillMaxHeight()
//                                .padding(top = 4.dp)
//                        )
//                        Text(
//                            text = item,
//                            fontSize = 14.sp,
//                            fontWeight = FontWeight.Bold,
//                            modifier = Modifier.padding(4.dp), textAlign = TextAlign.Start
//                        )
//                    }
//                }
//            })
//        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(list.size) {
                FeatureItem(list = list[it])
            }
        }
    }
}

@Composable
fun FeatureItem(
    list: String
) {
    Column(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.red_light)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//        Icon(
//            Icons.Default.AccountBox,
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 4.dp),
//
//            )
        Image(
            painter = painterResource(id = R.drawable.paneer),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 0.dp, end = 0.dp)
                .fillMaxWidth(),
        )
        Text(
            text = list,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(4.dp), textAlign = TextAlign.Start
        )
    }
}