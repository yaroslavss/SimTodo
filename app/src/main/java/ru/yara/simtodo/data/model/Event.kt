package ru.yara.simtodo.data.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("id")
    val id: Int,
    @SerializedName("date_start")
    val dateStart: String,
    @SerializedName("date_finish")
    val dateFinish: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String
)