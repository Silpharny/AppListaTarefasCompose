package com.example.listadetarefacompose.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.listadetarefacompose.R
import com.example.listadetarefacompose.itemlista.TarefaItem
import com.example.listadetarefacompose.model.Tarefa
import com.example.listadetarefacompose.repository.TarefaRepository
import com.example.listadetarefacompose.ui.theme.Purple40
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.compose

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTarefa(
   navController: NavController
) {

    val tarefasRepository = TarefaRepository()
    val context = LocalContext.current

    Scaffold(
        topBar = {
           TopAppBar(
               title = { Text(text = "Lista de Tarefas", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White) },
               colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple40),

           )
        },

        containerColor = Color.Black,
        floatingActionButton = {
            FloatingActionButton(
             shape = CircleShape,
                onClick = {
                navController.navigate("SalvarTarefas")
            }) {
                Image(imageVector = ImageVector.vectorResource(R.drawable.ic_add), contentDescription = "Buttom ADD" )
            }
        }
    ) {
        Column(
        modifier = Modifier.padding(it)
        ) {

            val listaTarefas = tarefasRepository.recuperarTarefas().collectAsState(mutableListOf()).value

            LazyColumn(
            ) {
                itemsIndexed(listaTarefas) {position, _ ->
                    TarefaItem(position, listaTarefas, context, navController)
                }
            }
        }
    }
}
