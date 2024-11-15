package com.cabrera.manuel.trujillodi.screena

import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.ui.UiEvents

class ScreenUiEventsA(
    private val emitterData: EmitterData,
    private val showButton: () -> Unit,
    private val hideButton: () -> Unit,
) : UiEvents, EmitterData by emitterData {

    fun goToScreenB(title: String, description: String) {
        emitterData.emitData(ScreenAData(title, description))
    }

}