package ru.yara.simtodo.data.mapper

import ru.yara.simtodo.data.db.EventEntity
import ru.yara.simtodo.domain.model.Event

object EventEntityMapper {

    fun EventEntity.toDomainEvent() = Event(
        id = this.id,
        dateStart = this.dateStart,
        dateFinish = this.dateFinish,
        name = this.name,
        description = this.description
    )
}