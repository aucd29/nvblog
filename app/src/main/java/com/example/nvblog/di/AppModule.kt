package com.example.nvblog.di

import brigitte.di.dagger.module.AssetModule
import brigitte.di.dagger.module.RxModule
import brigitte.di.dagger.module.ViewModelFactoryModule
import com.example.nvblog.di.module.*
import dagger.Module

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

@Module(includes = [ ViewModelFactoryModule::class
    , ViewModelModule::class
    , NetworkModule::class
    , DbModule::class
    , AssetModule::class
    , RxModule::class
    , CalligraphyModule::class
    , ChipModule::class
])
class AppModule {
}
