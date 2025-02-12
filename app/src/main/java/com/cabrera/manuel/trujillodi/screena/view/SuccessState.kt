package com.cabrera.manuel.trujillodi.screena.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.cabrera.manuel.trujillodi.R
import com.cabrera.manuel.trujillodi.ui.theme.cardStyle
import com.willard.cabrera.domain.model.LocationModel
import com.willard.cabrera.domain.model.RecipeModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessState(
    recipes: List<RecipeModel>,
    goToDetail: (RecipeModel) -> Unit,
) {
    var showInput by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(TextFieldValue("")) }

    val filteredRecipes = remember(text.text, recipes) {
        if (showInput) {
            recipes.filter { it.name.contains(text.text, ignoreCase = true) }
        } else {
            recipes
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.recipes)) }
            )
        },
        bottomBar = {
            if (recipes.isEmpty()) return@Scaffold
            BottomAppBar {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = if (showInput) Arrangement.End else Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (showInput) {
                        TextField(
                            placeholder = { Text(text = stringResource(id = R.string.search)) },
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                            value = text,
                            onValueChange = { text = it }
                        )
                    }
                    ExtendedFloatingActionButton(
                        onClick = {
                            showInput = !showInput
                            text = TextFieldValue("")
                        },
                    ) {
                        Icon(
                            imageVector = if (showInput) Icons.Default.Close else Icons.Default.Search,
                            contentDescription = stringResource(id = R.string.icon_search),
                        )
                        if (!showInput) {
                            Text(
                                text = stringResource(id = R.string.search_label),
                                modifier = Modifier.padding(horizontal = 4.dp),
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(filteredRecipes) { index, recipe ->

                    var isVisible by remember { mutableStateOf(false) }

                    LaunchedEffect(Unit) {
                        delay(index * 25L)
                        isVisible = true
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = slideInHorizontally { it } + fadeIn(),
                        exit = ExitTransition.None,
                    ) {
                        ItemCard(goToDetail, recipe)
                    }

                    AnimatedVisibility(
                        visible = isVisible.not(),
                        enter = fadeIn(),
                        exit = ExitTransition.None,
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            CircularProgressIndicator(
                                Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }

                }
            }
        }
    }

}

@Composable
private fun ItemCard(
    goToDetail: (RecipeModel) -> Unit,
    item: RecipeModel
) {
    Card(
        onClick = { goToDetail(item) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.image)
                    .crossfade(true)
                    .transformations(CircleCropTransformation())
                    .build(),
                contentDescription = stringResource(R.string.item_image),
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(96.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(
                        id = R.string.card_description,
                        item.name, item.likes, item.usedIngredientCount
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    style = cardStyle,
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SuccessStatePreview() {
    SuccessState(
        recipes = listOf(
            RecipeModel(
                id = 1,
                name = "Test",
                image = "https://spoonacular.com/recipeImages/Cinnamon--Apple--Oatmeal-Cake-639496.jpg",
                description = "",
                usedIngredientCount = 1,
                missedIngredientCount = 1,
                likes = 1,
                location = LocationModel(
                    latitude = "1.0",
                    longitude = "1.0"
                )
            )
        ),
        goToDetail = {}
    )
}
