package com.newoverride.listadetarefas.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList

data class TaskHookModel(
    val textFieldText: MutableState<String>,
    val isPressed: MutableState<Boolean>,
    val taskList: SnapshotStateList<TaskModel>,
    val saveTask: () -> Unit,
)