package com.newoverride.listadetarefas.hooks

import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.newoverride.listadetarefas.model.TaskHookModel
import com.newoverride.listadetarefas.model.TaskModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun taskHook(): TaskHookModel {
    val focusManager = LocalFocusManager.current

    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val pressedAllTask = remember { mutableStateOf(false) }
    val isDeleteMode = remember { mutableStateOf<Boolean>(false) }
    val visibility = remember { mutableStateOf(false) }
    val allTask = remember { mutableIntStateOf(0) }
    val textFieldText = remember { mutableStateOf("") }
    val isPressed = remember { mutableStateOf(false) }
    val taskList = remember {
        mutableStateListOf<TaskModel>(
            TaskModel(
                id = mutableIntStateOf(1),
                message = "Comprar pão",
                isVisible = mutableStateOf(true)
            ),
            TaskModel(
                id = mutableIntStateOf(2),
                message = "Estudar Kotlin",
                isVisible = mutableStateOf(true)
            ),
            TaskModel(
                id = mutableIntStateOf(3),
                message = "Ir à academia",
                isVisible = mutableStateOf(true)
            ),
//            TaskModel(
//                id = mutableIntStateOf(4),
//                message = "Ler livro",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(5),
//                message = "Escrever código",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(6),
//                message = "Revisar projeto",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(7),
//                message = "Limpar a casa",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(8),
//                message = "Lavar roupa",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(9),
//                message = "Fazer mercado",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(10),
//                message = "Estudar Compose",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(11),
//                message = "Atualizar currículo",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(12),
//                message = "Planejar viagem",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(13),
//                message = "Marcar médico",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(14),
//                message = "Fazer backup",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(15),
//                message = "Meditar",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(16),
//                message = "Pagar contas",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(17),
//                message = "Jogar videogame",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(18),
//                message = "Estudar inglês",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(19),
//                message = "Organizar fotos",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(20),
//                message = "Testar app",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(21),
//                message = "Cozinhar almoço",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(22),
//                message = "Atualizar sistema",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(23),
//                message = "Aprender React",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(24),
//                message = "Jogar futebol",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(25),
//                message = "Pintar quadro",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(26),
//                message = "Trocar senha",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(27),
//                message = "Cuidar do jardim",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(28),
//                message = "Visitar amigos",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(29),
//                message = "Fazer yoga",
//                isVisible = mutableStateOf(true)
//            ),
//            TaskModel(
//                id = mutableIntStateOf(30),
//                message = "Escrever diário",
//                isVisible = mutableStateOf(true)
//            )
        )
    }

    fun animatedButton(active: MutableState<Boolean>) {
        active.value = true
        coroutineScope.launch {
            delay(200)
            active.value = false
        }
    }

    fun showAllTaskInfo(taskList: SnapshotStateList<TaskModel>) {
        val total = taskList.size
        allTask.intValue = total
    }

    fun showAllTaskInfoMarked(taskList: SnapshotStateList<TaskModel>) {
        val allTaskMarked = taskList.filter { it.marked.value }.size
        allTask.intValue = allTaskMarked
    }

    fun zeroAllTaskInfo() {
        allTask.intValue = 0
    }

    fun hideX(taskList: SnapshotStateList<TaskModel>) {
        taskList.forEach { items ->
            items.marked.value = false
            items.showCheckBox.value = false
        }
        visibility.value = false
        showAllTaskInfo(taskList)
        isDeleteMode.value = false
        focusManager.clearFocus()
    }

    fun rebuildId(taskList: SnapshotStateList<TaskModel>) {
        var index = 1
        taskList.forEach { items ->
            items.id.intValue = index
            index++
        }
        index = 1
    }

    fun onDelete(taskList: SnapshotStateList<TaskModel>) {
        animatedButton(isPressed)
        val itemsToRemove = taskList.filter { it.marked.value }
        if (itemsToRemove.isNotEmpty()) {
            itemsToRemove.forEach { item ->
                item.isVisible.value = false
            }
            coroutineScope.launch {
                delay(600)
                taskList.removeAll(itemsToRemove)
                hideX(taskList)
                rebuildId(taskList)
            }
        } else {
            hideX(taskList)
        }
    }

    fun checkedCont(taskList: SnapshotStateList<TaskModel>) {
        val totalChecked = taskList.filter { it.marked.value }.size
        allTask.intValue = totalChecked
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
        focusManager.clearFocus()
    }

    val showAllCheckBox = {
        showAllCheckBox(taskList)
    }

    fun verifyTextFieldAndAddTask(taskList: SnapshotStateList<TaskModel>) {
        val removeSpace = textFieldText.value.trim()
        focusManager.clearFocus()
        if (removeSpace.isEmpty()) return
        val index = taskList.size
        val newTask = TaskModel(
            id = mutableIntStateOf(index + 1),
            message = removeSpace,
            isVisible = mutableStateOf(false)
        )
        textFieldText.value = ""
        animatedButton(isPressed)
        focusManager.clearFocus()
        taskList.add(newTask)
        coroutineScope.launch {
            delay(100)
            newTask.isVisible.value = true
            lazyListState.animateScrollToItem(taskList.size - 1)
        }
    }

    fun saveTask(taskList: SnapshotStateList<TaskModel>) {
        verifyTextFieldAndAddTask(taskList)
        showAllTaskInfo(taskList)
    }

    val saveTask = {
        saveTask(taskList)
    }

    val hideX = {
        hideX(taskList)
    }

    val checkedCont = {
        checkedCont(taskList)
    }

    val addTaskDone = {
        saveTask(taskList)
    }

    fun selectAllTask(taskList: SnapshotStateList<TaskModel>) {
        if (taskList.isNotEmpty()) {
            if (taskList[0].showCheckBox.value) {
                val verifyMarked = taskList.filter { it.marked.value }.size
                if (verifyMarked == taskList.size) {
                    taskList.forEach { items ->
                        items.marked.value = false
                    }
                    showAllTaskInfoMarked(taskList)
                    animatedButton(pressedAllTask)
                    return
                }
                taskList.forEach { items ->
                    items.marked.value = true
                }
                showAllTaskInfoMarked(taskList)
                animatedButton(pressedAllTask)
            }
        }
    }

    val selectAllTask = {
        selectAllTask(taskList)
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
        isDeleteMode = isDeleteMode,
        addTaskDone = addTaskDone,
        lazyListState = lazyListState,
        selectAllTask = selectAllTask,
        pressedAllTask = pressedAllTask
    )
}