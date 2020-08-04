package com.sebastianseno.crudapps

import com.sebastianseno.crudapps.inject.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CrudApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
        }
}