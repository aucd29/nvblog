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

package com.example.nvblog.viewmodel

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2019-08-12. <p/>
 */

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.nvblog.R
import com.example.nvblog.ui.titlebar.TitlebarViewModel
import briggite.shield.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.slf4j.LoggerFactory

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-12 <p/>
 */
@RunWith(RobolectricTestRunner::class)
class TitlebarViewModelTest: BaseRoboViewModelTest<TitlebarViewModel>() {
    @Before
    @Throws(Exception::class)
    fun setup() {
        initMock()

        viewmodel = TitlebarViewModel(app)
    }

    @Test
    fun defaultValueTest() {
        viewmodel.apply {
            titleId.get()
            viewpagerPos.get()
        }
    }

    @Test
    fun naviItemSelectedListenerTest() {
        viewmodel.apply {
            var returnValue: Boolean = false

            returnValue = naviItemSelectedListener.get()!!.invoke(R.id.nav_new_article)
            titleId.get()
            viewpagerPos.get()

            returnValue = naviItemSelectedListener.get()!!.invoke(R.id.nav_recommended)
            titleId.get()
            viewpagerPos.get()

            returnValue = naviItemSelectedListener.get()!!.invoke(R.id.nav_write)
            titleId.get()
            viewpagerPos.get()

            returnValue = naviItemSelectedListener.get()!!.invoke(R.id.nav_mynotification)
            titleId.get()
            viewpagerPos.get()

            returnValue = naviItemSelectedListener.get()!!.invoke(R.id.nav_myblog)
            titleId.get()
            viewpagerPos.get()

            returnValue = naviItemSelectedListener.get()!!.invoke(R.id.nav_myblog)
        }
    }

    @Test
    fun cmdTest() {
        viewmodel.apply {
            command(TitlebarViewModel.CMD_MOVE_FIRST_TAB)

            viewpagerPos.get()
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // MOCK
    //
    ////////////////////////////////////////////////////////////////////////////////////

    companion object {
        private val mLog = LoggerFactory.getLogger(TitlebarViewModelTest::class.java)
    }

    //override fun initMock() {
    //    super.initMock()
    //
    //    initShadow()
    //    shadowApp?.grantPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION)
    //}
}