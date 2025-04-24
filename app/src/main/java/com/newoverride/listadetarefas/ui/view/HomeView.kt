package com.newoverride.listadetarefas.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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

    ListaDeTarefasTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CardCustom(
                    textFieldText = taskHook.textFieldText,
                    saveTask = taskHook.saveTask,
                    isPressed = taskHook.isPressed,
                    taskList = taskHook.taskList
                )
            }
        }
    }
}