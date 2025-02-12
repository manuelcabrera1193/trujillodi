package com.cabrera.manuel.trujillodi.base.visibility

enum class VisibilityState {
    INIT,
    LOADING,
    EMPTY,
    SUCCESS,
    ERROR;

    companion object {
        @JvmStatic
        fun from(list: List<*>): VisibilityState = if (list.isEmpty()) EMPTY else SUCCESS
    }
}
