package com.newoverride.listadetarefas.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TaskModel(
    val id: Int,
    val marked: MutableState<Boolean> = mutableStateOf(false),
    val message: String
)