package com.cabrera.manuel.trujillodi.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cabrera.manuel.trujillodi.base.Screen
import kotlinx.coroutines.delay

class Splash(
    override val state: SplashState,
    override val events: SplashUiEvents,
    override val route: String = "splash",
) : Screen {

    @Composable
    override fun Create(modifier: Modifier) {
        LaunchedEffect(Unit) {
            delay(3000)
            events.goToHome()
        }
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator()
        }
    }

}