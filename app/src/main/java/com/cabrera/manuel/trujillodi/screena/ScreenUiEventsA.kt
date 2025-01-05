package com.cabrera.manuel.trujillodi.screena

import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.ui.UiEvents

class ScreenUiEventsA(
    private val emitterData: EmitterData,
) : UiEvents, EmitterData by emitterData {

    fun goToScreenB(title: String, description: String) {
        println("goToScreenB")
        emitterData.emitData(ScreenAData(title, description))
    }

}