package com.newoverride.listadetarefas.hooks

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.newoverride.listadetarefas.App
import com.newoverride.listadetarefas.R
import com.newoverride.listadetarefas.infra.entitys.Task
import com.newoverride.listadetarefas.model.TaskHookModel
import com.newoverride.listadetarefas.model.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun taskHook(): TaskHookModel {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val app = context.applicationContext as? App
    val dao = app?.db?.taskDao()
    val loadedOnce = remember { mutableStateOf(false) }
    val compareText = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val pressedAllTask = remember { mutableStateOf(false) }
    val isDeleteMode = remember { mutableStateOf(false) }
    val visibilityX = remember { mutableStateOf(false) }
    val hideButtonEditor = remember { mutableStateOf(false) }
    val allTask = remember { mutableIntStateOf(0) }
    val textFieldText = remember { mutableStateOf("") }
    val isPressed = remember { mutableStateOf(false) }
    val contentView = remember { mutableStateOf(false) }
    val indexTask = remember { mutableIntStateOf(0) }
    val messageToEditor = remember { mutableStateOf("") }
    val whatsAppPressed = remember { mutableStateOf(false) }
    val taskList = remember { mutableStateListOf<TaskModel>() }

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
        coroutineScope.launch(Dispatchers.IO) {
            // DELETE ON DATABASE
            itemsToRemove.forEach { items ->
                dao?.deleteTask(
                    Task(
                        uid = items.idDatabase.intValue,
                        taskName = items.message.value
                    )
                )
            }
        }
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
        // FETCH MAX ID FROM DATABASE
        coroutineScope.launch(Dispatchers.IO) {
            // SAVE ON DATABASE
            val generatedId = dao?.insertTask(Task(taskName = removeSpace))?.toInt() ?: 0
            val index = taskList.size
            val newTask = TaskModel(
                id = mutableIntStateOf(index + 1),
                idDatabase = mutableIntStateOf(generatedId),
                message = mutableStateOf(removeSpace),
                isVisible = mutableStateOf(false)
            )
            taskList.add(newTask)
            coroutineScope.launch {
                delay(100)
                newTask.isVisible.value = true
                lazyListState.animateScrollToItem(taskList.size - 1)
            }
            showAllTaskInfo(taskList)
        }
        textFieldText.value = ""
        animatedButton(isPressed)
        focusManager.clearFocus()
    }

    fun saveTask(taskList: SnapshotStateList<TaskModel>) {
        verifyTextFieldAndAddTask(taskList)
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
                taskList[indexTask.intValue - 1].message.value + " "
            compareText.value = messageToEditor.value
        }
    }

    LaunchedEffect(messageToEditor.value) {
        hideButtonEditor.value = compareText.value != messageToEditor.value
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

    fun taskEditorDone() {
        val uid = taskList[indexTask.intValue - 1].idDatabase.intValue
        coroutineScope.launch(Dispatchers.IO) {
            dao?.updateTask(Task(uid = uid, taskName = messageToEditor.value))
        }
        animatedButton(isPressed)
        coroutineScope.launch {
            delay(300)
            taskList[indexTask.intValue - 1].message.value = messageToEditor.value
            arrowBack()
        }
    }

    val taskEditorDone = {
        taskEditorDone()
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
            Toast.makeText(
                context,
                context.getString(R.string.erro) + " " + error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            if (taskList.isNotEmpty()) if (!taskList[0].showCheckBox.value) showAllTaskInfo(taskList)
        }
    }

    fun loadDatabaseStartedActivity(event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_CREATE && !loadedOnce.value) {
            loadedOnce.value = true
            coroutineScope.launch(Dispatchers.IO) {
                val allDatabaseTask = dao?.getAllTasks()
                if (allDatabaseTask?.isNotEmpty() == true) {
                    allDatabaseTask.forEach { items ->
                        taskList.add(
                            TaskModel(
                                id = mutableIntStateOf(items.uid),
                                idDatabase = mutableIntStateOf(items.uid),
                                message = mutableStateOf(items.taskName)
                            )
                        )
                    }
                }
                rebuildId(taskList)
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            loadDatabaseStartedActivity(event)
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
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
        whatsAppShare = whatsAppShare,
        hideButtonEditor = hideButtonEditor
    )
}