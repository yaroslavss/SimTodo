package ru.yara.simtodo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Event(
    val id: Int,
    val dateStart: LocalDateTime,
    val dateFinish: LocalDateTime,
    val name: String,
    val description: String
) : Parcelable