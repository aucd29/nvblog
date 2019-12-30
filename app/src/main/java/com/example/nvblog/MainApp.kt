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

package com.example.nvblog

import android.content.Context
import androidx.multidex.MultiDex
import com.example.nvblog.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.github.inflationx.viewpump.ViewPump
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class MainApp : DaggerApplication() {
    @Inject lateinit var viewPump: ViewPump

    override fun onCreate() {
        super.onCreate()

        mLog.error("START APP ${BuildConfig.APPLICATION_ID} (${BuildConfig.VERSION_NAME})")

        ViewPump.init(viewPump)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // DAGGER
    //
    ////////////////////////////////////////////////////////////////////////////////////

    private val mComponent: AndroidInjector<MainApp> by lazy(LazyThreadSafetyMode.NONE) {
        DaggerAppComponent.factory().create(this)
    }

    override fun applicationInjector() =
        mComponent

    companion object {
        private val mLog = LoggerFactory.getLogger(MainApp::class.java)
    }
}