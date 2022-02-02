package com.todomvvm.di

import android.app.Application
import androidx.room.Room
import com.todomvvm.model.TodoDatabase
import com.todomvvm.repo.TodoRepository
import com.todomvvm.repo.TodoRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideString(): String = "just for testing"

    @Singleton
    @Provides
    fun provideRepo(todoDatabase : TodoDatabase): TodoRepository {
        return TodoRepository_Impl(todoDatabase.dao)
    }

    @Singleton
    @Provides
    fun provideTodoDatabase(application: Application): TodoDatabase {
        return Room.databaseBuilder(application, TodoDatabase::class.java, "todo_database").build()
    }

}