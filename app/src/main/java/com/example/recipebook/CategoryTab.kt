@file:OptIn(ExperimentalMaterialApi::class)

package com.example.recipebook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object CategoryTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Category"
            val icon = rememberVectorPainter(Icons.Default.Favorite)

            return remember {
                TabOptions(
                    1u, title, icon
                )
            }
        }

    @Composable
    override fun Content() {
            Column(modifier = Modifier.fillMaxSize()) {
                AppBarCategory()
                RecipesList()
            }
    }

}

@Composable
fun AppBarCategory() {
    TopAppBar(
        title = {
            Text(text = "All Recipes")
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painterResource(id = R.drawable.ic_filter),
                    contentDescription = "Menu"
                )
            }
        },
        backgroundColor = Color.White,
        contentColor = colorResource(id = R.color.blue_dark),
        elevation = 8.dp
    )
}

@Composable
fun RecipesList(
) {
    val list = listOf("Veg", "Non-veg", "Vegan", "Egg", "Veg", "Non-veg", "Vegan", "Egg")
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(4.dp)
        ) {
            items(list.size) {
                CardRecipeList(list = list[it])
            }
        }
    }
}

@Composable
fun CardRecipeList(list: String) {
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(10.dp)),
        elevation = 12.dp,
        backgroundColor = colorResource(id = R.color.red_light)
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.paneer),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 0.dp)
                    .fillMaxWidth()
                    .height(200.dp),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(
                        text = "Recipe Name",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                        textAlign = TextAlign.Start,
                        color = colorResource(id = R.color.black)
                    )
                    Text(
                        text = "Short description",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 8.dp, bottom = 4.dp),
                        textAlign = TextAlign.Start,
                        color = colorResource(id = R.color.black)
                    )
                }
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_vegan),
//                    contentDescription = null,
//                    modifier = Modifier.padding(8.dp)
//                )
                Image(
                    painter = painterResource(id = R.drawable.ic_vegan),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(top = 0.dp, end = 8.dp),
                )
            }
        }
    }
}

@Composable
fun PrevCat() {
    Column() {
        AppBarCategory()
        RecipesList()
    }

}