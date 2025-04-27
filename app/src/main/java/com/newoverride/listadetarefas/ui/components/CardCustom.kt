package com.newoverride.listadetarefas.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.newoverride.listadetarefas.dimens.Dimens
import com.newoverride.listadetarefas.model.TaskModel

@Composable
fun CardCustom(
    textFieldText: MutableState<String>,
    saveTask: () -> Unit,
    taskList: SnapshotStateList<TaskModel>,
    isPressed: MutableState<Boolean>,
    allTask: MutableIntState,
    showAllCheckBox: () -> Unit,
    zeroAllTaskInfo: () -> Unit,
    visibility: MutableState<Boolean>,
    hideX: () -> Unit,
    checkedCont: () -> Unit,
    onDelete: () -> Unit,
    isDeleteMode: MutableState<Boolean>,
    addTaskDone: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = Dimens.cardElevation),
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .heightIn(max = Dimens.cardHeight)
    ) {
        TitleCardWithInfo(allTask = allTask, visibility = visibility, hideX = hideX)
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumnCustom(
                taskList = taskList,
                checkedCont = checkedCont,
                showAllCheckBox = showAllCheckBox,
                zeroAllTaskInfo = zeroAllTaskInfo,
                modifier = Modifier
                    .fillMaxHeight(0.74f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextFieldCustom(textFieldText = textFieldText, addTaskDone = addTaskDone)
                ButtonCustom(
                    onClick = saveTask,
                    isPressed = isPressed,
                    onDelete = onDelete,
                    isDeleteMode = isDeleteMode
                )
            }
        }
    }
}