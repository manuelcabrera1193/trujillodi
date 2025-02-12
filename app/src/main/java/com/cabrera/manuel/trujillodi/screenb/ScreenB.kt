package com.cabrera.manuel.trujillodi.screenb

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.cabrera.manuel.trujillodi.R
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.Screen

class ScreenB(
    override val state: ScreenUiStateB,
    override val emitterData: EmitterData,
    override val route: String = "screenB",
) : Screen {

    @Composable
    override fun CreateBody(modifier: Modifier) {

        Column(
            modifier = modifier
                .background(state.color)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = state.recipe.name)
            Text(text = state.recipe.description)

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.recipe.image)
                    .crossfade(true)
                    .transformations(CircleCropTransformation())
                    .build(),
                contentDescription = stringResource(R.string.item_image),
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(96.dp)
            )
            Button(
                onClick = {
                    emitterData.emitData(ScreenBData("Completed"))
                }
            ) {
                Text(text = "Go to New List")
            }
        }
    }

}