package com.example.nvblog.ui.myblog

import brigitte.BaseDaggerFragment
import brigitte.interval
import brigitte.singleTimer
import com.example.nvblog.databinding.MyblogFragmentBinding
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
    }

    override fun initViewBinding() {
        mMyblogAllPostViewModel.initLayoutManager()
        mBinding.myblogSwipeRefresh.isEnabled = false
    }

    override fun initViewModelEvents() {
        observe(mViewModel.swipeRefreshLive) {
            mDisposable.add(singleTimer(600)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    // 여러 데이터를 로드 했다고 가정 하에
                    mViewModel.stopSwipeRefresh()
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
    // Module
    //
    ////////////////////////////////////////////////////////////////////////////////////

    @dagger.Module
    abstract class Module {
        @ContributesAndroidInjector
        abstract fun contributeMyblogFragmentInjector(): MyblogFragment

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
