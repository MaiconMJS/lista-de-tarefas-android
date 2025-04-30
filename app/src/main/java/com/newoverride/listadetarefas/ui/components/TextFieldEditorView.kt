package com.newoverride.listadetarefas.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.newoverride.listadetarefas.ui.theme.Green

@Composable
fun TextFieldEditorView(textFieldTextEditor: MutableState<String>) {
    TextField(
        value = textFieldTextEditor.value,
        modifier = Modifier.fillMaxSize(),
        onValueChange = { textFieldTextEditor.value = it },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Green,
            selectionColors = TextSelectionColors(
                handleColor = MaterialTheme.colorScheme.tertiary,
                backgroundColor = MaterialTheme.colorScheme.tertiary
            ),
            cursorColor = MaterialTheme.colorScheme.tertiary
        ),
    )
}