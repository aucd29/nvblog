package com.example.nvblog.ui.myblog

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import brigitte.CommandEventViewModel
import brigitte.app
import brigitte.arch.SingleLiveEvent
import brigitte.numberFormat
import com.example.nvblog.R
import com.example.nvblog.common.PreloadConfig
import io.reactivex.disposables.CompositeDisposable
import org.slf4j.LoggerFactory
import javax.inject.Inject
import kotlin.math.abs

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class MyblogViewModel @Inject @JvmOverloads constructor(
    application: Application,
    private val mPreConfig: PreloadConfig
) : CommandEventViewModel(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(MyblogViewModel::class.java)
    }

    lateinit var disposable: CompositeDisposable

    val blogItem   = mPreConfig.blogInfoItem
    val circleCrop = ObservableBoolean(true)

    // appbar
    val appbarChangedListener = ObservableField<(Int, Int) -> Unit>()
    val viewAlpha  = ObservableFloat(1f)
    val viewTransY = ObservableFloat(0f)

    val swipeRefreshListener = ObservableField<() -> Unit>()
    val swipeRefreshLive     = SingleLiveEvent<Void>()
    val swipeIsRefresh       = ObservableBoolean(false)
    val swipeIsEnabled       = ObservableBoolean(true)

    init {
        initOffsetListener()
        initSwipeRefreshListener()
    }

    fun convertVisiteCount(today: Int, total: Int) =
        app.getString(R.string.myblog_visiter_count, today, total.numberFormat())

    fun convertFriendCount(count: Int) =
        app.getString(R.string.myblog_friend_count, count)

    private fun initSwipeRefreshListener() {
        swipeRefreshListener.set {
            if (mLog.isDebugEnabled) {
                mLog.debug("START SWIPE REFRESH")
            }

            swipeRefreshLive.call()
        }
    }

    fun stopSwipeRefresh() {
        if (mLog.isDebugEnabled) {
            mLog.debug("STOP SWIPE REFRESH")
        }

        if (!swipeIsRefresh.get()) {
            swipeIsRefresh.notifyChange()
        } else {
            swipeIsRefresh.set(false)
        }
    }

    private fun initOffsetListener() {
        appbarChangedListener.set { maxScroll, offset ->
            val percentage = 1f - abs(offset).toFloat() / maxScroll.toFloat()

            if (mLog.isTraceEnabled) {
                mLog.trace("APPBAR (ALPHA) : $percentage")
            }

            viewAlpha.set(percentage)
            viewTransY.set(offset / 2f * -1f)

            if (offset == 0) {
                swipeIsEnabled.set(true)
            } else {
                swipeIsEnabled.set(false)
            }
        }
    }
}