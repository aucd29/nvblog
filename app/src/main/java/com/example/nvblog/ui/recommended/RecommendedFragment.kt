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

package com.example.nvblog.ui.recommended

import android.webkit.WebView
import brigitte.*
import brigitte.widget.*
import brigitte.widget.swiperefresh.VerticalSwipeRefreshLayout
import com.example.nvblog.common.Config
import com.example.nvblog.databinding.RecommendedFragmentBinding
import com.example.nvblog.ui.ViewController
import com.example.nvblog.ui.titlebar.TitlebarViewModel
import dagger.android.ContributesAndroidInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import org.slf4j.LoggerFactory
import javax.inject.Inject
import com.example.nvblog.R

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class RecommendedFragment @Inject constructor(
): BaseDaggerFragment<RecommendedFragmentBinding, RecommendedViewModel>(), OnBackPressedListener {
    override val layoutId = R.layout.recommended_fragment

    companion object {
        private val mLog = LoggerFactory.getLogger(RecommendedFragment::class.java)

        fun create() = RecommendedFragment()

        // 타이틀을 날릴 수 있는 옵션이 있을거 같은데?
        private const val LOAD_URL = "https://m.blog.naver.com/Recommendation.nhn"
        private const val POST_URL = "https://m.blog.naver.com/PostView.nhn"
    }

    @Inject lateinit var config: Config
    @Inject lateinit var viewController: ViewController

    private val mTitlebarModel: TitlebarViewModel by activityInject()

    private val webview: WebView
        get() = mBinding.recommendedWebview

    private val swipeRefresh: VerticalSwipeRefreshLayout
        get() = mBinding.recommendedSwipeRefresh

    override fun onPause() {
        super.onPause()

        webview.pause()
        disposable().clear()
    }

    override fun onResume() {
        webview.resume()

        super.onResume()
    }

    override fun onDestroyView() {
        webview.free()

        super.onDestroyView()
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    //
    //
    ////////////////////////////////////////////////////////////////////////////////////

    override fun bindViewModel() {
        super.bindViewModel()

        mBinding.apply {
            titlebarModel = mTitlebarModel
        }
    }

    override fun initViewBinding() = mBinding.run {
        if (webview.url == null) {
            webview.loadUrl(LOAD_URL)
        }

        webview.defaultSetting(WebViewSettingParams(
            urlLoading = { _, url ->
                url?.let {
                    if (it.startsWith(POST_URL)) {
                        if (mLog.isDebugEnabled) {
                            mLog.debug("OPEN BROWSER FRAGMENT : $url")
                        }

                        viewController.browserFragment(it)
                    } else {
                        webview.loadUrl(url)
                    }
                }
            },
            pageFinished = {
                swipeRefresh.apply {
                    if (isRefreshing) {
                        isRefreshing = false     // hide refresh icon

                        disposable().clear()

                        if (mLog.isDebugEnabled) {
                            mLog.debug("HIDE REFRESH ICON")
                        }
                    }
                }

                syncCookie()
            }
        ))

        swipeRefresh.setOnRefreshListener {
            if (mLog.isDebugEnabled) {
                mLog.debug("SWIPE RELOAD URL : ${webview.url}")
            }

            webview.reload()

            disposable().clear()
            disposable().add(singleTimer(config.TIMEOUT_RELOAD_ICO)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ ->
                    if (mLog.isInfoEnabled) {
                        mLog.info("EXPLODE RELOAD ICO TIMER")
                    }

                    swipeRefresh.isRefreshing = false
                })
        }
    }

    override fun initViewModelEvents() {
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // OnBackPressedListener
    //
    ////////////////////////////////////////////////////////////////////////////////////

    override fun onBackPressed(): Boolean {
        if (webview.canGoBack()) {
            webview.goBack()

            return true
        }

        return false
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // Module
    //
    ////////////////////////////////////////////////////////////////////////////////////

    @dagger.Module
    abstract class Module {
        @ContributesAndroidInjector
        abstract fun contributeRecommendedFragmentInjector(): RecommendedFragment
    }

}
