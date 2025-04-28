package com.newoverride.listadetarefas.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.newoverride.listadetarefas.R
import com.newoverride.listadetarefas.dimens.Dimens
import com.newoverride.listadetarefas.ui.theme.Green

@Composable
fun TextFieldCustom(
    textFieldText: MutableState<String>,
    addTaskDone: () -> Unit
) {
    OutlinedTextField(
        value = textFieldText.value,
        onValueChange = { textFieldText.value = it },
        maxLines = 3,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            focusedLabelColor = Green,
            selectionColors = TextSelectionColors(
                handleColor = MaterialTheme.colorScheme.tertiary,
                backgroundColor = MaterialTheme.colorScheme.tertiary
            ),
            cursorColor = MaterialTheme.colorScheme.tertiary
        ),
        modifier = Modifier.padding(bottom = Dimens.textFieldBottomPadding),
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { addTaskDone() }),
        label = { Text(text = stringResource(R.string.tarefa)) })
}
