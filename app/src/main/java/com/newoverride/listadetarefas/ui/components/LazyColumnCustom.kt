package com.newoverride.listadetarefas.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.newoverride.listadetarefas.model.TaskModel

@Composable
fun LazyColumnCustom(
    taskList: SnapshotStateList<TaskModel>,
    modifier: Modifier,
    horizontalAlignment: Alignment.Horizontal,
    showAllCheckBox: () -> Unit,
    zeroAllTaskInfo: () -> Unit,
    checkedCont: () -> Unit
) {
    LazyColumn(modifier = modifier, horizontalAlignment = horizontalAlignment) {
        items(taskList.size) { index ->
            TaskCardCustom(
                task = taskList[index],
                showAllCheckBox = showAllCheckBox,
                zeroAllTaskInfo = zeroAllTaskInfo,
                checkedCont = checkedCont
            )
        }
    }
}