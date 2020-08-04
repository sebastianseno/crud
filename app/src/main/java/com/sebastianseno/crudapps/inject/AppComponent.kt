package com.sebastianseno.crudapps.inject

import com.sebastianseno.crudapps.CrudApp
import com.sebastianseno.crudapps.db.DatabaseModule
import com.sebastianseno.crudapps.network.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        ContextModule::class,
        NetworkModule::class,
        ActivityBindingModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent : AndroidInjector<CrudApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<CrudApp>() {
        abstract override fun build(): AppComponent
    }
}
