package com.example.listadetarefacompose.itemlista

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.listadetarefacompose.R
import com.example.listadetarefacompose.model.Tarefa
import com.example.listadetarefacompose.repository.TarefaRepository
import com.example.listadetarefacompose.ui.theme.BlackFontColor
import com.example.listadetarefacompose.ui.theme.RadioButtonGreenEnable
import com.example.listadetarefacompose.ui.theme.RadioButtonRedEnable
import com.example.listadetarefacompose.ui.theme.RadioButtonYellowEnable
import com.example.listadetarefacompose.ui.theme.ShapeCardPrioridade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TarefaItem(
    position: Int,
    listaTarefas: MutableList<Tarefa>,
    context: Context,
    navController: NavController
) {

    val tituloTarefa = listaTarefas[position].tarefa
    val descricaoTarefa = listaTarefas[position].descricao
    val prioridadeTarefa = listaTarefas[position].prioridade

    val scope = rememberCoroutineScope()
    val tarefaRepository = TarefaRepository()

    fun dialogDeletar(){

        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Deletar Tarefa")
            .setMessage("Deseja excluir a tarefa?")
            .setPositiveButton("Sim"){_, _->
                tarefaRepository.deletarTarefa(tituloTarefa.toString())

                scope.launch(Dispatchers.Main) {
                    listaTarefas.removeAt(position)
                    navController.navigate("listaTarefas")
                    Toast.makeText(context, "Sucesso ao deletar Tarefa!", Toast.LENGTH_SHORT)

                }
            }
            .setNegativeButton("Não"){_, _->

            }
            .show()
    }

    val nivelPrioridade: String = when(prioridadeTarefa) {
        0 -> {
            "Sem Prioridade"
        }
        1 -> {
            "Prioridade Baixa"
        }
        2 -> {
            "Prioridade Média"
        }
        else -> {
            "Prioridade Alta"
        }
    }

    val colorBtn = when(prioridadeTarefa) {
        0 -> {
            Color.Black
        }
        1 -> {
            RadioButtonGreenEnable
        }
        2 -> {
            RadioButtonYellowEnable
        }
        else -> {
            RadioButtonRedEnable
        }
    }


    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()

    ){

        ConstraintLayout(
            modifier = Modifier
                .padding(30.dp)
        ) {

            val (txtTitulo, txtDescricao, cardPrioridade, txtPrioridade, btnDeletar) = createRefs()

            Text(
                text = tituloTarefa.toString(),
                modifier = Modifier.constrainAs(txtTitulo) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = descricaoTarefa.toString(),
                modifier = Modifier.constrainAs(txtDescricao) {
                    top.linkTo(txtTitulo.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = nivelPrioridade,
                modifier = Modifier.constrainAs(txtPrioridade) {
                    top.linkTo(txtDescricao.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            )

            Card(
                colors = CardDefaults.cardColors(colorBtn ),
                modifier = Modifier
                    .size(20.dp)
                    .constrainAs(cardPrioridade) {
                        top.linkTo(txtDescricao.bottom, margin = 10.dp)
                        start.linkTo(txtPrioridade.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    },
                shape = ShapeCardPrioridade.large
            ) {
            }
            IconButton(
                modifier = Modifier
                    .size(25.dp)
                    .constrainAs(btnDeletar) {
                        top.linkTo(txtDescricao.bottom, margin = 10.dp)
                        start.linkTo(cardPrioridade.end, margin = 30.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    },
                onClick = {
                    dialogDeletar()

                }) {
                Image(imageVector = ImageVector.vectorResource(R.drawable.ic_delete), contentDescription = "Delete")
            }
        }
    }
}
