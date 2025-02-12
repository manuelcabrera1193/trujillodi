package com.cabrera.manuel.trujillodi.base.coordinator.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle.Event.ON_ANY
import androidx.lifecycle.Lifecycle.Event.ON_CREATE
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.Lifecycle.Event.ON_PAUSE
import androidx.lifecycle.Lifecycle.Event.ON_RESUME
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun DisposableEffectWithLifeCycle(lifecycleCoordinator: LifeCycleCoordinator) {

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                ON_CREATE -> {
                    println("ON_CREATE")
                }

                ON_START -> {
                    println("ON_START")
                }

                ON_RESUME -> {
                    println("ON_RESUME")
                    lifecycleCoordinator.resume()
                }

                ON_PAUSE -> {
                    println("ON_PAUSE")
                    lifecycleCoordinator.pause()
                }

                ON_STOP -> {
                    println("ON_STOP")
                    lifecycleCoordinator.stop()
                }

                ON_DESTROY -> {
                    println("ON_DESTROY")
                    lifecycleCoordinator.destroy()
                }

                ON_ANY -> Unit
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            println("onDispose")
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}