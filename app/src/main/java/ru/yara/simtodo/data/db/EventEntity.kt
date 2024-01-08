package ru.yara.simtodo.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "date_start")
    val dateStart: LocalDateTime,
    @ColumnInfo(name = "date_finish")
    val dateFinish: LocalDateTime,
    val name: String,
    val description: String
)