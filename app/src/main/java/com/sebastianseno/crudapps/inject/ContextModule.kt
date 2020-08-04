package com.sebastianseno.crudapps.inject

import android.content.Context
import com.sebastianseno.crudapps.CrudApp
import dagger.Binds
import dagger.Module

@Module
abstract class ContextModule {
    @Binds
    abstract fun bindContext(qasirApp: CrudApp): Context
}