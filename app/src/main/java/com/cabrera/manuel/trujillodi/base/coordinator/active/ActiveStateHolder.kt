package com.cabrera.manuel.trujillodi.base.coordinator.active

interface ActiveStateHolder {

    val active: ActiveState

    val isActive: Boolean
        get() = ActiveState.ENABLED == active
}
