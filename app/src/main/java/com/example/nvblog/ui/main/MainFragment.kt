package com.example.nvblog.ui.main

import androidx.fragment.app.FragmentManager
import brigitte.BaseDaggerFragment
import brigitte.interval
import com.example.nvblog.databinding.MainFragmentBinding
import com.example.nvblog.ui.ViewController
import com.example.nvblog.ui.navigation.NavigationViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
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

    private lateinit var mNaviViewModel: NavigationViewModel

    override fun bindViewModel() {
        super.bindViewModel()

        mNaviViewModel = inject(requireActivity())

        mBinding.naviModel = mNaviViewModel
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

        when (cmd) {
            CMD_MOVE_FIRST_TAB  -> reloadContent()
            CMD_GROUP_DIALOG    -> groupDialog()
            CMD_SEARCH_FRAGMENT -> searchFragment()
        }
    }

    private fun reloadContent() {
    }

    private fun groupDialog() {
    }

    private fun searchFragment() {

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

         @dagger.Module
         companion object {
             @JvmStatic
             @Provides
             @Named("child_fragment_manager")
             fun provide(fragment: MainFragment): FragmentManager {
                 return fragment.childFragmentManager
             }
         }
    }
}
