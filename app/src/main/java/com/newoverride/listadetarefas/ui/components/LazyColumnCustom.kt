package com.newoverride.listadetarefas.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import com.newoverride.listadetarefas.model.TaskModel

@Composable
fun LazyColumnCustom(
    taskList: SnapshotStateList<TaskModel>,
    modifier: Modifier,
    horizontalAlignment: Alignment.Horizontal,
    showAllCheckBox: () -> Unit,
    zeroAllTaskInfo: () -> Unit,
    checkedCont: () -> Unit,
    lazyListState: LazyListState,
    goToContent: () -> Unit,
    indexTask: MutableIntState
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment,
        state = lazyListState
    ) {
        itemsIndexed(taskList) { index, task ->
            val targetRotation by animateFloatAsState(
                targetValue = if (task.isVisible.value) 0f else 40f,
                animationSpec = tween(600),
                label = "rotate-exit"
            )
            val scale by animateFloatAsState(
                targetValue = if (task.isVisible.value) 1f else 3f,
                animationSpec = tween(600, easing = EaseOutBounce),
                label = "scale-boeing"
            )
            val shakeOffset by animateFloatAsState(
                targetValue = if (task.isVisible.value) 0f else 60f,
                animationSpec = infiniteRepeatable(
                    animation = tween(600, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "shake-exit"
            )
            AnimatedVisibility(
                visible = task.isVisible.value,
                enter = slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(durationMillis = 600)
                ) + fadeIn(animationSpec = tween(600)) + expandVertically(animationSpec = tween(600)),
                exit = slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(durationMillis = 600)
                ) + fadeOut(animationSpec = tween(600)) + shrinkVertically(animationSpec = tween(600)),
                modifier = Modifier
                    .rotate(targetRotation)
                    .scale(scale)
                    .graphicsLayer {
                        translationX = if (!task.isVisible.value) shakeOffset else 0f
                    }
            ) {
                TaskCardCustom(
                    task = task,
                    showAllCheckBox = showAllCheckBox,
                    zeroAllTaskInfo = zeroAllTaskInfo,
                    checkedCont = checkedCont,
                    goToContent = goToContent,
                    indexTask = indexTask
                )
            }
        }
    }
}