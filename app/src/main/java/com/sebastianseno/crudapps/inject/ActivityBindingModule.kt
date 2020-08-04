package com.sebastianseno.crudapps.inject

import com.sebastianseno.crudapps.ui.goods.GoodsFragmentModule
import com.sebastianseno.crudapps.ui.main.MainActivity
import com.sebastianseno.crudapps.ui.main.MainModules
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(
        modules = [
            GoodsFragmentModule::class,
            MainModules::class
        ]
    )
    @ActivityScope
    abstract fun mainActivity(): MainActivity
}