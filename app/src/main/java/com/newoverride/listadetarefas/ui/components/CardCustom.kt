package com.newoverride.listadetarefas.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.newoverride.listadetarefas.dimens.Dimens
import com.newoverride.listadetarefas.model.TaskModel

@Composable
fun CardCustom(
    textFieldText: MutableState<String>,
    saveTask: () -> Unit,
    taskList: SnapshotStateList<TaskModel>,
    isPressed: MutableState<Boolean>,
    allTask: MutableIntState,
    showAllCheckBox: () -> Unit,
    zeroAllTaskInfo: () -> Unit,
    visibilityX: MutableState<Boolean>,
    hideX: () -> Unit,
    checkedCont: () -> Unit,
    onDelete: () -> Unit,
    isDeleteMode: MutableState<Boolean>,
    addTaskDone: () -> Unit,
    lazyListState: LazyListState,
    selectAllTask: () -> Unit,
    pressedAllTask: MutableState<Boolean>,
    goToContent: () -> Unit,
    contentView: MutableState<Boolean>,
    indexTask: MutableIntState,
    arrowBack: () -> Unit,
    taskEditorDone: () -> Unit,
    messageToEditor: MutableState<String>,
    whatsAppPressed: MutableState<Boolean>,
    whatsAppShare: () -> Unit,
    hideButtonEditor: MutableState<Boolean>
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = Dimens.cardElevation),
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .heightIn(max = Dimens.cardHeight)
    ) {
        TitleCardWithInfo(
            allTask = allTask,
            visibilityX = visibilityX,
            hideX = hideX,
            selectAllTask = selectAllTask,
            pressedAllTask = pressedAllTask,
            contentView = contentView,
            arrowBack = arrowBack,
            indexTask = indexTask
        )
        Box(modifier = Modifier.fillMaxSize()) {
            if (!contentView.value) LazyColumnCustom(
                taskList = taskList,
                checkedCont = checkedCont,
                showAllCheckBox = showAllCheckBox,
                lazyListState = lazyListState,
                zeroAllTaskInfo = zeroAllTaskInfo,
                modifier = Modifier
                    .fillMaxHeight(0.74f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                goToContent = goToContent,
                indexTask = indexTask
            ) else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    TextFieldEditorView(textFieldTextEditor = messageToEditor)
                }
            }
            if (contentView.value) Box(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = Dimens.whatsAppHeight, end = Dimens.whatsAppWidth)
                ) {
                    WhatsAppButton(whatsAppShare = whatsAppShare, whatsAppPressed = whatsAppPressed)
                }
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .imePadding()
                    .animateContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!contentView.value) {
                    TextFieldCustom(
                        textFieldText = textFieldText,
                        addTaskDone = addTaskDone,
                    )
                    ButtonCustom(
                        onClick = saveTask,
                        isPressed = isPressed,
                        onDelete = onDelete,
                        isDeleteMode = isDeleteMode,
                    )
                } else if (contentView.value && hideButtonEditor.value) {
                    ButtonEditor(isPressed = isPressed, taskEditorDone = taskEditorDone)
                }
            }
        }
    }
}