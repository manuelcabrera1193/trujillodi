package com.cabrera.manuel.trujillodi.screena

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cabrera.manuel.trujillodi.base.Screen

class ScreenA(
    override val state: ScreenUiStateA,
    override val events: ScreenUiEventsA,
) : Screen {

    @Composable
    override fun Create(modifier: Modifier) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = state.title)
            Text(text = state.description)
            Button(
                onClick = {
                    events.goToScreenB(
                        title = "Screen New B",
                        description = "Description New B"
                    )
                }
            ) {
                Text(text = "Go to screen B")
            }
        }
    }

}