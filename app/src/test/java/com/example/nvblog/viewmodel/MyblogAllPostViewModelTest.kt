package com.example.nvblog.viewmodel

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-13 <p/>
 */

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
import brigitte.shield.*
import brigitte.dpToPx
import brigitte.toDateString
import brigitte.widget.viewpager.GridItemDecoration
import brigitte.widget.viewpager.OffsetDividerItemDecoration
import com.example.nvblog.R
import com.example.nvblog.common.PreloadConfig
import com.example.nvblog.ui.myblog.MyblogAllPostViewModel
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-13 <p/>
 */
@RunWith(RobolectricTestRunner::class)
class MyblogAllPostViewModelTest: BaseRoboViewModelTest<MyblogAllPostViewModel>() {
    @Before
    @Throws(Exception::class)
    fun setup() {
        initMock()

        viewmodel = MyblogAllPostViewModel(app, PreloadConfig(app))
    }

    @Test
    fun defaultValueTest() = viewmodel.run {
        roundedCorners.assertEquals(7.dpToPx(app))
    }

    @Test
    fun initLayoutManagerTest() = viewmodel.run {
        initLayoutManager(0)
        assertTrue(layoutManager.get() is GridLayoutManager)

        initLayoutManager(1)
        assertTrue(layoutManager.get() is LinearLayoutManager)
    }

    @Test
    fun convertDateTest() = viewmodel.run {
        val date = System.currentTimeMillis()
        val result = convertDate(date)
        val target = date.toDateString(SimpleDateFormat(MyblogAllPostViewModel.DATE_FORMAT, Locale.getDefault()))

        result.assertEquals(target)
    }

    @Test
    fun viewTypeCheckedListenerTest() = viewmodel.run {
        viewTypeCheckedListener.get()?.invoke(R.id.myblog_view_type_grid_image)
        assertTrue(itemDecoration.get() is GridItemDecoration)
        adapter.get()?.mLayouts?.get(0).assertEquals(R.layout.myblog_item_all_post_grid_image)
        items.get()?.forEach {
            it.blogImage.assertNotNull()
        }

        viewTypeCheckedListener.get()?.invoke(R.id.myblog_view_type_list)
        assertTrue(itemDecoration.get() is OffsetDividerItemDecoration)
        adapter.get()?.mLayouts?.get(0).assertEquals(R.layout.myblog_item_all_post_list)

        viewTypeCheckedListener.get()?.invoke(R.id.myblog_view_type_video)
        assertTrue(itemDecoration.get() is OffsetDividerItemDecoration)
        adapter.get()?.mLayouts?.get(0).assertEquals(R.layout.myblog_item_all_post_video)

        viewTypeCheckedListener.get()?.invoke(R.id.myblog_view_type_blog)
        assertTrue(itemDecoration.get() is OffsetDividerItemDecoration)
        adapter.get()?.mLayouts?.get(0).assertEquals(R.layout.myblog_item_all_post_blog)
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // MOCK
    //
    ////////////////////////////////////////////////////////////////////////////////////

    companion object {
        private val mLog = LoggerFactory.getLogger(MyblogAllPostViewModelTest::class.java)
    }
}