package com.cabrera.manuel.trujillodi.screena

import androidx.compose.ui.graphics.Color
import com.cabrera.manuel.trujillodi.base.ui.UiState
import com.cabrera.manuel.trujillodi.base.visibility.VisibilityState
import com.cabrera.manuel.trujillodi.base.visibility.VisibilityStateHolder
import com.cabrera.manuel.trujillodi.util.getRandomComposeColor
import com.willard.cabrera.domain.model.RecipeModel

data class ScreenUiStateA(
    override val id: Int = 1,
    val color: Color = getRandomComposeColor(),
    val recipes: List<RecipeModel> = emptyList(),
    val selectedRecipe: RecipeModel? = null,
    val retry: () -> Unit = {},
    val goToDetail: (RecipeModel) -> Unit = {},
    override var currentState: VisibilityState = VisibilityState.INIT,
) : UiState, VisibilityStateHolder