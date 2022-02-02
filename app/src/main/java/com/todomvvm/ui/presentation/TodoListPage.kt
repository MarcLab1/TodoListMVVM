package com.todomvvm.ui.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.todomvvm.util.Routes

@Composable
fun TodoListPage(vm: MyViewModel, nav: NavController) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(nav = nav) },
        content = { TodoListContent(vm = vm, nav = nav) },
       // floatingActionButton = { FloatingActionButtonAdd(vm, nav) },
    )
}

@Composable
fun TodoListContent(vm: MyViewModel, nav: NavController) {

    if(vm.todos!=null) {
        val todos = vm.todos!!.collectAsState(initial = listOf())

        LazyColumn(modifier = Modifier.padding(bottom = 80.dp))
        {
            items(todos.value)
            {
                TodoCard(vm = vm, nav = nav, todo = it)
            }
        }
    }
}


@Composable
fun TopBar( nav: NavController) {

    TopAppBar(
        title = { Text(text = "To Do List") },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {

                IconButton(onClick = { nav.navigate(Routes.ADD) }) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add")
                }
        }
    )
}


@Composable
fun FloatingActionButtonAdd(vm: MyViewModel, nav: NavController) {

    FloatingActionButton(
        onClick = {
            nav.navigate("add")
        },
        content = { Icon(Icons.Filled.Add, contentDescription = "Add") })
}