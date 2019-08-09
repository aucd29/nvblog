package com.example.nvblog.ui.main

import androidx.fragment.app.FragmentManager
import brigitte.BaseDaggerFragment
import brigitte.interval
import com.example.nvblog.ui.titlebar.TitlebarViewModel
import com.example.nvblog.databinding.MainFragmentBinding
import com.example.nvblog.ui.ViewController
import com.example.nvblog.ui.navigation.NavigationViewModel
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class MainFragment @Inject constructor(
):  BaseDaggerFragment<MainFragmentBinding, MainViewModel>() {
    companion object {
        private val mLog = LoggerFactory.getLogger(MainFragment::class.java)
    }

    @Inject lateinit var viewController: ViewController

    private lateinit var mTitlebarModel: TitlebarViewModel
    private lateinit var mNaviViewModel: NavigationViewModel

    override fun bindViewModel() {
        super.bindViewModel()

        mTitlebarModel = inject(requireActivity())
        mNaviViewModel = inject(requireActivity())

        mBinding.apply {
            naviModel     = mNaviViewModel
            titlebarModel = mTitlebarModel
        }
    }

    override fun initViewBinding() {
        mBinding.mainSwipeRefresh.setOnRefreshListener {
            mDisposable.add(interval(3000)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    // 로드 했다고 가정 하에
                    mBinding.mainSwipeRefresh.isRefreshing = false
                })
        }
    }

    override fun initViewModelEvents() {
    }
    
    ////////////////////////////////////////////////////////////////////////////////////
    //
    // COMMAND
    //
    ////////////////////////////////////////////////////////////////////////////////////

    override fun onCommandEvent(cmd: String, data: Any) = MainViewModel.run {
        if (mLog.isDebugEnabled) {
            mLog.debug("COMMAND : $cmd")
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
        abstract fun contributeMainFragmentInjector(): MainFragment
    }
}
