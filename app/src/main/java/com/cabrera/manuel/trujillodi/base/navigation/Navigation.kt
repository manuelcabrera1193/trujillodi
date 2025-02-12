package com.cabrera.manuel.trujillodi.base.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.cabrera.manuel.trujillodi.base.coordinator.Coordinator

class Navigation {

    private var _coordinators = mutableStateOf(value = listOf<Coordinator>())
    val coordinators: State<List<Coordinator>> = _coordinators

    private var _lastCoordinator: MutableState<Coordinator?> = mutableStateOf(value = null)
    val lastCoordinator: State<Coordinator?> = _lastCoordinator

    fun canGoBack(): Boolean {
        return coordinators.value.size > 1
    }

    fun add(coordinator: Coordinator) {
        _lastCoordinator.value = coordinators.value.lastOrNull().also { last -> last?.pause() }
        println("Navigation add ${coordinator.screen.route}")
        _coordinators.value = coordinators.value.toMutableList().apply {
            add(coordinator)
            coordinator.start()
        }
        println("coordinators ${coordinators.value.joinToString("-") { it.screen.route }}")
        _lastCoordinator.value = coordinators.value.lastOrNull()
    }

    fun replace(coordinator: Coordinator) {
        _coordinators.value = coordinators.value.toMutableList().apply {
            removeAt(coordinators.value.lastIndex)
            add(coordinator)
            coordinator.start()
        }
        _lastCoordinator.value = coordinators.value.lastOrNull()
    }

    fun remove(coordinator: Coordinator) {
        _coordinators.value = coordinators.value.toMutableList().apply {
            remove(coordinator)
        }
        _lastCoordinator.value = coordinators.value.lastOrNull()
    }

    fun pop() {
        _lastCoordinator.value = coordinators.value.lastOrNull().also { last -> last?.destroy() }
        _coordinators.value = coordinators.value.toMutableList().apply {
            removeAt(coordinators.value.lastIndex)
        }
        _lastCoordinator.value = coordinators.value.lastOrNull().also { last -> last?.resume() }
    }

    fun popTo(coordinator: Coordinator) {
        _coordinators.value = coordinators.value.toMutableList().apply {
            subList(0, coordinators.value.indexOf(coordinator))
        }
        _lastCoordinator.value = coordinators.value.lastOrNull()
    }
}