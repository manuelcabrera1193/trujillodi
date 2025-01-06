package com.cabrera.manuel.trujillodi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.cabrera.manuel.trujillodi.base.lifecycle.DisposableEffectWithLifeCycle
import com.cabrera.manuel.trujillodi.base.navigation.Navigation
import com.cabrera.manuel.trujillodi.ui.navigation.CustomNavigationBar
import com.cabrera.manuel.trujillodi.ui.theme.TrujilloDiTheme
import com.cabrera.manuel.trujillodi.ui.toolbar.CustomToolbar
import com.cabrera.manuel.trujillodi.util.viewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val navigation: Navigation by lazy {
        Navigation()
    }

    private val factory: ViewModelProvider.Factory by lazy {
        viewModelFactory { MainViewModel(navigation) }
    }

    private val viewModel: MainViewModel by viewModels { factory }

    private val handleOnBackPressed = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            runCatching {
                navigation.pop()
                println("coordinators ${viewModel.navigation.coordinators.value.joinToString("-") { it.screen.route }}")
            }.onFailure {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        onBackPressedDispatcher.addCallback(this, handleOnBackPressed)
        viewModel.start()
        setContent {

            val scope = rememberCoroutineScope()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

            LaunchedEffect(viewModel.showDrawerState) {
                if (viewModel.showDrawerState.value) {
                    openMenu(scope, drawerState)
                } else {
                    closeMenu(scope, drawerState)
                }
            }

            TrujilloDiTheme {
                Surface {
//                    ModalNavigationDrawer(
//                        gesturesEnabled = false,
//                        drawerState = drawerState,
//                        drawerContent = {
//                            CustomDrawer { closeMenu(scope, drawerState) }
//                        }
//                    ) {
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            topBar = {
                                CustomToolbar(viewModel.toolbarState.value)
                            },
                            bottomBar = {
                                CustomNavigationBar(viewModel.navigationBarState.value)
                            },
                            content = { innerPadding ->
                                navigation.coordinators.value.lastOrNull()?.screen?.CreateBody(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                ) ?: finish()
                            }
                        )
//                    }
                }.also {
                    DisposableEffectWithLifeCycle(viewModel)
                }
            }
        }
    }

    private fun openMenu(scope: CoroutineScope, drawerState: DrawerState) {
        scope.launch { with(drawerState) { if (!isOpen) open() } }
    }

    private fun closeMenu(scope: CoroutineScope, drawerState: DrawerState) {
        scope.launch { with(drawerState) { if (isOpen) close() } }
    }
}

