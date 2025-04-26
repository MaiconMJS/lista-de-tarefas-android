package com.newoverride.listadetarefas.model

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList

data class TaskHookModel(
    val textFieldText: MutableState<String>,
    val isPressed: MutableState<Boolean>,
    val taskList: SnapshotStateList<TaskModel>,
    val saveTask: () -> Unit,
    val allTask: MutableIntState,
    val showAllCheckBox: () -> Unit,
    val zeroAllTaskInfo: () -> Unit,
    val visibility: MutableState<Boolean>,
    val hideX: () -> Unit,
    val checkedCont: () -> Unit,
    val onDelete: () -> Unit,
    val isDeleteMode: MutableState<Boolean>
)