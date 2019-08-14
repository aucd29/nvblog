package com.example.nvblog.ui.myblog

import brigitte.BaseDaggerFragment
import brigitte.interval
import brigitte.singleTimer
import com.example.nvblog.databinding.MyblogFragmentBinding
import com.example.nvblog.ui.ViewController
import com.example.nvblog.ui.titlebar.TitlebarViewModel
import dagger.android.ContributesAndroidInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class MyblogFragment @Inject constructor(
): BaseDaggerFragment<MyblogFragmentBinding, MyblogViewModel>() {
    companion object {
        private val mLog = LoggerFactory.getLogger(MyblogFragment::class.java)
    }

    private lateinit var mTitlebarModel: TitlebarViewModel
    private lateinit var mMyblogPopularPostViewModel: MyblogPopularPostViewModel
    private lateinit var mMyblogAllPostViewModel: MyblogAllPostViewModel

    @Inject lateinit var viewController: ViewController

    override fun bindViewModel() {
        super.bindViewModel()

        mTitlebarModel              = inject(requireActivity())
        mMyblogPopularPostViewModel = inject()
        mMyblogAllPostViewModel     = inject()

        mBinding.apply {
            titlebarModel    = mTitlebarModel
            popularPostModel = mMyblogPopularPostViewModel
            allPostModel     = mMyblogAllPostViewModel
        }

        mDisposable.let {
            mViewModel.disposable = it
            mMyblogPopularPostViewModel.disposable = it
            mMyblogAllPostViewModel.disposable = it
        }

        addCommandEventModels(mMyblogPopularPostViewModel, mMyblogAllPostViewModel)
    }

    override fun initViewBinding() {
        mMyblogAllPostViewModel.initLayoutManager()
        mBinding.myblogSwipeRefresh.isEnabled = false
    }

    override fun initViewModelEvents() {
        observe(mViewModel.swipeRefresh.refreshLive) {
            mDisposable.clear()
            mDisposable.add(singleTimer(500)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    // 여러 데이터를 로드 했다고 가정 하에
                    mViewModel.swipeRefresh.stopSwipeRefresh()
                }, ::errorLog))
        }
    }

    private fun errorLog(e: Throwable) {
        if (mLog.isDebugEnabled) {
            e.printStackTrace()
        }

        mLog.error("ERROR: ${e.message}")
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // COMMAND
    //
    ////////////////////////////////////////////////////////////////////////////////////

    override fun onCommandEvent(cmd: String, data: Any) {
        when (cmd) {
            "open-brs" -> viewController.browserFragment(data)
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // Module
    //
    ////////////////////////////////////////////////////////////////////////////////////

    @dagger.Module
    abstract class Module {
        @ContributesAndroidInjector
        abstract fun contributeMyblogFragmentInjector(): MyblogFragment
    }
}
