package com.cabrera.manuel.trujillodi.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CustomNavigationBar(navigationBarState: NavigationBarState = NavigationBarState()) {

    val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.Star)
    val unselectedIcons =
        listOf(Icons.Outlined.Home, Icons.Outlined.FavoriteBorder, Icons.Outlined.Star)

    AnimatedVisibility(
        visible = navigationBarState.visible,
        enter = slideInVertically { it } + fadeIn(),
        exit = ExitTransition.None,
    ) {
        NavigationBar {
            navigationBarState.items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            if (navigationBarState.selectedItem == index) {
                                selectedIcons[index]
                            } else {
                                unselectedIcons[index]
                            },
                            contentDescription = item
                        )
                    },
                    label = { Text(item) },
                    selected = navigationBarState.selectedItem == index,
                    onClick = {
                        navigationBarState.selectedItem = index
                        navigationBarState.onItemClick(index)
                    }
                )
            }
        }
    }
}