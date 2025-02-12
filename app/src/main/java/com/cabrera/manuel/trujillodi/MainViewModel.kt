package com.cabrera.manuel.trujillodi

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cabrera.manuel.trujillodi.base.coordinator.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.Screen
import com.cabrera.manuel.trujillodi.base.navigation.Navigation
import com.cabrera.manuel.trujillodi.base.navigation.NavigationEvents
import com.cabrera.manuel.trujillodi.base.navigation.NavigationService
import com.cabrera.manuel.trujillodi.splash.SplashCoordinatorFactory
import com.cabrera.manuel.trujillodi.splash.SplashData
import com.cabrera.manuel.trujillodi.ui.alert.AlertDialogState
import com.cabrera.manuel.trujillodi.ui.bottomsheet.BottomSheetState
import com.cabrera.manuel.trujillodi.ui.navigation.NavigationBarState
import com.cabrera.manuel.trujillodi.ui.toolbar.ToolbarState
import com.willard.cabrera.data.service.base.HttpClientFactory
import kotlinx.coroutines.launch

class MainViewModel(
    val navigation: Navigation,
    private val httpClientFactory: HttpClientFactory,
) : ViewModel(), Coordinator, EmitterData, NavigationService {

    private val splashCoordinatorFactory by lazy {
        SplashCoordinatorFactory(this, this)
    }

    private val splashCoordinator by lazy {
        splashCoordinatorFactory.create()
    }

    override val parentCoordinator: Coordinator
        get() = this

    private var _navigationBarState: MutableState<NavigationBarState> =
        mutableStateOf(NavigationBarState())

    val navigationBarState: State<NavigationBarState>
        get() = _navigationBarState

    override val navigationBar: NavigationBarState
        get() = navigationBarState.value

    private var _showDrawerState: MutableState<Boolean> = mutableStateOf(false)

    val showDrawerState: State<Boolean>
        get() = _showDrawerState

    override val showDrawer: Boolean
        get() = showDrawerState.value

    private var _toolbarState: MutableState<ToolbarState> = mutableStateOf(ToolbarState())

    val toolbarState: State<ToolbarState>
        get() = _toolbarState

    override val toolbar: ToolbarState
        get() = _toolbarState.value

    private var _alertDialogState: MutableState<AlertDialogState> =
        mutableStateOf(AlertDialogState())

    val alertDialogState: State<AlertDialogState>
        get() = _alertDialogState

    override val alertDialog: AlertDialogState
        get() = alertDialogState.value

    private var _bottomSheetState: MutableState<BottomSheetState> =
        mutableStateOf(BottomSheetState())

    val bottomSheetState: State<BottomSheetState>
        get() = _bottomSheetState

    override val bottomSheet: BottomSheetState
        get() = bottomSheetState.value

    override fun goToHome() {
        viewModelScope.launch {
            val startCoordinator = StartCoordinator(
                navigation = navigation,
                scope = viewModelScope,
                emitterData = this@MainViewModel,
                navigationService = this@MainViewModel,
                httpClientFactory = httpClientFactory,
            )
            startCoordinator.start()
        }
    }

    override fun emitDataToParent(event: Any) {
        emitData(event)
    }

    override fun updateShowDrawer(show: Boolean) {
        _showDrawerState.value = show
    }

    override fun updateNavigationBarState(navigationBarState: NavigationBarState) {
        _navigationBarState.value = navigationBarState
    }

    override fun updateToolbarState(toolbarState: ToolbarState) {
        _toolbarState.value = toolbarState
    }

    override fun updateAlertDialogState(alertDialogState: AlertDialogState) {
        _alertDialogState.value = alertDialogState
    }

    override fun updateBottomSheetState(bottomSheetState: BottomSheetState) {
        _bottomSheetState.value = bottomSheetState
    }

    override val screen: Screen
        get() = splashCoordinator.screen

    override fun start() {
        println("MainViewModel start")
        navigation.add(splashCoordinator)
    }

    override fun emitData(data: Any) {
        when (data) {
            NavigationEvents.GO_TO_BACK -> {
                navigation.pop()
            }

            is NavigationBarState -> {
                _navigationBarState.value = data
            }

            SplashData.GO_TO_HOME -> {
                goToHome()
            }

            else -> {
                println("MainViewModel emitData not found: $data")
            }
        }
    }
}