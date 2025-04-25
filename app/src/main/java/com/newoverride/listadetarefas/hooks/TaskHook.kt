package com.newoverride.listadetarefas.hooks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.newoverride.listadetarefas.model.TaskHookModel
import com.newoverride.listadetarefas.model.TaskModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun taskHook(): TaskHookModel {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val coroutineScope = rememberCoroutineScope()

    val textFieldText = remember { mutableStateOf("") }
    val isPressed = remember { mutableStateOf(false) }
    val taskList = remember {
        mutableStateListOf<TaskModel>(
            TaskModel(id = 1, message = "Teste"),
            TaskModel(id = 2, message = "Mais um teste wad wa dawwa dwa dwadaw wa wad wad wa")
        )
    }

    fun animatedButton(active: MutableState<Boolean>) {
        active.value = true
        coroutineScope.launch {
            delay(300)
            active.value = false
        }
    }

    fun verifyTextFieldAndAddTask(taskList: SnapshotStateList<TaskModel>) {
        val removeSpace = textFieldText.value.trim()
        if (removeSpace.isEmpty()) return
        val index = taskList.size
        taskList.add(TaskModel(id = index + 1, message = removeSpace))
        textFieldText.value = ""
        animatedButton(isPressed)
        keyboardController?.hide()
        focusManager.clearFocus()
    }

    val saveTask = {
        verifyTextFieldAndAddTask(taskList)
    }

    return TaskHookModel(
        textFieldText = textFieldText,
        isPressed = isPressed,
        taskList = taskList,
        saveTask = saveTask
    )
}