package ru.yara.simtodo.domain.model

import java.time.LocalDateTime

data class Hour(
    val startHour: LocalDateTime,
    val endHour: LocalDateTime
)
