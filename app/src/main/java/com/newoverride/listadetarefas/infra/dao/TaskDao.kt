package com.newoverride.listadetarefas.infra.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.newoverride.listadetarefas.infra.entitys.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTasks(): List<Task>

    @Insert
    suspend fun insertTask(task: Task): Long

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)
}