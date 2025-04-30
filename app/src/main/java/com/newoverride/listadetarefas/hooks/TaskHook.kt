package com.newoverride.listadetarefas.hooks

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.newoverride.listadetarefas.R
import com.newoverride.listadetarefas.model.TaskHookModel
import com.newoverride.listadetarefas.model.TaskModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun taskHook(): TaskHookModel {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val pressedAllTask = remember { mutableStateOf(false) }
    val isDeleteMode = remember { mutableStateOf(false) }
    val visibilityX = remember { mutableStateOf(false) }
    val allTask = remember { mutableIntStateOf(0) }
    val textFieldText = remember { mutableStateOf("") }
    val isPressed = remember { mutableStateOf(false) }
    val contentView = remember { mutableStateOf(false) }
    val indexTask = remember { mutableIntStateOf(0) }
    val messageToEditor = remember { mutableStateOf("") }
    val whatsAppPressed = remember { mutableStateOf(false) }
    val taskList = remember {
        mutableStateListOf<TaskModel>(
            TaskModel(
                id = mutableIntStateOf(1),
                message = mutableStateOf("Comprar pão"),
            ),
            TaskModel(
                id = mutableIntStateOf(2),
                message = mutableStateOf("Estudar Kotlin"),
            ),
            TaskModel(
                id = mutableIntStateOf(3),
                message = mutableStateOf("Ir à academia"),
            ),
            TaskModel(
                id = mutableIntStateOf(4),
                message = mutableStateOf("Ler livro"),
            ),
            TaskModel(
                id = mutableIntStateOf(5),
                message = mutableStateOf("Escrever código"),
            ),
            TaskModel(
                id = mutableIntStateOf(6),
                message = mutableStateOf("Revisar projeto"),
            ),
            TaskModel(
                id = mutableIntStateOf(7),
                message = mutableStateOf("Limpar a casa"),
            ),
            TaskModel(
                id = mutableIntStateOf(8),
                message = mutableStateOf("Lavar roupa"),
            ),
            TaskModel(
                id = mutableIntStateOf(9),
                message = mutableStateOf("Fazer mercado"),
            ),
            TaskModel(
                id = mutableIntStateOf(10),
                message = mutableStateOf("Estudar Compose"),
            ),
            TaskModel(
                id = mutableIntStateOf(11),
                message = mutableStateOf("Atualizar currículo"),
            ),
            TaskModel(
                id = mutableIntStateOf(12),
                message = mutableStateOf("Planejar viagem"),
            ),
            TaskModel(
                id = mutableIntStateOf(13),
                message = mutableStateOf("Marcar médico"),
            ),
            TaskModel(
                id = mutableIntStateOf(14),
                message = mutableStateOf("Fazer backup"),
            ),
            TaskModel(
                id = mutableIntStateOf(15),
                message = mutableStateOf("Meditar"),
            ),
            TaskModel(
                id = mutableIntStateOf(16),
                message = mutableStateOf("Pagar contas"),
            ),
            TaskModel(
                id = mutableIntStateOf(17),
                message = mutableStateOf("Jogar videogame"),
            ),
            TaskModel(
                id = mutableIntStateOf(18),
                message = mutableStateOf("Estudar inglês"),
            ),
            TaskModel(
                id = mutableIntStateOf(19),
                message = mutableStateOf("Organizar fotos"),
            ),
            TaskModel(
                id = mutableIntStateOf(20),
                message = mutableStateOf("Testar app"),
            ),
            TaskModel(
                id = mutableIntStateOf(21),
                message = mutableStateOf("Cozinhar almoço"),
            ),
            TaskModel(
                id = mutableIntStateOf(22),
                message = mutableStateOf("Atualizar sistema"),
            ),
            TaskModel(
                id = mutableIntStateOf(23),
                message = mutableStateOf("Aprender React"),
            ),
            TaskModel(
                id = mutableIntStateOf(24),
                message = mutableStateOf("Jogar futebol"),
            ),
            TaskModel(
                id = mutableIntStateOf(25),
                message = mutableStateOf("Pintar quadro"),
            ),
            TaskModel(
                id = mutableIntStateOf(26),
                message = mutableStateOf("Trocar senha"),
            ),
            TaskModel(
                id = mutableIntStateOf(27),
                message = mutableStateOf("Cuidar do jardim"),
            ),
            TaskModel(
                id = mutableIntStateOf(28),
                message = mutableStateOf("Visitar amigos"),
            ),
            TaskModel(
                id = mutableIntStateOf(29),
                message = mutableStateOf("Fazer yoga"),
            ),
            TaskModel(
                id = mutableIntStateOf(30),
                message = mutableStateOf("Escrever diário"),
            )
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
        visibilityX.value = false
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
        visibilityX.value = true
        isDeleteMode.value = true
        focusManager.clearFocus()
    }

    val showAllCheckBox = {
        showAllCheckBox(taskList)
    }

    fun verifyTextFieldAndAddTask(taskList: SnapshotStateList<TaskModel>) {
        var removeSpace = textFieldText.value.trim()
        focusManager.clearFocus()
        if (removeSpace.isEmpty()) return
        val index = taskList.size
        val newTask = TaskModel(
            id = mutableIntStateOf(index + 1),
            message = mutableStateOf(removeSpace),
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
        if (taskList.isNotEmpty() && taskList[0].showCheckBox.value) {
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

    val goToContent = {
        if (taskList.isNotEmpty() && !taskList[0].showCheckBox.value) {
            contentView.value = !contentView.value
            messageToEditor.value =
                "${taskList[indexTask.intValue - 1].message.value} ${textFieldText.value}"
        }
    }

    fun arrowBack() {
        contentView.value = !contentView.value
    }

    val arrowBack = {
        arrowBack()
    }

    val selectAllTask = {
        selectAllTask(taskList)
    }

    val taskEditorDone = {
        animatedButton(isPressed)
        taskList[indexTask.intValue - 1].message.value = messageToEditor.value
        arrowBack()
    }

    val whatsAppShare = {
        animatedButton(whatsAppPressed)
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            setPackage("com.whatsapp")
            putExtra(Intent.EXTRA_TEXT, messageToEditor.value)
        }
        try {
            context.startActivity(intent)
        } catch (error: ActivityNotFoundException) {
            Toast.makeText(context, context.getString(R.string.erro), Toast.LENGTH_SHORT).show()
        }
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
        visibilityX = visibilityX,
        hideX = hideX,
        checkedCont = checkedCont,
        onDelete = onDelete,
        isDeleteMode = isDeleteMode,
        addTaskDone = addTaskDone,
        lazyListState = lazyListState,
        selectAllTask = selectAllTask,
        pressedAllTask = pressedAllTask,
        goToContent = goToContent,
        contentView = contentView,
        indexTask = indexTask,
        arrowBack = arrowBack,
        taskEditorDone = taskEditorDone,
        messageToEditor = messageToEditor,
        whatsAppPressed = whatsAppPressed,
        whatsAppShare = whatsAppShare
    )
}