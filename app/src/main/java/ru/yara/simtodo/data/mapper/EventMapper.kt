package ru.yara.simtodo.data.mapper

import ru.yara.simtodo.data.db.EventEntity
import ru.yara.simtodo.data.model.Event
import java.time.LocalDateTime
import java.time.ZoneOffset

object EventMapper {

    private val zoneOffset = ZoneOffset.systemDefault().rules.getOffset(LocalDateTime.now())

    fun Event.toEventEntity() = EventEntity(
        id = this.id,
        dateStart = LocalDateTime.ofEpochSecond(this.dateStart.toLong(), 0, zoneOffset),
        dateFinish = LocalDateTime.ofEpochSecond(this.dateFinish.toLong(), 0, zoneOffset),
        name = this.name,
        description = this.description
    )
}