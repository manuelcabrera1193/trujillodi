package com.cabrera.manuel.trujillodi.base.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
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
fun DisposableEffectWithLifeCycle(lifeCycleView: LifeCycleView) {

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    val currentOnCreate by rememberUpdatedState(lifeCycleView::onCreate)
    val currentOnStart by rememberUpdatedState(lifeCycleView::onStart)
    val currentOnResume by rememberUpdatedState(lifeCycleView::onResume)
    val currentOnPause by rememberUpdatedState(lifeCycleView::onPause)
    val currentOnStop by rememberUpdatedState(lifeCycleView::onStop)
    val currentOnDestroy by rememberUpdatedState(lifeCycleView::onDestroy)

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                ON_CREATE -> {
                    println("ON_CREATE")
                    currentOnCreate()
                }

                ON_START -> {
                    println("ON_START")
                    currentOnStart()
                }

                ON_RESUME -> {
                    println("ON_RESUME")
                    currentOnResume()
                }

                ON_PAUSE -> {
                    println("ON_PAUSE")
                    currentOnPause()
                }

                ON_STOP -> {
                    println("ON_STOP")
                    currentOnStop()
                }

                ON_DESTROY -> {
                    println("ON_DESTROY")
                    currentOnDestroy()
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