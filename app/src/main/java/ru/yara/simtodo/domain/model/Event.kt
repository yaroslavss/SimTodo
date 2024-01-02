package ru.yara.simtodo.domain.model

import java.time.LocalDateTime

data class Event(
    val id: Int,
    val dateStart: LocalDateTime,
    val dateFinish: LocalDateTime,
    val name: String,
    val description: String
)