package com.todomvvm.ui.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.todomvvm.model.Todo
import com.todomvvm.util.Routes

@Composable
fun AddUpdatePage(vm: MyViewModel, nav: NavController, todo: Todo?) {

    val scaffoldState = rememberScaffoldState()

    //Elvis operator is perfect here
    var title = remember { mutableStateOf(todo?.title ?: "") }
    var description = remember { mutableStateOf(todo?.description ?: "") }
    var isImportant = remember { mutableStateOf(todo?.isImportant ?: false) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBarAddUpdate(vm, nav, todo) },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                TextField(
                    value = title.value,
                    onValueChange = {
                        title.value = it
                    },
                    placeholder = {
                        Text(text = "Title")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    maxLines = 1,
                    singleLine = true
                )

                TextField(
                    value = description.value,
                    onValueChange = {
                        description.value = it
                    },
                    placeholder = {
                        Text(text = "Description")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    singleLine = false,
                    maxLines = 5,
                )

                Row()
                {
                    Checkbox(
                        checked = isImportant.value,
                        onCheckedChange = { isImportant.value = it },
                    modifier = Modifier.padding(end = 3.dp))
                    Text("Important")
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (todo == null)
                        vm.addTodo(title.value, description.value, isImportant.value)
                    else
                        vm.updateTodo(todo.id, title.value, description.value, isImportant.value)

                    nav.navigate(Routes.HOME)
                },
                content = { Icon(Icons.Filled.Check, contentDescription = "Add") })
        })
}

@Composable
fun TopBarAddUpdate(vm: MyViewModel, nav: NavController, todo: Todo?) {
    var title = ""
    if (todo == null)
        title = "Add Todo"
    else
        title = "Update Todo"
    TopAppBar(
        title = { Text(title) },
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = {
            IconButton(onClick = { nav.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        actions = {
            if (todo != null) {
                IconButton(onClick = {
                    vm.deleteTodo(todo)
                    nav.navigate(Routes.HOME)
                }) {
                    Icon(Icons.Filled.Delete, "delete")
                }
            }
        }
    )
}


