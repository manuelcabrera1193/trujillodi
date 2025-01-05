package com.cabrera.manuel.trujillodi

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cabrera.manuel.trujillodi.base.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.Screen
import com.cabrera.manuel.trujillodi.base.navigation.Navigation
import com.cabrera.manuel.trujillodi.base.navigation.NavigationEvents
import com.cabrera.manuel.trujillodi.splash.SplashCoordinatorFactory
import com.cabrera.manuel.trujillodi.splash.SplashData
import com.cabrera.manuel.trujillodi.ui.toolbar.ToolbarState
import kotlinx.coroutines.launch

class MainViewModel(val navigation: Navigation) : ViewModel(), Coordinator, EmitterData {

    private val splashCoordinatorFactory by lazy {
        SplashCoordinatorFactory(viewModelScope, this, this)
    }

    private val splashCoordinator by lazy {
        splashCoordinatorFactory.create()
    }

    override val parentCoordinator: Coordinator
        get() = this

    override val screen: Screen
        get() = splashCoordinator.screen

    override fun start() {
        navigation.add(splashCoordinator)
        println("MainViewModel start")
    }

    override fun emitData(data: Any) {
        when (data) {
            NavigationEvents.GO_TO_BACK -> {
                navigation.pop()
            }

            is SplashData -> goToStartCoordinator()

            else -> {
                println("MainViewModel emitData not found: $data")
            }
        }
    }

    private fun goToStartCoordinator() {
        viewModelScope.launch {
            val startCoordinator = StartCoordinator(
                navigation = navigation,
                scope = viewModelScope,
                emitterData = this@MainViewModel
            )
            startCoordinator.start()
        }
    }
}