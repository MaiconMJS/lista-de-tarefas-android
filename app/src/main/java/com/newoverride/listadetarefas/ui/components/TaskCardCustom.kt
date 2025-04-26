package com.newoverride.listadetarefas.ui.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.newoverride.listadetarefas.dimens.Dimens
import com.newoverride.listadetarefas.model.TaskModel

@Composable
fun TaskCardCustom(
    task: TaskModel,
    showAllCheckBox: () -> Unit,
    zeroAllTaskInfo: () -> Unit,
    checkedCont: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = Dimens.cardElevation),
        shape = CardDefaults.shape,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(Dimens.taskCardHeight)
            .padding(vertical = Dimens.paddingVerticalCardTask)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        showAllCheckBox()
                        zeroAllTaskInfo()
                    },
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .padding(start = Dimens.paddingCheckBoxStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextCustomTaskCard(text = "${task.id} ->")
                if (task.showCheckBox.value) Checkbox(
                    checked = task.marked.value,
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.tertiary,
                        uncheckedColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    onCheckedChange = {
                        task.marked.value = it
                        checkedCont()
                    },
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.paddingTaskText)
            ) {
                TextCustomTaskCard(text = task.message)
            }
        }
    }
}