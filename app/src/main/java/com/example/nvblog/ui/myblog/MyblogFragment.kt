package com.example.nvblog.ui.myblog

import brigitte.BaseDaggerFragment
import brigitte.interval
import com.example.nvblog.databinding.MyblogFragmentBinding
import com.example.nvblog.ui.titlebar.TitlebarViewModel
import dagger.android.ContributesAndroidInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class MyblogFragment @Inject constructor(
): BaseDaggerFragment<MyblogFragmentBinding, MyblogViewModel>() {
    private lateinit var mTitlebarModel: TitlebarViewModel

    override fun bindViewModel() {
        super.bindViewModel()

        mTitlebarModel = inject(requireActivity())

        mBinding.apply {
            titlebarModel = mTitlebarModel
        }
    }

    override fun initViewBinding() {
//        mBinding.myblogSwipeRefresh.setOnRefreshListener {
//            mDisposable.add(interval(2000)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    // 로드 했다고 가정 하에
//                    mBinding.myblogSwipeRefresh.isRefreshing = false
//                })
//        }
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
