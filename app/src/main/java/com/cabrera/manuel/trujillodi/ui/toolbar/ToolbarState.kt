package com.cabrera.manuel.trujillodi.ui.toolbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class ToolbarState(
    val visible: Boolean = false,
    val content: @Composable () -> Unit = {},
    val navigationIcon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    val contentColor: Color = Color.Transparent,
    val backgroundColor: Color = Color.Transparent,
    val elevation: Int = 0,
    val showMenu: Boolean = true,
    val actionsIcon: ImageVector = Icons.Default.Menu,
    val iconEnd: ImageVector = Icons.Default.Close,
    val showIconStart: Boolean = false,
    val showIconEnd: Boolean = false,
    val eventStart: () -> Unit = {},
    val eventEnd: () -> Unit = {},
)

