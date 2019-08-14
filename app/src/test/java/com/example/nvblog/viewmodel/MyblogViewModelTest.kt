package com.example.nvblog.viewmodel

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-13 <p/>
 */

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import briggite.shield.*
import brigitte.app
import brigitte.numberFormat
import com.example.nvblog.R
import com.example.nvblog.common.PreloadConfig
import com.example.nvblog.ui.myblog.MyblogViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.slf4j.LoggerFactory
import kotlin.math.abs

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-13 <p/>
 */
@RunWith(RobolectricTestRunner::class)
class MyblogViewModelTest: BaseRoboViewModelTest<MyblogViewModel>() {
    @Before
    @Throws(Exception::class)
    fun setup() {
        initMock()

        viewmodel = MyblogViewModel(app, PreloadConfig(app))
    }

    @Test
    fun defaultValueTest() = viewmodel.run {
        viewAlpha.assertEquals(1f)
        viewTransY.assertEquals(0f)
        swipeRefresh.isRefresh.assertEquals(false)
        swipeIsEnabled.assertEquals(true)
    }

    @Test
    fun convertVisiteCountTest() = viewmodel.run {
        val today = 0
        val total = 10

        val result = convertVisiteCount(today, total)
        val target = app.getString(R.string.myblog_visiter_count, today, total.numberFormat())

        result.assertEquals(target)
    }

    @Test
    fun convertFriendCountTest() = viewmodel.run {
        val count = 10

        val result = convertFriendCount(count)
        val target = app.getString(R.string.myblog_friend_count, count)

        result.assertEquals(target)
    }

    @Test
    fun blogItemTest() = viewmodel.run {
        val item = blogItem.get()!!
        item.userId.equals("aucd29")
        item.blogTitle.equals("Pandora's box")

        Unit
    }

    @Test
    fun swipeRefreshListenerTest() = viewmodel.run {
        mockObserver(swipeRefresh.refreshLive).apply {
            swipeRefresh.listener.get()?.invoke()
            verify(this, atLeastOnce()).onChanged(null)
        }

        Unit
    }

    @Test
    fun appbarChangedListenerTest() = viewmodel.run {
        val maxScroll = 100
        var offset    = 50

        appbarChangedListener.get()?.invoke(maxScroll, offset)

        val percentage = 1f - abs(offset).toFloat() / maxScroll.toFloat()
        viewAlpha.assertEquals(percentage)
        viewTransY.assertEquals(offset / 2f * -1f)
        swipeIsEnabled.assertFalse()

        offset    = 0
        appbarChangedListener.get()?.invoke(maxScroll, offset)
        swipeIsEnabled.assertTrue()

        Unit
    }
}