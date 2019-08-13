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
import brigitte.string
import com.example.nvblog.R
import com.example.nvblog.ui.notification.NotificationViewModel
import io.reactivex.disposables.CompositeDisposable

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
    fun defaultValueTest() = viewmodel.run {
        viewType.get().assertEquals(R.id.noti_news)
        noticeData.get().assertEquals(string(R.string.noti_lorem))
        viewNotice.get().assertEquals(prefs().getInt(NotificationViewModel.PREF_NOTI_VISIBILITY, View.VISIBLE))
        viewNotRead.get().assertEquals(View.VISIBLE)
        viewProgress.get().assertEquals(View.GONE)
    }

    @Test
    fun convertNotificationTest() = viewmodel.run {
        val str = "hello world"
        val result = convertNotification(str)?.toHtml()
        val comparision = """$str<br><font color="#00AC09"><b>from.블로그 씨</b></font> """.html()?.toHtml()

        result.assertEquals(comparision)
    }

    @Test
    fun iconTypeTest() = viewmodel.run {
        var result = iconType(0)
        result.assertEquals(R.drawable.ic_android_black_24dp)

        result = iconType(1)
        result.assertEquals(R.drawable.ic_favorite_black_24dp)

        result = iconType(999)
        result.assertEquals(R.drawable.ic_favorite_black_24dp)
    }

    @Test
    fun textTypeTest() = viewmodel.run {
        var result = textType(0)
        result.assertEquals(R.string.noti_reply)

        result = textType(1)
        result.assertEquals(R.string.noti_empathy)

        result = textType(999)
        result.assertEquals(R.string.noti_empathy)
    }

    @Test
    fun convertDateTest() {
        viewmodel.apply {
            val value = System.currentTimeMillis()
            val result = convertDate(value)
        }
    }

    @Test
    fun convertNumOfAppliedForFriendTest() = viewmodel.run {
        val value = 9
        val result = convertNumOfAppliedForFriend(value)?.toHtml()
        val comparision = ("<font color='black'>${string(R.string.noti_num_of_applied_for_friend)}</font>" +
                " <font color='#00AC09'>${app.getString(R.string.noti_people, value)}</font>").html()?.toHtml()

        result.assertEquals(comparision)
    }

    @Test
    fun convertNoNewsPostsTest() = viewmodel.run {
        val result = convertNoNewsPosts()?.toHtml()
        val comparision = ("<font color='#00AC09'>${string(R.string.noti_no_new_posts)}</font>" +
                "<br/>${string(R.string.noti_no_new_posts2)}").html()?.toHtml()

        result.assertEquals(comparision)
    }

    @Test
    fun viewTypeCheckedListenerTest() = viewmodel.run {
        disposable = CompositeDisposable()

        mockObserver<Int>(viewTypeLive).apply {
            viewTypeCheckedListener.get()?.invoke(R.id.noti_news)
            viewNotRead.assertEquals(View.VISIBLE)
            verifyChanged(R.id.noti_news)

            viewTypeCheckedListener.get()?.invoke(R.id.noti_posted)
            viewNotRead.assertEquals(View.GONE)
            viewProgress.assertEquals(View.VISIBLE)
            verifyChanged(R.id.noti_posted)
        }

        disposable.dispose()

        Unit
    }

    @Test
    fun commandHideNoticeTest() = viewmodel.run {
        command(NotificationViewModel.IN_HIDE_NOTICE)
        viewNotice.assertEquals(View.GONE)
        app.prefs().getInt(NotificationViewModel.PREF_NOTI_VISIBILITY, View.GONE).assertEquals(View.GONE)
    }

    @Test
    fun commandDeleteItemTest() = viewmodel.run {
        val list = postedDummyData()
        items.set(list)

        val before = items.get()?.size
        command(NotificationViewModel.IN_DELETE_ITEM, list[0].id)
        val after = items.get()?.size

        assert(before != after)
    }
}