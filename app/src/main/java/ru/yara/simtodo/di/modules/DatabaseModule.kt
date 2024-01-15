package ru.yara.simtodo.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.yara.simtodo.data.db.AppDatabase
import ru.yara.simtodo.data.db.EventDao
import ru.yara.simtodo.data.repository.DatabaseRepositoryImpl
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideUserDao(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "simtodo_db"
        ).build().eventDao()

    @Provides
    @Singleton
    fun provideRepository(userDao: EventDao) = DatabaseRepositoryImpl(userDao)
}