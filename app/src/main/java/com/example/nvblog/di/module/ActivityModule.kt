/*
 * Copyright (C) Hanwha S&C Ltd., 2019. All rights reserved.
 *
 * This software is covered by the license agreement between
 * the end user and Hanwha S&C Ltd., and may be
 * used and copied only in accordance with the terms of the
 * said agreement.
 *
 * Hanwha S&C Ltd., assumes no responsibility or
 * liability for any errors or inaccuracies in this software,
 * or any consequential, incidental or indirect damage arising
 * out of the use of the software.
 */

package com.example.nvblog.di.module

import androidx.fragment.app.FragmentActivity
import brigitte.di.dagger.scope.ActivityScope
import com.example.nvblog.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Named

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2018. 12. 6. <p/>
 */

@Module(includes = [])
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [
        MainActivityModule::class
    ])
    abstract fun contributeMainActivity(): MainActivity
}

// https://stackoverflow.com/questions/48533899/how-to-inject-members-in-baseactivity-using-dagger-android
@Module(includes = [
    FragmentModule::class
])
abstract class MainActivityModule {
    // 라이브러리 쪽에서 Activity 를 이용하기 위해서 반드시 설정해야 함
    @Binds
    abstract fun bindFragmentActivity(activity: MainActivity): FragmentActivity

    @Module
    companion object {
        @Provides
        @JvmStatic
        @Named("activityFragmentManager")
        fun provideFragmentManager(activity: MainActivity) =
            activity.supportFragmentManager
    }
}