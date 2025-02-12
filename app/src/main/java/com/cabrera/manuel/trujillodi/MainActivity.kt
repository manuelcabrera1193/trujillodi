package com.cabrera.manuel.trujillodi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.cabrera.manuel.trujillodi.base.coordinator.lifecycle.DisposableEffectWithLifeCycle
import com.cabrera.manuel.trujillodi.base.coordinator.lifecycle.LifeCycleCoordinator
import com.cabrera.manuel.trujillodi.base.coordinator.lifecycle.LifeCycleCoordinatorImpl
import com.cabrera.manuel.trujillodi.base.navigation.Navigation
import com.cabrera.manuel.trujillodi.ui.drawer.CustomDrawer
import com.cabrera.manuel.trujillodi.ui.navigation.CustomNavigationBar
import com.cabrera.manuel.trujillodi.ui.theme.TrujilloDiTheme
import com.cabrera.manuel.trujillodi.ui.toolbar.CustomToolbar
import com.cabrera.manuel.trujillodi.util.chucker.ChuckerHttpInterceptorProvider
import com.cabrera.manuel.trujillodi.util.viewModelFactory
import com.willard.cabrera.data.service.base.HttpClientFactory
import com.willard.cabrera.data.service.base.HttpInterceptorProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val navigation: Navigation by lazy {
        Navigation()
    }

    private val lifecycleCoordinator: LifeCycleCoordinator by lazy {
        LifeCycleCoordinatorImpl(navigation.lastCoordinator)
    }

    private val interceptors: List<HttpInterceptorProvider> by lazy {
        listOf(ChuckerHttpInterceptorProvider(this))
    }

    private val httpClientFactory: HttpClientFactory by lazy {
        HttpClientFactory(baseUrl = "http://demo4550768.mockable.io/", interceptors = interceptors)
    }

    private val factory: ViewModelProvider.Factory by lazy {
        viewModelFactory {
            MainViewModel(
                navigation = navigation,
                httpClientFactory = httpClientFactory,
            )
        }
    }

    private val viewModel: MainViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
                    ModalNavigationDrawer(
                        gesturesEnabled = false,
                        drawerState = drawerState,
                        drawerContent = {
                            CustomDrawer { closeMenu(scope, drawerState) }
                        }
                    ) {

                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            topBar = {
                                CustomToolbar(viewModel.toolbarState.value)
                            },
                            bottomBar = {
                                CustomNavigationBar(viewModel.navigationBarState.value)
                            },
                            content = { innerPadding ->
                                AnimatedContent(
                                    targetState = navigation.coordinators.value,
                                    label = "AnimatedContent",
                                    transitionSpec = {
                                        if (targetState.size > initialState.size) {
                                            slideInHorizontally { it }
                                                .togetherWith(slideOutHorizontally { -it })
                                        } else {
                                            slideInHorizontally { -it }
                                                .togetherWith(slideOutHorizontally { it })
                                        }
                                    }
                                ) { targetState ->

                                    BackHandler {
                                        runCatching {
                                            if (navigation.canGoBack()) {
                                                navigation.pop()
                                            } else {
                                                finish()
                                            }
                                        }.onFailure {
                                            finish()
                                        }
                                    }

                                    targetState.lastOrNull()?.screen?.CreateBody(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(innerPadding)
                                    ) ?: finish()
                                }
                            }
                        )
                    }
                }

                DisposableEffectWithLifeCycle(lifecycleCoordinator = lifecycleCoordinator)
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

