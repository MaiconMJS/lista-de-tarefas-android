package com.newoverride.listadetarefas.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.newoverride.listadetarefas.model.TaskModel

@Composable
fun LazyColumnCustom(taskList: SnapshotStateList<TaskModel>) {
    LazyColumn {
        items(taskList.size) { index ->
            TaskCardCustom(
                task = taskList[index]
            )
        }
    }
}