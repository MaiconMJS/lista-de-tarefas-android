package com.newoverride.listadetarefas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.newoverride.listadetarefas.R
import com.newoverride.listadetarefas.dimens.Dimens

@Composable
fun TitleCardWithInfo(
    allTask: MutableIntState,
    visibility: MutableState<Boolean>,
    hideX: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(width = 0.dp, height = Dimens.boxTitleCardHeight)
            .background(color = MaterialTheme.colorScheme.tertiary),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.1f),
            horizontalArrangement = Arrangement.Center

        ) {
            Text(text = "${allTask.intValue}", style = MaterialTheme.typography.labelSmall)
        }
        Row(
            modifier = Modifier.fillMaxWidth(0.7f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.labelSmall,
            )
            if (visibility.value) Text(
                text = "X",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(onTap = { hideX() })
                })
        }
    }
}