package com.example.listadetarefacompose.dataSource

import com.example.listadetarefacompose.model.Tarefa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataSource {

    private val db = FirebaseFirestore.getInstance()

    private val _todasTarefa = MutableStateFlow<MutableList<Tarefa>>(mutableListOf())
    private val todasTarefas:  StateFlow<MutableList<Tarefa>> = _todasTarefa

    fun SalvarTarefa(tarefa: String,descricao: String,prioridade: Int) {

        val tarefaMap = hashMapOf(
            "tarefa" to tarefa,
            "descricao" to descricao,
            "prioridade" to prioridade
        )
        db.collection("tarefas").document(tarefa).set(tarefaMap).addOnCompleteListener {

        }.addOnFailureListener {

        }
    }

    fun recuperarTarefa(): Flow<MutableList<Tarefa>>{

        val listaTarefas: MutableList<Tarefa> = mutableListOf()

        db.collection("tarefas").get().addOnCompleteListener{querySnapshot ->
            if(querySnapshot.isSuccessful) {
                for(documento in querySnapshot.result) {
                    val tarefa = documento.toObject(Tarefa::class.java)
                    listaTarefas.add(tarefa)
                    _todasTarefa.value = listaTarefas
                }
            }
        }
        return todasTarefas
    }

    fun deletarTarefa(tarefa: String){
        db.collection("tarefas").document(tarefa).delete().addOnCanceledListener {

        }.addOnFailureListener {

        }

    }
}