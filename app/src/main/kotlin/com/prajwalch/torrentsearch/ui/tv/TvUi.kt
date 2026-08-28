package com.prajwalch.torrentsearch.ui.tv

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawOutline
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

/** True while the UI is running on an Android TV-class device. */
val LocalIsTelevision = staticCompositionLocalOf { false }

/**
 * Detects both certified Android TV/Google TV devices and devices that expose TV UI mode
 * without advertising the Leanback feature.
 */
fun Context.isTelevisionDevice(): Boolean {
    val currentUiMode = resources.configuration.uiMode and Configuration.UI_MODE_TYPE_MASK
    return currentUiMode == Configuration.UI_MODE_TYPE_TELEVISION ||
            packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK)
}

/** Adds an overscan-safe inset only on televisions. */
@Composable
fun Modifier.tvSafeArea(): Modifier = if (LocalIsTelevision.current) {
    padding(horizontal = 32.dp, vertical = 20.dp)
} else {
    this
}

/**
 * Draws a persistent, high-contrast focus ring and a subtle scale transition for D-pad focus.
 * The modifier intentionally does not create another focus target; it observes the focus target
 * supplied by clickable, combinedClickable, or a Material component later in the modifier chain.
 */
@Composable
fun Modifier.tvFocusHighlight(
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.medium,
): Modifier {
    if (!LocalIsTelevision.current || !enabled) return this

    var isFocused by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.025f else 1f,
        animationSpec = spring(stiffness = 700f, dampingRatio = 0.82f),
        label = "TV focus scale",
    )
    val focusColor = MaterialTheme.colorScheme.primary

    return this
        .onFocusChanged { isFocused = it.isFocused }
        .zIndex(if (isFocused) 1f else 0f)
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .drawWithCache {
            val outline = shape.createOutline(size, layoutDirection, this)
            onDrawWithContent {
                drawContent()
                if (isFocused) {
                    drawOutline(
                        outline = outline,
                        color = focusColor,
                        style = Stroke(width = 3.dp.toPx()),
                    )
                }
            }
        }
}
