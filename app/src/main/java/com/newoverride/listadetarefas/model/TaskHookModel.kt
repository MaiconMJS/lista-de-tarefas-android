package com.newoverride.listadetarefas.model

import androidx.compose.foundation.lazy.LazyListState
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
    val visibilityX: MutableState<Boolean>,
    val hideX: () -> Unit,
    val checkedCont: () -> Unit,
    val onDelete: () -> Unit,
    val isDeleteMode: MutableState<Boolean>,
    val addTaskDone: () -> Unit,
    val lazyListState: LazyListState,
    val selectAllTask: () -> Unit,
    val pressedAllTask: MutableState<Boolean>,
    val goToContent: () -> Unit,
    val contentView: MutableState<Boolean>,
    val indexTask: MutableIntState,
    val arrowBack: () -> Unit,
    val taskEditorDone: () -> Unit,
    val messageToEditor: MutableState<String>,
    val whatsAppPressed: MutableState<Boolean>,
    val whatsAppShare: () -> Unit,
    val hideButtonEditor: MutableState<Boolean>
)