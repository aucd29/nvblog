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
import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.text.toHtml
import androidx.test.core.app.ApplicationProvider
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
import briggite.shield.*
import brigitte.app
import brigitte.html
import brigitte.prefs
import com.example.nvblog.R
import com.example.nvblog.ui.notification.NotificationViewModel

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-12 <p/>
 */
@RunWith(RobolectricTestRunner::class)
class NotificationViewModelTest: BaseRoboViewModelTest<NotificationViewModel>() {
    @Before
    @Throws(Exception::class)
    fun setup() {
        initMock()

        viewmodel = NotificationViewModel(app)
    }

    @Test
    fun defaultValueTest() {
        viewmodel.apply {
            viewType.get()
            noticeData.get()
            viewNotice.get()
            viewNotRead.get()
            viewProgress.get()
        }
    }

    @Test
    fun convertNotificationTest() {
        viewmodel.apply {
            val str = "hello world"
            val result = convertNotification(str)?.toHtml()
            val comparision = """$str<br><font color="#00AC09"><b>from.블로그 씨</b></font> """.html()?.toHtml()
        }
    }

    @Test
    fun iconTypeTest() {
        viewmodel.apply {
            var result = iconType(0)

            result = iconType(1)

            result = iconType(999)
        }
    }

    @Test
    fun textTypeTest() {
        viewmodel.apply {
            var result = textType(0)

            result = textType(1)

            result = textType(999)
        }
    }

    @Test
    fun convertDateTest() {
        viewmodel.apply {
            val value = System.currentTimeMillis()
            val result = convertDate(value)
        }
    }

    @Test
    fun convertNumOfAppliedForFriendTest() {
        viewmodel.apply {
            val value = 9
            val result = convertNumOfAppliedForFriend(value)
        }
    }

    @Test
    fun convertNoNewsPostsTest() {
        viewmodel.apply {
            val value = 9
            val result = convertNumOfAppliedForFriend(value)
        }
    }

    @Test
    fun viewTypeCheckedListenerTest() {
        viewmodel.apply {
            viewTypeCheckedListener.get()?.invoke(R.id.noti_news)
            viewNotRead.get()
            items.get()

//            mockObserver(viewTypeLive) {
//                verifyChanged(R.id.noti_news)
//            }

            viewTypeCheckedListener.get()?.invoke(R.id.noti_posted)
            viewNotRead.get()
            items.get()
//            mockObserver(viewTypeLive) {
//                verifyChanged(R.id.noti_posted)
//            }
        }
    }

    @Test
    fun commandHideNoticeTest() {
        viewmodel.apply {
            command(NotificationViewModel.IN_HIDE_NOTICE)
            viewNotice.get()
            app.prefs().getInt(NotificationViewModel.PREF_NOTI_VISIBILITY, View.GONE)
        }
    }

    @Test
    fun commandDeleteItemTest() {
        viewmodel.apply {
            val before = items.get()?.size
            command(NotificationViewModel.IN_HIDE_NOTICE, 0)
            val after = items.get()?.size
        }
    }

    @Test
    fun commandNotReadTest() {
        viewmodel.apply {
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // MOCK
    //
    ////////////////////////////////////////////////////////////////////////////////////

    companion object {
        private val mLog = LoggerFactory.getLogger(NotificationViewModelTest::class.java)
    }

    //override fun initMock() {
    //    super.initMock()
    //
    //    initShadow()
    //    shadowApp?.grantPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION)
    //}
}