package ru.yara.simtodo.ui.addevent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.yara.simtodo.App
import ru.yara.simtodo.data.db.EventEntity
import ru.yara.simtodo.data.repository.DatabaseRepositoryImpl
import javax.inject.Inject

class AddEventViewModel : ViewModel() {

    @Inject
    lateinit var databaseRepository: DatabaseRepositoryImpl

    init {
        App.instance.dagger.inject(this)
    }

    fun addEventIntoDB(eventEntity: EventEntity) = viewModelScope.launch(Dispatchers.IO) {
        databaseRepository.insertEvent(eventEntity)
    }
}