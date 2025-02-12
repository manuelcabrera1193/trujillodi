package com.cabrera.manuel.trujillodi.screena.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.cabrera.manuel.trujillodi.base.visibility.VisibilityState
import com.cabrera.manuel.trujillodi.screena.ScreenUiStateA

@Composable
fun ListRecipes(data: ScreenUiStateA) {
    Column {

        when(data.currentState){
            VisibilityState.EMPTY -> EmptyState()
            VisibilityState.SUCCESS -> SuccessState(data.recipes, data.goToDetail)
            VisibilityState.ERROR -> ErrorState(data.retry)
            else -> LoadingState()
        }
    }
}
