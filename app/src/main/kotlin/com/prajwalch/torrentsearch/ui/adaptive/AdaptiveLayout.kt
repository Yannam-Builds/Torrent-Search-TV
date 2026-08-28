package com.prajwalch.torrentsearch.ui.adaptive

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/** Window-driven layouts shared by phones, foldables, tablets, desktop windows, and TVs. */
enum class AdaptiveLayout {
    Compact,
    Medium,
    Expanded,
    Television;

    val usesTwoPanes: Boolean
        get() = this == Expanded || this == Television
}

val LocalAdaptiveLayout = staticCompositionLocalOf { AdaptiveLayout.Compact }

/**
 * Re-evaluates the layout whenever the app window changes size. This supports rotation,
 * split-screen, freeform windows, and foldable state changes without requiring separate APKs.
 */
@Composable
fun AdaptiveContentFrame(
    isTelevision: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        val layout = when {
            isTelevision -> AdaptiveLayout.Television
            maxWidth < 600.dp -> AdaptiveLayout.Compact
            maxWidth < 840.dp -> AdaptiveLayout.Medium
            else -> AdaptiveLayout.Expanded
        }

        val contentModifier = when (layout) {
            AdaptiveLayout.Compact -> Modifier.fillMaxSize()
            AdaptiveLayout.Medium -> Modifier
                .fillMaxHeight()
                .widthIn(max = 840.dp)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
            AdaptiveLayout.Expanded -> Modifier
                .fillMaxHeight()
                .widthIn(max = 1280.dp)
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
            AdaptiveLayout.Television -> Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 20.dp)
        }

        CompositionLocalProvider(LocalAdaptiveLayout provides layout) {
            Box(modifier = contentModifier) { content() }
        }
    }
}
