package com.todomvvm.ui.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todomvvm.model.Todo
import com.todomvvm.repo.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    var repo: TodoRepository
) : ViewModel() {

    var todos: Flow<List<Todo>>? = null

    init {
        todos = repo.getTodos()
    }
    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repo.delete(todo)
        }
        //todos returns a flow so it is automagically updated
    }

    fun addTodo(title: String, description: String, isImportant: Boolean) {
        viewModelScope.launch {
            repo.insert(
                Todo(
                    title = title,
                    description = description,
                    isImportant = isImportant
                )
            )
        }
    }

    fun updateTodo(id: Int, title: String, description: String, isImportant: Boolean) {
        viewModelScope.launch {
            val todo = repo.getTodoById(id)
            if (todo != null) {
                todo.title = title
                todo.description = description
                todo.isImportant = isImportant
                repo.update(todo)
            }
        }
    }
}

