package com.newoverride.listadetarefas.hooks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.newoverride.listadetarefas.model.TaskHookModel
import com.newoverride.listadetarefas.model.TaskModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun taskHook(): TaskHookModel {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val coroutineScope = rememberCoroutineScope()

    val isDeleteMode = remember { mutableStateOf<Boolean>(false) }
    val visibility = remember { mutableStateOf(false) }
    val allTask = remember { mutableIntStateOf(0) }
    val textFieldText = remember { mutableStateOf("") }
    val isPressed = remember { mutableStateOf(false) }
    val taskList = remember {
        mutableStateListOf<TaskModel>(
            TaskModel(id = 1, message = "Teste"),
            TaskModel(id = 2, message = "Mais um teste wad wa dawwa dwa dwadaw wa wad wad wa"),
            TaskModel(id = 1, message = "Teste"),
            TaskModel(id = 2, message = "Mais um teste wad wa dawwa dwa dwadaw wa wad wad wa"),
            TaskModel(id = 1, message = "Teste"),
            TaskModel(id = 2, message = "Mais um teste wad wa dawwa dwa dwadaw wa wad wad wa"),
            TaskModel(id = 1, message = "Teste"),
            TaskModel(id = 2, message = "Mais um teste wad wa dawwa dwa dwadaw wa wad wad wa"),
            TaskModel(id = 1, message = "Teste"),
            TaskModel(id = 2, message = "Mais um teste wad wa dawwa dwa dwadaw wa wad wad wa"),
            TaskModel(id = 1, message = "Teste"),
            TaskModel(id = 2, message = "Mais um teste wad wa dawwa dwa dwadaw wa wad wad wa"),
        )
    }

    fun animatedButton(active: MutableState<Boolean>) {
        active.value = true
        coroutineScope.launch {
            delay(300)
            active.value = false
        }
    }

    fun showAllTaskInfo(taskList: SnapshotStateList<TaskModel>) {
        val total = taskList.size
        allTask.intValue = total
    }

    fun zeroAllTaskInfo() {
       allTask.intValue = 0
    }

    fun onDelete(taskList: SnapshotStateList<TaskModel>) {
        animatedButton(isPressed)
        val itemsToRemove = taskList.filter { it.marked.value }
        taskList.removeAll(itemsToRemove)
        showAllTaskInfo(taskList)
        zeroAllTaskInfo()
    }

    val zeroAllTaskInfo = {
        zeroAllTaskInfo()
    }

    val onDelete = {
        onDelete(taskList)
    }

    fun showAllCheckBox(taskList: SnapshotStateList<TaskModel>) {
        taskList.forEach { items ->
            items.showCheckBox.value = true
        }
        visibility.value = true
        isDeleteMode.value = true
    }

    val showAllCheckBox = {
        showAllCheckBox(taskList)
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
        showAllTaskInfo(taskList)
    }

    fun hideX(taskList: SnapshotStateList<TaskModel>) {
        taskList.forEach { items ->
            items.marked.value = false
            items.showCheckBox.value = false
        }
        visibility.value = false
        showAllTaskInfo(taskList)
        isDeleteMode.value = false
    }

    val hideX = {
        hideX(taskList)
    }

    fun incrementTaskCont() {
        allTask.intValue++
    }

    val checkedCont = {
        incrementTaskCont()
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            if (taskList.isNotEmpty()) if (!taskList[0].showCheckBox.value) showAllTaskInfo(taskList)
        }
    }

    return TaskHookModel(
        textFieldText = textFieldText,
        isPressed = isPressed,
        taskList = taskList,
        saveTask = saveTask,
        allTask = allTask,
        showAllCheckBox = showAllCheckBox,
        zeroAllTaskInfo = zeroAllTaskInfo,
        visibility = visibility,
        hideX = hideX,
        checkedCont = checkedCont,
        onDelete = onDelete,
        isDeleteMode = isDeleteMode
    )
}