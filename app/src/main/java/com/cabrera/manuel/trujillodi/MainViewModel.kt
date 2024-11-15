package com.cabrera.manuel.trujillodi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cabrera.manuel.trujillodi.base.Coordinator
import com.cabrera.manuel.trujillodi.base.Navigation
import com.cabrera.manuel.trujillodi.base.Screen

class MainViewModel : ViewModel(), Coordinator {

    override val parentCoordinator: Coordinator
        get() = this

    override val screen: Screen
        get() = currentCoordinator.screen

    override val navigation = Navigation()

    val currentCoordinator: Coordinator
        get() = navigation.coordinators.last()

    fun start() {
        val startCoordinator = StartCoordinator(navigation, viewModelScope)
        navigation.add(startCoordinator)
    }

}