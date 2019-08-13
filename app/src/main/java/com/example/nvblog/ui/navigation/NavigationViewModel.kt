package com.example.nvblog.ui.navigation

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.RecyclerView
import brigitte.RecyclerViewModel
import brigitte.app
import brigitte.numberFormat
import brigitte.widget.viewpager.PositionDividerItemDecoration
import com.example.nvblog.R
import com.example.nvblog.common.PreloadConfig
import com.example.nvblog.model.local.NavigationData
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class NavigationViewModel @Inject @JvmOverloads constructor(
    application: Application,
    private val mPreConfig: PreloadConfig
) : RecyclerViewModel<NavigationData>(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(NavigationViewModel::class.java)

        const val CMD_HIDE_NAVI = "hide-navi"
    }

    var blogItem    = mPreConfig.blogInfoItem
    val circleCrop  = ObservableBoolean(true)
    val itemDecoration = ObservableField<RecyclerView.ItemDecoration>()

    val dummyGraph  = ObservableInt(R.drawable.navi_dummy_graph)
    val dummyBanner = ObservableInt(R.drawable.navi_dummy_banner)

    init {
        initNaviData()
        itemDecoration.set(
            PositionDividerItemDecoration(app,
            R.drawable.shape_divider_gray_height_5dp,
            listOf(1, 5))
        )
    }

    fun convertVisiteCount(today: Int, total: Int) =
        app.getString(R.string.myblog_visiter_count, today, total.numberFormat())

    private fun initNaviData() {
        initAdapter(R.layout.main_navigation_item)
        items.set(mPreConfig.naviDataItem)
    }
}