package com.cabrera.manuel.trujillodi.screenb

import androidx.compose.ui.graphics.Color
import com.cabrera.manuel.trujillodi.base.coordinator.active.ActiveState
import com.cabrera.manuel.trujillodi.base.coordinator.active.ActiveStateHolder
import com.cabrera.manuel.trujillodi.base.ui.UiState
import com.cabrera.manuel.trujillodi.util.getRandomComposeColor
import com.willard.cabrera.domain.model.RecipeModel

data class ScreenUiStateB(
    val recipe: RecipeModel,
    val color: Color = getRandomComposeColor(),
    val title: String = "Screen B",
    val description: String = "This is screen B",
    override val active: ActiveState = ActiveState.ENABLED,
) : UiState, ActiveStateHolder {
    override val id: Int
        get() = 2
}