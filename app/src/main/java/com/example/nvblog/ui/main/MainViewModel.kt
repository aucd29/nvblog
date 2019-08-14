package com.example.nvblog.ui.main

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import brigitte.*
import brigitte.arch.SingleLiveEvent
import brigitte.widget.viewpager.OffsetDividerItemDecoration
import com.example.nvblog.R
import com.example.nvblog.common.PreloadConfig
import com.example.nvblog.model.remote.entity.IMainData
import com.example.nvblog.model.remote.entity.MainData
import com.example.nvblog.model.remote.entity.MainDummy
import com.example.nvblog.model.remote.entity.MainNotification
import com.example.nvblog.ui.myblog.MyblogViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class MainViewModel @Inject @JvmOverloads constructor(
    application: Application,
    val mPreConfig: PreloadConfig
) : RecyclerViewModel<IMainData>(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(MainViewModel::class.java)

        const val CMD_OPEN_BLOG         = "open-blog"
        const val CMD_OPTION_DIALOG     = "option-dialog"
        const val CMD_CONNECT_APP       = "connect-app"
    }

    lateinit var disposable: CompositeDisposable

    val circleCrop     = ObservableBoolean(true)
    val roundedCorners = ObservableInt(10.dpToPx(app))
    val itemDecoration = ObservableField(OffsetDividerItemDecoration(app, R.drawable.shape_divider_gray, 15))

    val swipeRefreshListener = ObservableField<() -> Unit>()
    val swipeIsRefresh       = ObservableBoolean(false)

    val searchGroup = ObservableField<String>("all")

    init {
        initSwipeRefreshListener()
        initAdapter(R.layout.main_item_notification, R.layout.main_item_content, R.layout.main_item_dummy)
        items.set(mPreConfig.mainDataItem)
    }

    fun convertNotification(str: String) =
        """<font color="#00AC09"><b>알림!</b></font> $str""".html()

    private fun initSwipeRefreshListener() {
        swipeRefreshListener.set {
            if (mLog.isDebugEnabled) {
                mLog.debug("START SWIPE REFRESH")
            }

            disposable.clear()
            disposable.add(singleTimer(600)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ -> stopSwipeRefresh() })
        }
    }

    fun stopSwipeRefresh() {
        if (mLog.isDebugEnabled) {
            mLog.debug("STOP SWIPE REFRESH")
        }

        if (swipeIsRefresh.get() == false) {
            swipeIsRefresh.notifyChange()
        } else {
            swipeIsRefresh.set(false)
        }
    }
}