package com.cabrera.manuel.trujillodi.ui.toolbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.cabrera.manuel.trujillodi.ui.theme.dimen12dp
import com.cabrera.manuel.trujillodi.ui.theme.dimen14dp
import com.cabrera.manuel.trujillodi.ui.theme.dimen16dp
import com.cabrera.manuel.trujillodi.ui.theme.dimen1dp
import com.cabrera.manuel.trujillodi.ui.theme.dimen56dp
import com.cabrera.manuel.trujillodi.ui.theme.dimen5dp
import com.cabrera.manuel.trujillodi.ui.theme.dimen8dp

@Composable
fun CustomToolbar(toolBarState: ToolbarState = ToolbarState()) {
    AnimatedVisibility(toolBarState.visible) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .height(dimen56dp)
                    .fillMaxWidth()
                    .background(toolBarState.backgroundColor)
                    .padding(horizontal = dimen14dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart
                ) {
                    if (toolBarState.showIconStart) {
                        Box(
                            modifier = Modifier
                                .absoluteOffset(x = -(dimen16dp))
                                .fillMaxHeight()
                                .clickable {
                                    toolBarState.eventStart.invoke()
                                }
                                .padding(horizontal = dimen16dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .fillMaxHeight(),
                                imageVector = toolBarState.navigationIcon,
                                tint = Color.Black,
                                contentDescription = null
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    if (toolBarState.showIconEnd) {
                        Row(
                            modifier = Modifier
                                .wrapContentWidth()
                                .border(
                                    width = dimen1dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(dimen8dp)
                                )
                                .clickable {
                                    toolBarState.eventEnd.invoke()
                                }
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(dimen8dp)
                                )
                                .padding(vertical = dimen8dp, horizontal = dimen12dp),
                            horizontalArrangement = Arrangement.spacedBy(dimen5dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = if (toolBarState.showMenu) {
                                    toolBarState.actionsIcon
                                } else {
                                    toolBarState.iconEnd
                                },
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                    }
                }
            }

            Text(
                text = toolBarState.title,
                textAlign = TextAlign.Center
            )
        }
    }
}