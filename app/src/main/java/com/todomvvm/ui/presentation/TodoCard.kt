package com.todomvvm.ui.presentation

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.todomvvm.model.Todo
import com.todomvvm.R
import com.todomvvm.util.Routes


@Composable
fun TodoCard(todo: Todo, vm: MyViewModel, nav: NavController) {
    var isImportant = remember { mutableStateOf(todo.isImportant) }
    var painterResource : Painter
    var colorFilter : ColorFilter

    Card(
        elevation = 5.dp, modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable {
                val jsonTodo = Uri.encode(Gson().toJson(todo))
                nav.navigate("${Routes.UPDATE}/$jsonTodo")
                //nav.navigate("update/${todo.id}")
            }
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp, top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {

            if (isImportant.value) {
                painterResource = painterResource(id = R.drawable.ic_baseline_star_24)
                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
            }
            else {
                painterResource = painterResource(id = R.drawable.ic_baseline_star_outline_24)
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSecondary)
            }

            Image(
                painter =  painterResource,
                contentDescription = "delete",
                colorFilter = colorFilter)

            Column(modifier = Modifier.fillMaxWidth(.85f)) {

                Text(
                    "${todo.title}",
                    maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(bottom = 5.dp)
                )
                Text("${todo.dateCreatedString}", style = MaterialTheme.typography.h3)
            }

            Row()
            {
                Image(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "delete",
                    modifier = Modifier.clickable {
                        vm.deleteTodo(todo)
                    })
            }
        }

    }
}
