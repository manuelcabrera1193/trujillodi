package com.cabrera.manuel.trujillodi.splash

import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.ui.UiEvents

class SplashUiEvents(
    private val emitterData: EmitterData,
) : UiEvents {
    fun goToHome() {
        println("goToHome")
        emitterData.emitData(SplashData("Init", "home"))
    }
}