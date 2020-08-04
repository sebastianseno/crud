package com.sebastianseno.crudapps.ui.main

import androidx.lifecycle.ViewModel
import com.sebastianseno.crudapps.inject.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MainModules {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}