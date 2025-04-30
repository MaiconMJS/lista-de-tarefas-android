package com.newoverride.listadetarefas.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.newoverride.listadetarefas.R

@Composable
fun WhatsAppButton(whatsAppShare: () -> Unit, whatsAppPressed: MutableState<Boolean>) {
    IconButton(onClick = whatsAppShare, modifier = Modifier.size(size = 60.dp)) {
        val whatsScale by animateFloatAsState(
            targetValue = if (whatsAppPressed.value) 0.8f else 1.0f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            ),
            label = "spring-press-scale"
        )
        Image(
            modifier = Modifier
                .padding(5.dp)
                .alpha(0.70f)
                .scale(whatsScale),
            painter = painterResource(R.drawable.logo),
            contentDescription = stringResource(R.string.botaoWhatsApp)
        )
    }
}