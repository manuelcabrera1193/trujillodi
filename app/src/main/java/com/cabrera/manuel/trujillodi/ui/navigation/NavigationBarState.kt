package com.cabrera.manuel.trujillodi.ui.navigation

data class NavigationBarState(
    val visible: Boolean = false,
    var selectedItem: Int = 0,
    val items: List<String> = listOf("Songs", "Artists", "Playlists"),
    val onItemClick: (Int) -> Unit = {},
)