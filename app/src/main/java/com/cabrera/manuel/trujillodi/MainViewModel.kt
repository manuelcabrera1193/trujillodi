package com.cabrera.manuel.trujillodi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cabrera.manuel.trujillodi.base.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.Navigation
import com.cabrera.manuel.trujillodi.base.Screen
import com.cabrera.manuel.trujillodi.base.lifecycle.LifeCycleView
import com.cabrera.manuel.trujillodi.splash.SplashCoordinatorFactory
import com.cabrera.manuel.trujillodi.splash.SplashData
import kotlinx.coroutines.launch

class MainViewModel(val navigation: Navigation) : ViewModel(), Coordinator, LifeCycleView {

    private val emitterData: EmitterData = object : EmitterData {
        override fun emitData(event: Any) {
            when (event) {
                is SplashData -> {
                    viewModelScope.launch {
                        val startCoordinator = StartCoordinator(navigation, viewModelScope)
                        startCoordinator.start()
                    }
                }
            }
        }
    }

    private val splashCoordinatorFactory by lazy {
        SplashCoordinatorFactory(viewModelScope, this, emitterData)
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
}