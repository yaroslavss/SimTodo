package ru.yara.simtodo.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.yara.simtodo.di.modules.DatabaseModule
import ru.yara.simtodo.ui.home.HomeViewModel
import javax.inject.Singleton

@Singleton
@Component(
    // all modules
    modules = [
        DatabaseModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(userViewModel: HomeViewModel)
}