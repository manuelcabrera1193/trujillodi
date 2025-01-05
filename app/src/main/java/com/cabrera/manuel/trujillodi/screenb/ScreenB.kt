package com.cabrera.manuel.trujillodi.screenb

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cabrera.manuel.trujillodi.base.Screen

class ScreenB(
    override val state: ScreenUiStateB,
    override val events: ScreenUiEventsB,
    override val route: String = "screenB",
) : Screen {

    @Composable
    override fun Create(modifier: Modifier) {

        Column(
            modifier = modifier.background(state.color),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = state.title)
            Text(text = state.description)
            Button(onClick = {
                events.goToScreenC()
            }) {
                Text(text = "Go to screen B")
            }
        }
    }

}