package com.example.listadetarefacompose.view
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.listadetarefacompose.R
import com.example.listadetarefacompose.components.Botao
import com.example.listadetarefacompose.components.CaixaDeTexto
import com.example.listadetarefacompose.constants.Constants
import com.example.listadetarefacompose.repository.TarefaRepository
import com.example.listadetarefacompose.ui.theme.BlackFontColor
import com.example.listadetarefacompose.ui.theme.Purple40
import com.example.listadetarefacompose.ui.theme.RadioButtonGreenDisable
import com.example.listadetarefacompose.ui.theme.RadioButtonGreenEnable
import com.example.listadetarefacompose.ui.theme.RadioButtonRedDisable
import com.example.listadetarefacompose.ui.theme.RadioButtonRedEnable
import com.example.listadetarefacompose.ui.theme.RadioButtonYellowDisable
import com.example.listadetarefacompose.ui.theme.RadioButtonYellowEnable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalvarTarefa(
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val tarefasRepository = TarefaRepository()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Salvar Tarefa", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)},
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple40),
                navigationIcon = {
                    Button(
                        onClick = { navController.navigate("ListaTarefas") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)

                ) {
                    Image(imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back), contentDescription = "Voltar página")
                }}
            )
        }
    ) { innerPadding ->

        var tituloTarefa by remember {
            mutableStateOf("")
        }
        var descricaoTarefa by remember {
            mutableStateOf("")
        }

        var semPrioridadeTarefa by remember {
            mutableStateOf(false)
        }

        var prioridadeBaixaTarefa by remember {
            mutableStateOf(false)
        }

        var prioridadeMediaTarefa by remember {
            mutableStateOf(false)
        }

        var prioridadeAltaTarefa by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BlackFontColor)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            CaixaDeTexto(
                value = tituloTarefa,
                onValueChange = {
                    tituloTarefa = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp, 20.dp, 0.dp),
                label = "Título da Tarefa",
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )
            CaixaDeTexto(
                value = descricaoTarefa,
                onValueChange = {
                    descricaoTarefa = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(20.dp, 5.dp, 20.dp, 0.dp),
                label = "Descrição da Tarefa",
                maxLines = 5,
                keyboardType = KeyboardType.Text
            )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
        ) {
            Text(text = "Nível de prioridade: ")
            RadioButton(
                selected = prioridadeBaixaTarefa,
                onClick = {
                    prioridadeBaixaTarefa = !prioridadeBaixaTarefa
                },
                colors = RadioButtonDefaults.colors(
                    unselectedColor = RadioButtonGreenDisable,
                    selectedColor = RadioButtonGreenEnable
                )
            )
            RadioButton(
                selected = prioridadeMediaTarefa,
                onClick = {
                    prioridadeMediaTarefa = !prioridadeMediaTarefa
                },
                colors = RadioButtonDefaults.colors(
                    unselectedColor = RadioButtonYellowDisable,
                    selectedColor = RadioButtonYellowEnable
                )
            )
            RadioButton(
                selected = prioridadeAltaTarefa,
                onClick = {
                    prioridadeAltaTarefa = !prioridadeAltaTarefa
                },
                colors = RadioButtonDefaults.colors(
                    unselectedColor = RadioButtonRedDisable,
                    selectedColor = RadioButtonRedEnable
                )
            )
        }
        Botao(
            onClick = {
                var mensagem = true

                scope.launch(Dispatchers.IO){
                    if(tituloTarefa.isEmpty()){
                        mensagem = false
                    } else if(tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeBaixaTarefa) {
                        tarefasRepository.salvarTarefa(tituloTarefa, descricaoTarefa, Constants.PRIORIDADEBAIXA)
                        mensagem = true

                    } else if(tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeMediaTarefa) {
                        tarefasRepository.salvarTarefa(tituloTarefa, descricaoTarefa, Constants.PRIORIDADEMEDIA)
                        mensagem = true

                    } else if(tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeAltaTarefa) {
                        tarefasRepository.salvarTarefa(tituloTarefa, descricaoTarefa, Constants.PRIORIDADEALTA)
                        mensagem = true
                    } else if(tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && semPrioridadeTarefa) {
                        tarefasRepository.salvarTarefa(tituloTarefa, descricaoTarefa, Constants.SEMPRIORIDADE)
                        mensagem = true
                    } else if(tituloTarefa.isNotEmpty() && prioridadeBaixaTarefa) {
                        tarefasRepository.salvarTarefa(tituloTarefa, descricaoTarefa, Constants.PRIORIDADEBAIXA)
                        mensagem = true
                    } else if(tituloTarefa.isNotEmpty() && prioridadeMediaTarefa) {
                        tarefasRepository.salvarTarefa(tituloTarefa, descricaoTarefa, Constants.PRIORIDADEMEDIA)
                        mensagem = true
                    } else if(tituloTarefa.isNotEmpty() && prioridadeAltaTarefa) {
                        tarefasRepository.salvarTarefa(tituloTarefa, descricaoTarefa, Constants.PRIORIDADEALTA)
                        mensagem = true
                    } else {
                        tarefasRepository.salvarTarefa(tituloTarefa, descricaoTarefa, Constants.SEMPRIORIDADE)
                        mensagem = true
                    }
                }

                scope.launch(Dispatchers.Main){
                    if(mensagem){
                        Toast.makeText(context, "Sucesso ao salvar a tarefa!", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    } else{
                        Toast.makeText(context, "Título da tarefa é obrigatório!", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(20.dp, 5.dp, 20.dp, 0.dp),
            texto = "Salvar" )
        }
    }
}
