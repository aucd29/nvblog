package com.example.nvblog.ui.browser

import android.view.View
import android.webkit.WebView
import android.widget.ProgressBar
import brigitte.*
import brigitte.widget.*
import brigitte.widget.swiperefresh.VerticalSwipeRefreshLayout
import com.example.nvblog.common.Config
import com.example.nvblog.databinding.BrowserFragmentBinding
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import org.slf4j.LoggerFactory
import javax.inject.Inject
import com.example.nvblog.R

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-09 <p/>
 */

class BrowserFragment @Inject constructor(
): BaseDaggerFragment<BrowserFragmentBinding, BrowserViewModel>(), OnBackPressedListener {
    override val layoutId = R.layout.browser_fragment

    companion object {
        private val mLog = LoggerFactory.getLogger(BrowserFragment::class.java)

        const val K_URL = "url"
    }

    @Inject lateinit var config: Config

    private val webview: WebView
        get() = mBinding.browserWebview

    private val swipeRefresh: VerticalSwipeRefreshLayout
        get() = mBinding.browserSwipeRefresh

    private val progress: ProgressBar
        get() = mBinding.browserProgress


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

    override fun initViewBinding() = mBinding.run {
        val loadUrl = arguments?.getString(K_URL)
        webview.loadUrl(loadUrl)

        webview.defaultSetting(WebViewSettingParams(
            pageFinished = {
                progress.visibility = View.GONE

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
            },
            pageStarted = {
                progress.visibility = View.VISIBLE
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
        abstract fun contributeBrowserFragmentInjector(): BrowserFragment

        // @dagger.Module
        // companion object {
        //     @JvmStatic
        //     @Provides
        //     @Named("child_fragment_manager")
        //     fun provide(fragment: MainFragment): FragmentManager {
        //         return fragment.childFragmentManager
        //     }
        // }
    }
}
