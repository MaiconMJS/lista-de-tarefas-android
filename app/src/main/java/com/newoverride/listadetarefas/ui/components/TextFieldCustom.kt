package com.newoverride.listadetarefas.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.newoverride.listadetarefas.R
import com.newoverride.listadetarefas.dimens.Dimens
import com.newoverride.listadetarefas.ui.theme.Green

@Composable
fun TextFieldCustom(textFieldText: MutableState<String>) {
    OutlinedTextField(
        value = textFieldText.value,
        onValueChange = { textFieldText.value = it },
        maxLines = 3,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            focusedLabelColor = Green,
            cursorColor = MaterialTheme.colorScheme.tertiary
        ),
        modifier = Modifier.padding(bottom = Dimens.textFieldBottomPadding),
        label = { Text(text = stringResource(R.string.tarefa)) })
}
