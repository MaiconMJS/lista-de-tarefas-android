package com.newoverride.listadetarefas.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.newoverride.listadetarefas.dimens.Dimens

@Composable
fun CircleCheckbox(
    checked: Boolean,
    onCheckedChange: () -> Unit
) {
    val pulseScale by animateFloatAsState(
        targetValue = if (checked) 1.2f else 1f,
        animationSpec = if (checked) infiniteRepeatable(
            animation = tween(300, easing = EaseOutCubic),
            repeatMode = RepeatMode.Reverse
        ) else tween(100),
        label = "pulse-animation"
    )
    val shadowElevation by animateDpAsState(
        targetValue = if (checked) 12.dp else 0.dp,
        animationSpec = if (checked) infiniteRepeatable(
            animation = tween(800, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ) else tween(500),
        label = "pulse-shadow"
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(Dimens.checkboxSize)
            .scale(pulseScale)
            .shadow(
                elevation = shadowElevation,
                shape = CircleShape,
                ambientColor = MaterialTheme.colorScheme.tertiary,
                spotColor = MaterialTheme.colorScheme.tertiary
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape
            )
            .background(
                color = if (checked) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.background,
                shape = CircleShape
            )
            .clickable { onCheckedChange() }
    ) {
        AnimatedVisibility(visible = checked) {
            val rotation by animateFloatAsState(
                targetValue = if (checked) 360f else 0f,
                animationSpec = tween(durationMillis = 1000, easing = EaseOutCubic),
                label = "rotation-check"
            )
            val scale by animateFloatAsState(
                targetValue = if (checked) 1f else 0f,
                animationSpec = tween(durationMillis = 600, easing = EaseOutCubic),
                label = "scale-check"
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .scale(scale)
                    .rotate(rotation)
            ) {
                Text(
                    text = "✓",
                    fontSize = Dimens.textToCheckbox,
                    color = MaterialTheme.colorScheme.onPrimary,
                    lineHeight = Dimens.textToCheckbox,
                )
            }
        }
    }
}