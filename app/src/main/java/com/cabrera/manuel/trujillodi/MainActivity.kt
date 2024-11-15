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
import com.cabrera.manuel.trujillodi.ui.theme.TrujilloDiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel by viewModels<MainViewModel>()

        val handleOnBackPressed =  object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                try {
                    viewModel.navigation.pop()
                } catch (e: Exception) {
                    finish()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, handleOnBackPressed)
        viewModel.start()
        setContent {
            TrujilloDiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    viewModel.currentCoordinator.screen.Create(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

