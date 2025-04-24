package com.newoverride.listadetarefas.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.newoverride.listadetarefas.dimens.Dimens
import com.newoverride.listadetarefas.model.TaskModel

@Composable
fun TaskCardCustom(task: TaskModel) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = Dimens.cardElevation),
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(vertical = Dimens.paddingVerticalCardTask)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .padding(start = Dimens.paddingCheckBoxStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "${task.id} ->")
                Checkbox(
                    checked = task.marked.value,
                    onCheckedChange = { task.marked.value = it },
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.paddingTaskText)
            ) {
                Text(text = task.message, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}