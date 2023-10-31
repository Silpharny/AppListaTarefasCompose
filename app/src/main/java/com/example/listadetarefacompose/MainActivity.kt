package com.example.listadetarefacompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listadetarefacompose.ui.theme.ListaDeTarefaComposeTheme
import com.example.listadetarefacompose.view.ListaTarefa
import com.example.listadetarefacompose.view.SalvarTarefa

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaDeTarefaComposeTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "ListaTarefas") {
                    composable(
                        route = "ListaTarefas"
                    ) {
                        ListaTarefa(navController)
                    }
                    composable(
                        route = "SalvarTarefas"
                    ) {
                        SalvarTarefa(navController)
                    }
                }
            }
        }
    }
}
