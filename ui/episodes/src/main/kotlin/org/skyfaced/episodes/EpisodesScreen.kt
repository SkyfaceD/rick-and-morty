package org.skyfaced.episodes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun EpisodesRoute(
    modifier: Modifier = Modifier,
) {
    EpisodesScreen(
        modifier = modifier
    )
}

@Composable
fun EpisodesScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Episodes Screen")
    }
}