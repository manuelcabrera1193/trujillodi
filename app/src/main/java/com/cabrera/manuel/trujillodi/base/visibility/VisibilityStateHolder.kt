package com.cabrera.manuel.trujillodi.base.visibility

interface VisibilityStateHolder {

    var currentState: VisibilityState

    val isLoadingVisible: Boolean
        get() = VisibilityState.LOADING == currentState
    val isErrorVisible: Boolean
        get() = VisibilityState.ERROR == currentState
    val isSuccessVisible: Boolean
        get() = VisibilityState.SUCCESS == currentState
    val isEmptyVisible: Boolean
        get() = VisibilityState.EMPTY == currentState
}
