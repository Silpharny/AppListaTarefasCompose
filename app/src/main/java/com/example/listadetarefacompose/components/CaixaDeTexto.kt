package com.example.listadetarefacompose.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.listadetarefacompose.ui.theme.LightBlue
import androidx.compose.ui.text.input.KeyboardType
import com.example.listadetarefacompose.ui.theme.ShapeEditText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaixaDeTexto(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String,
    maxLines: Int,
    keyboardType: KeyboardType
){

    OutlinedTextField(
        value = value,
        onValueChange,
        modifier,
        label = { Text(text = label) },
        maxLines = maxLines,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = LightBlue,
            focusedLabelColor = LightBlue,
            cursorColor = LightBlue,

        ),
        shape = ShapeEditText.small,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}