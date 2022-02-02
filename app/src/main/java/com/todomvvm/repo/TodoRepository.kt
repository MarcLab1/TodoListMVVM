package com.todomvvm.repo

import com.todomvvm.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getTodos() : Flow<List<Todo>>

    suspend fun insert(todo: Todo) : Unit

    suspend fun delete(todo: Todo) : Unit

    suspend fun update(todo: Todo) : Unit

    suspend fun getTodoById(id : Int) : Todo?

    fun searchTodos(query: String) : Flow<List<Todo>>
}