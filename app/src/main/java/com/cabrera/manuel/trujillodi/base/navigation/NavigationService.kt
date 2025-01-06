package com.cabrera.manuel.trujillodi.base.navigation

import com.cabrera.manuel.trujillodi.ui.alert.AlertDialogState
import com.cabrera.manuel.trujillodi.ui.bottomsheet.BottomSheetState
import com.cabrera.manuel.trujillodi.ui.navigation.NavigationBarState
import com.cabrera.manuel.trujillodi.ui.toolbar.ToolbarState

interface NavigationService {
    val showDrawer: Boolean
    val toolbar: ToolbarState
    val alertDialog: AlertDialogState
    val bottomSheet: BottomSheetState
    val navigationBar: NavigationBarState

    fun goToHome()
    fun emitDataToParent(event: Any)
    fun updateShowDrawer(show: Boolean)
    fun updateNavigationBarState(navigationBarState: NavigationBarState)
    fun updateToolbarState(toolbarState: ToolbarState)
    fun updateAlertDialogState(alertDialogState: AlertDialogState)
    fun updateBottomSheetState(bottomSheetState: BottomSheetState)
}