package com.newoverride.listadetarefas.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import com.newoverride.listadetarefas.R
import com.newoverride.listadetarefas.dimens.Dimens

@Composable
fun ButtonCustom(
    onClick: () -> Unit,
    onDelete: () -> Unit,
    isPressed: MutableState<Boolean>,
    isDeleteMode: MutableState<Boolean>,
) {
    val scale by animateFloatAsState(
        targetValue = if (isPressed.value) 0.8f else 1.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "spring-press-scale"
    )

    val modifier = Modifier
        .scale(scale)
        .padding(bottom = Dimens.buttonBottomPadding)

    Button(
        onClick = if (isDeleteMode.value) onDelete else onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isDeleteMode.value) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.tertiary
        ),
        modifier = modifier
    ) {
        Text(
            text = stringResource(
                if (isDeleteMode.value) R.string.deletar else R.string.salvar
            ),
            style = MaterialTheme.typography.labelSmall
        )
    }
}