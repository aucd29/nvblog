package com.example.nvblog.di

import androidx.fragment.app.FragmentManager
import com.example.nvblog.MainActivity
import com.example.nvblog.di.module.FragmentModule
import com.example.nvblog.ui.ViewController
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2018. 12. 6. <p/>
 */

@Module(includes = [])
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [
        FragmentModule::class,
        MainActivityModule::class
    ])
    abstract fun contributeMainActivity(): MainActivity

//    @Singleton
//    @Binds
//    abstract fun bindViewController(controller: ViewController): Any
}

// https://stackoverflow.com/questions/48533899/how-to-inject-members-in-baseactivity-using-dagger-android
@Module
class MainActivityModule {
    @Provides
    fun provideFragmentManager(activity: MainActivity): FragmentManager =
        activity.supportFragmentManager
}
