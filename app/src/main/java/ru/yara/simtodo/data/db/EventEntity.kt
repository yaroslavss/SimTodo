package ru.yara.simtodo.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "date_start")
    val dateStart: String,
    @ColumnInfo(name = "date_finish")
    val dateFinish: String,
    val name: String,
    val description: String
)