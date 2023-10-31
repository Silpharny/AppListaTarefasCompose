package com.example.listadetarefacompose.components

import android.sax.TextElementListener
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listadetarefacompose.ui.theme.LightBlue
import com.example.listadetarefacompose.ui.theme.White

@Composable
fun Botao(
    onClick: () -> Unit,
    modifier: Modifier,
    texto: String
) {
    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = LightBlue,
            contentColor = White
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = texto, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}