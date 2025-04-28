package com.newoverride.listadetarefas.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TextCustomTaskCard(
    text: String,
    maxLine: Int = 1,
    overFlow: TextOverflow = TextOverflow.Ellipsis,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    modifier: Modifier
) {
    Text(text = text, color = color, overflow = overFlow, maxLines = maxLine, modifier = modifier)
}