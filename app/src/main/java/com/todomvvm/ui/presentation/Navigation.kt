package com.todomvvm.ui.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.todomvvm.model.AssetParamType
import com.todomvvm.model.Todo
import com.todomvvm.util.Routes

@Composable
fun Naviagation() {
    val nav = rememberNavController()
    val vm: MyViewModel = hiltViewModel()

    NavHost(navController = nav, startDestination = "home")
    {
        composable(Routes.HOME)
        {
            TodoListPage(vm, nav)
        }
        composable(Routes.ADD)
        {
            AddUpdatePage(vm, nav, null)
        }

        //both view and update
        composable("${Routes.UPDATE}/{todo}",
            arguments = listOf(navArgument("todo") {
                type = AssetParamType()
            }))
        {
            val todo = it.arguments?.getParcelable<Todo>("todo")
            if (todo != null) {
                AddUpdatePage(vm, nav, todo)
            }
        }
    }
}
