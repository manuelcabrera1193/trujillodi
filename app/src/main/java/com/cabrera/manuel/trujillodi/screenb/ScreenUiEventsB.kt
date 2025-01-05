package com.cabrera.manuel.trujillodi.screenb

import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.ui.UiEvents

class ScreenUiEventsB(
    private val emitterData: EmitterData,
) : UiEvents {

    fun goToScreenC() {
        println("goToScreenC")
        emitterData.emitData(ScreenBData("Completed"))
    }
}