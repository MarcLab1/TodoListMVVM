package com.todomvvm.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.DateFormat

@Entity(tableName = "todo")
@Parcelize
data class Todo(
    var title: String,
    var description: String = "",
    var isImportant: Boolean = false,
    var dateCreatedLong: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable {
    val dateCreatedString: String
        get() = DateFormat.getDateTimeInstance().format(dateCreatedLong)
}
