package com.newoverride.listadetarefas.ui.view

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.newoverride.listadetarefas.hooks.taskHook
import com.newoverride.listadetarefas.ui.components.CardCustom
import com.newoverride.listadetarefas.ui.theme.ListaDeTarefasTheme

@Composable
fun HomeView() {

    val taskHook = taskHook()

    BackHandler(enabled = taskHook.contentView.value) {
        taskHook.arrowBack()
    }

    ListaDeTarefasTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .imePadding()
                    .animateContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CardCustom(
                    textFieldText = taskHook.textFieldText,
                    saveTask = taskHook.saveTask,
                    isPressed = taskHook.isPressed,
                    taskList = taskHook.taskList,
                    showAllCheckBox = taskHook.showAllCheckBox,
                    allTask = taskHook.allTask,
                    zeroAllTaskInfo = taskHook.zeroAllTaskInfo,
                    visibilityX = taskHook.visibilityX,
                    hideX = taskHook.hideX,
                    checkedCont = taskHook.checkedCont,
                    onDelete = taskHook.onDelete,
                    isDeleteMode = taskHook.isDeleteMode,
                    addTaskDone = taskHook.addTaskDone,
                    lazyListState = taskHook.lazyListState,
                    selectAllTask = taskHook.selectAllTask,
                    pressedAllTask = taskHook.pressedAllTask,
                    goToContent = taskHook.goToContent,
                    contentView = taskHook.contentView,
                    indexTask = taskHook.indexTask,
                    arrowBack = taskHook.arrowBack,
                    taskEditorDone = taskHook.taskEditorDone,
                    messageToEditor = taskHook.messageToEditor,
                    whatsAppPressed = taskHook.whatsAppPressed,
                    whatsAppShare = taskHook.whatsAppShare
                )
            }
        }
    }
}