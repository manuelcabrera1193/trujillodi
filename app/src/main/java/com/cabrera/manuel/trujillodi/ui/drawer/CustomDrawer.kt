package com.cabrera.manuel.trujillodi.ui.drawer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CustomDrawer(
    closeMenu: () -> Unit
) {
    BackHandler {
        closeMenu.invoke()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        IconButton(
            onClick = {
                closeMenu.invoke()
            },
            content = {
                Text(text = "Close Menu")
            },
        )
    }
}