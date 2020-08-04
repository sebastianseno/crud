package com.sebastianseno.crudapps.ui.goods

import androidx.lifecycle.ViewModel
import com.sebastianseno.crudapps.inject.FragmentScope
import com.sebastianseno.crudapps.inject.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class GoodsModules {

    @Binds
    @IntoMap
    @ViewModelKey(GoodsViewModel::class)
    abstract fun bindGoodsViewModel(viewModel: GoodsViewModel): ViewModel
}

@Module
abstract class GoodsFragmentModule {

    @ContributesAndroidInjector(modules = [GoodsModules::class])
    @FragmentScope
    abstract fun goodsListFragment(): GoodsListFragment

    @ContributesAndroidInjector(modules = [GoodsModules::class])
    @FragmentScope
    abstract fun goodsDetailFragment(): GoodsDetailFragment

    @ContributesAndroidInjector(modules = [GoodsModules::class])
    @FragmentScope
    abstract fun addGoodsFragment(): AddGoodsFragment

}