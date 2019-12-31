package com.example.nvblog.ui.myblog

import android.app.Application
import android.graphics.Rect
import androidx.databinding.ObservableField
import brigitte.RecyclerViewModel
import brigitte.dpToPx
import brigitte.widget.viewpager.SpaceItemDecoration
import com.example.nvblog.R
import com.example.nvblog.common.PreloadConfig
import com.example.nvblog.model.remote.entity.BlogData
import io.reactivex.disposables.CompositeDisposable
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-12 <p/>
 */

class MyblogPopularPostViewModel @Inject @JvmOverloads constructor(
    application: Application,
    private val mPreConfig: PreloadConfig
) : RecyclerViewModel<BlogData>(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(MyblogPopularPostViewModel::class.java)

        const val CMD_OPEN_BRS = "open-brs"
    }

    lateinit var disposable: CompositeDisposable

    //https://stackoverflow.com/questions/37011982/how-to-add-margins-to-a-recyclerview-for-the-last-element/37012503
    val itemDecoration = ObservableField(SpaceItemDecoration(Rect().apply {
        left   = 15.dpToPx(app)
    }, Rect().apply {
        left   = 15.dpToPx(app)
        right  = left
    }))

    init {
        initAdapter(R.layout.myblog_item_popular_post)
        items.set(mPreConfig.popupularBlogDataItem)
    }
}