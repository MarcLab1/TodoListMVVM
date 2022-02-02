package com.todomvvm.repo

import com.todomvvm.model.Todo
import com.todomvvm.model.TodoDao
import kotlinx.coroutines.flow.Flow

class TodoRepository_Impl(val todoDao: TodoDao) : TodoRepository {

    override fun getTodos() : Flow<List<Todo>> = todoDao.getTodos()

    override suspend fun insert(todo: Todo)  = todoDao.insert(todo)

    override suspend fun delete(todo: Todo) = todoDao.delete(todo)

    override suspend fun update(todo: Todo) = todoDao.update(todo)

    override suspend fun getTodoById(id: Int): Todo? = todoDao.getTodoById(id)

    override fun searchTodos(query: String): Flow<List<Todo>>  = todoDao.searchTodos(query)

}
