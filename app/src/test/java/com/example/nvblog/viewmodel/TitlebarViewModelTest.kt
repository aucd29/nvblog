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
    fun defaultValueTest() = viewmodel.run {
        titleId.assertEquals(R.string.titlebar_title)
        viewpagerPos.assertEquals(0)
        viewpagerSmooth.assertEquals(false)
    }

    @Test
    fun naviItemSelectedListenerTest() = viewmodel.run {
        var returnValue: Boolean = false

        returnValue = naviItemSelectedListener.get()!!.invoke(R.id.nav_new_article)
        titleId.assertEquals(R.string.titlebar_title)
        viewpagerPos.assertEquals(0)
        returnValue.assertTrue()

        returnValue = naviItemSelectedListener.get()!!.invoke(R.id.nav_recommended)
        titleId.assertEquals(R.string.nav_recommended)
        viewpagerPos.assertEquals(1)
        returnValue.assertTrue()

        returnValue = naviItemSelectedListener.get()!!.invoke(R.id.nav_write)
        returnValue.assertTrue()

        returnValue = naviItemSelectedListener.get()!!.invoke(R.id.nav_mynotification)
        titleId.assertEquals(R.string.nav_mynotification)
        viewpagerPos.assertEquals(2)
        returnValue.assertTrue()

        returnValue = naviItemSelectedListener.get()!!.invoke(R.id.nav_myblog)
        titleId.assertEquals(R.string.nav_myblog)
        viewpagerPos.assertEquals(3)
        returnValue.assertTrue()

        returnValue = naviItemSelectedListener.get()!!.invoke(983483)
        returnValue.assertFalse()
    }

    @Test
    fun cmdTest() {
        viewmodel.apply {
            command(TitlebarViewModel.ITN_MOVE_FIRST_TAB)

            viewpagerPos.assertEquals(0)
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
}