package com.todomvvm.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * FROM todo")
    fun getTodos() : Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE id=:id")
    suspend fun getTodoById(id : Int) : Todo?

    @Query("SELECT * FROM todo WHERE title LIKE '%' || :search || '%'")
    fun searchTodos(search: String?): Flow<List<Todo>>
}