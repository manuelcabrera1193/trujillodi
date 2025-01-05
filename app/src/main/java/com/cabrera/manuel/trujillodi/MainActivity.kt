package com.cabrera.manuel.trujillodi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.cabrera.manuel.trujillodi.base.navigation.Navigation
import com.cabrera.manuel.trujillodi.base.lifecycle.DisposableEffectWithLifeCycle
import com.cabrera.manuel.trujillodi.ui.theme.TrujilloDiTheme
import com.cabrera.manuel.trujillodi.ui.toolbar.Toolbar
import com.cabrera.manuel.trujillodi.ui.toolbar.ToolbarState
import com.cabrera.manuel.trujillodi.util.viewModelFactory

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
                viewModel.navigation.pop()
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
            TrujilloDiTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        navigation.coordinators.value.lastOrNull()?.screen?.CreateToolbar(Modifier)
                            ?: Toolbar(ToolbarState())
                    },
                    bottomBar = {},
                    content = { innerPadding ->
                        navigation.coordinators.value.lastOrNull()?.screen?.CreateBody(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) ?: finish()
                    }
                ).also {
                    DisposableEffectWithLifeCycle(viewModel)
                }
            }
        }
    }
}

