package com.cabrera.manuel.trujillodi.base

class Navigation {

    internal var coordinators: MutableList<Coordinator> = mutableListOf()

    fun add(coordinator: Coordinator) {
        coordinators.add(coordinator)
    }

    fun replace(coordinator: Coordinator) {
        coordinators.removeAt(coordinators.lastIndex)
        coordinators.add(coordinator)
    }

    fun remove(coordinator: Coordinator) {
        coordinators.remove(coordinator)
    }

    fun pop() {
        coordinators.removeAt(coordinators.lastIndex)
    }

    fun popTo(coordinator: Coordinator) {
        coordinators = coordinators.subList(0, coordinators.indexOf(coordinator))
    }

    fun popToRoot() {
        coordinators = mutableListOf()
    }
}