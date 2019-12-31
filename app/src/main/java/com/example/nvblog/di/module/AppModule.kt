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

import android.app.Application
import brigitte.di.dagger.module.ContextModule
import com.example.nvblog.MainApp
import dagger.Module
import dagger.Provides

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2019-08-30 <p/>
 */

// lib 단에서 context 를 이용하기 위해 ContextModule 은 하위로 내림
@Module(includes = [ContextModule::class])
class AppModule {
    @Provides
    fun provideApplication(app: MainApp): Application =
        app
}