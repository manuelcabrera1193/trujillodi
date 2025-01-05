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
import com.cabrera.manuel.trujillodi.base.Navigation
import com.cabrera.manuel.trujillodi.base.lifecycle.DisposableEffectWithLifeCycle
import com.cabrera.manuel.trujillodi.ui.theme.TrujilloDiTheme
import com.cabrera.manuel.trujillodi.util.viewModelFactory

class MainActivity : ComponentActivity() {

    private val navigation: Navigation by lazy {
        Navigation()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val factory by lazy {
            viewModelFactory { MainViewModel(navigation) }
        }
        val viewModel: MainViewModel by viewModels { factory }

        val handleOnBackPressed = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                runCatching {
                    viewModel.navigation.pop()
                    println("coordinators ${viewModel.navigation.coordinators.value.joinToString("-") { it.screen.route }}")
                }.onFailure {
                    finish()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, handleOnBackPressed)
        viewModel.start()
        setContent {
            TrujilloDiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    navigation.coordinators.value.lastOrNull()?.screen?.Create(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) ?: finish()
                }.also {
                    DisposableEffectWithLifeCycle(viewModel)
                }
            }
        }
    }
}

