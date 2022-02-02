package com.todomvvm.model

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson

class AssetParamType : NavType<Todo>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Todo? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Todo {
        return Gson().fromJson(value, Todo::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Todo) {
        bundle.putParcelable(key, value)
    }
}