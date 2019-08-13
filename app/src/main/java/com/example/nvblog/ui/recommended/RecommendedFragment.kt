package com.example.nvblog.ui.recommended

import android.webkit.WebView
import brigitte.*
import brigitte.widget.VerticalSwipeRefreshLayout
import com.example.nvblog.common.Config
import com.example.nvblog.databinding.RecommendedFragmentBinding
import com.example.nvblog.ui.ViewController
import com.example.nvblog.ui.titlebar.TitlebarViewModel
import dagger.android.ContributesAndroidInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class RecommendedFragment @Inject constructor(
): BaseDaggerFragment<RecommendedFragmentBinding, RecommendedViewModel>() {
    companion object {
        private val mLog = LoggerFactory.getLogger(RecommendedFragment::class.java)

        // 타이틀을 날릴 수 있는 옵션이 있을거 같은데?
        private const val LOAD_URL = "https://m.blog.naver.com/Recommendation.nhn"
        private const val POST_URL = "https://m.blog.naver.com/PostView.nhn"
    }

    @Inject lateinit var config: Config
    @Inject lateinit var viewController: ViewController

    private lateinit var mTitlebarModel: TitlebarViewModel

    private val webview: WebView
        get() = mBinding.recommendedWebview

    private val swipeRefresh: VerticalSwipeRefreshLayout
        get() = mBinding.recommendedSwipeRefresh

    override fun onPause() {
        super.onPause()

        webview.pause()
        mDisposable.clear()
    }

    override fun onResume() {
        webview.resume()

        super.onResume()
    }

    override fun onDestroyView() {
        mDisposable.dispose()
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

        mTitlebarModel = inject(requireActivity())

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

                        mDisposable.clear()

                        if (mLog.isDebugEnabled) {
                            mLog.debug("HIDE REFRESH ICON")
                        }
                    }
                }

                syncCookie()
            }
        ))

        swipeRefresh.setProgressViewOffset(false, 0, 130.dpToPx(requireContext()))
        swipeRefresh.setOnRefreshListener {
            if (mLog.isDebugEnabled) {
                mLog.debug("SWIPE RELOAD URL : ${webview.url}")
            }

            webview.reload()

            mDisposable.clear()
            mDisposable.add(singleTimer(config.TIMEOUT_RELOAD_ICO)
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
    // Module
    //
    ////////////////////////////////////////////////////////////////////////////////////

    @dagger.Module
    abstract class Module {
        @ContributesAndroidInjector
        abstract fun contributeRecommendedFragmentInjector(): RecommendedFragment
    }
}
