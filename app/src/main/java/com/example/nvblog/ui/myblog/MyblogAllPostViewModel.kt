package com.example.nvblog.ui.myblog

import android.app.Application
import android.graphics.Rect
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import brigitte.*
import brigitte.widget.viewpager.GridItemDecoration
import brigitte.widget.viewpager.OffsetDividerItemDecoration
import brigitte.widget.viewpager.SpaceItemDecoration
import com.example.nvblog.R
import com.example.nvblog.common.PreloadConfig
import com.example.nvblog.model.remote.entity.BlogData
import io.reactivex.disposables.CompositeDisposable
import org.slf4j.LoggerFactory
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-12 <p/>
 */

class MyblogAllPostViewModel @Inject @JvmOverloads constructor(
    application: Application,
    val mPreConfig: PreloadConfig
) : RecyclerViewModel<BlogData>(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(MyblogAllPostViewModel::class.java)

        private const val PREF_VIEW_TYPE = "myblog-view-type"

        const val CMD_CONNECT_APP = "connect-app"
        const val CMD_OPEN_BRS    = "open-brs"
    }

    lateinit var disposable: CompositeDisposable

    val viewTypeCheckedListener = ObservableField<(Int) -> Unit>()
    val layoutManager  = ObservableField<RecyclerView.LayoutManager>()
    val itemDecoration = ObservableField<RecyclerView.ItemDecoration>()
    val roundedCorners = ObservableInt(7.dpToPx(app))

    init {
        initAdapter(viewType())
        initData()
        initItemDecoration()
        initViewTypeListener()
    }

    fun initLayoutManager(type: Int = 2) {
        layoutManager.set(when(type) {
            0    -> GridLayoutManager(app, 3)
            else -> LinearLayoutManager(app)
        })
    }

    fun convertDate(date: Long) = date.toDateString(SimpleDateFormat("yyyy. M. d.", Locale.getDefault()))

    private fun initItemDecoration(type: Int = 2) {
        itemDecoration.set(when (type) {
            0    -> GridItemDecoration(10.dpToPx(app), 3)
            else -> OffsetDividerItemDecoration(app, R.drawable.shape_divider_gray, 15)
        })
    }

    private fun initViewTypeListener() {
        viewTypeCheckedListener.set {
            val type = when(it) {
                R.id.myblog_view_type_grid_image -> 0
                R.id.myblog_view_type_list       -> 1
                R.id.myblog_view_type_video      -> 3
                else                             -> 2
            }

            if (mLog.isDebugEnabled) {
                mLog.debug("CHANGED VIEW TYPE : $type")
            }

            initLayoutManager(type)
            initItemDecoration(type)
            initAdapter(viewType(type))
            initData(type)
        }
    }

    private fun viewType(type: Int = 2): Int {
        val viewType = when (type) {
            0 -> R.layout.myblog_item_all_post_grid_image
            1 -> R.layout.myblog_item_all_post_list
            3 -> R.layout.myblog_item_all_post_video
            else -> R.layout.myblog_item_all_post_blog
        }

        if (mLog.isDebugEnabled) {
            mLog.debug("CHANGE VIEW TYPE : $viewType")
        }

        return viewType
    }

    private fun initData(type: Int = 2) {
        val itemList = mPreConfig.allBlogDataItem

        when (type) {
            0 -> {
                val tmpList = arrayListOf<BlogData>()
                itemList.forEach {
                    if (it.blogImage != null) {
                        tmpList.add(it)
                    }
                }

                if (mLog.isDebugEnabled) {
                    mLog.debug("GRID IMAGE SIZE : ${tmpList.size}")
                }

                items.set(tmpList)
            }
            else -> items.set(itemList)
        }
    }
}