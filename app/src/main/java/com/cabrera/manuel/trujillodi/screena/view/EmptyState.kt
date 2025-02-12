package com.cabrera.manuel.trujillodi.screena.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cabrera.manuel.trujillodi.R

@Composable
fun EmptyState() {
    Row(
        Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(text = stringResource(id = R.string.empty_text))
    }

}

@Preview(showSystemUi = true)
@Composable
fun EmptyStatePreview() {
    EmptyState()
}
