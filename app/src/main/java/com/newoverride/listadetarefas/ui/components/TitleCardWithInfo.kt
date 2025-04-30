package com.newoverride.listadetarefas.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.newoverride.listadetarefas.R
import com.newoverride.listadetarefas.dimens.Dimens
import com.newoverride.listadetarefas.ui.theme.White

@Composable
fun TitleCardWithInfo(
    allTask: MutableIntState,
    visibilityX: MutableState<Boolean>,
    hideX: () -> Unit,
    selectAllTask: () -> Unit,
    pressedAllTask: MutableState<Boolean>,
    contentView: MutableState<Boolean>,
    arrowBack: () -> Unit,
    indexTask: MutableIntState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(width = 0.dp, height = Dimens.boxTitleCardHeight)
            .background(color = MaterialTheme.colorScheme.tertiary),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val scale by animateFloatAsState(
            targetValue = if (pressedAllTask.value) 0.8f else 1.0f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            ),
            label = "spring-press-scale"
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(0.1f)
                .scale(scale)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { selectAllTask() })
                },
            horizontalArrangement = Arrangement.Center

        ) {
            Text(
                text = "\uD83D\uDCDD\n${if (contentView.value) indexTask.intValue else allTask.intValue}",
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(height = Dimens.heightContainerXAndArrowBack),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.labelSmall,
            )
            if (visibilityX.value) TextClose(
                text = "❌",
                hideX = hideX,
            )
            if (contentView.value) Icon(
                painterResource(id = R.drawable.baseline_undo_24),
                tint = White,
                contentDescription = stringResource(R.string.description_icon_back),
                modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(onTap = { arrowBack() })
                }
            )
        }
    }
}