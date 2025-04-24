package com.newoverride.listadetarefas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.newoverride.listadetarefas.R
import com.newoverride.listadetarefas.dimens.Dimens

@Composable
fun TitleCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(width = 0.dp, height = Dimens.boxTitleCardHeight)
            .background(color = MaterialTheme.colorScheme.tertiary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.labelSmall,
        )
    }
}