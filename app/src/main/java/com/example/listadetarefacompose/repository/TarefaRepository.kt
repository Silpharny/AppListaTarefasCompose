package com.example.listadetarefacompose.repository

import com.example.listadetarefacompose.dataSource.DataSource
import com.example.listadetarefacompose.model.Tarefa
import kotlinx.coroutines.flow.Flow

class TarefaRepository {

    private val dataSource = DataSource()

    fun salvarTarefa(tarefa: String, descricao: String, prioridade: Int) {

        dataSource.SalvarTarefa(tarefa, descricao, prioridade)
    }

    fun recuperarTarefas(): Flow<MutableList<Tarefa>>{
        return dataSource.recuperarTarefa()
    }

    fun deletarTarefa(tarefa: String) {
        dataSource.deletarTarefa(tarefa)
    }
}