package com.example.nvblog.ui.notification

import brigitte.BaseDaggerFragment
import com.example.nvblog.databinding.NotificationFragmentBinding
import com.example.nvblog.ui.titlebar.TitlebarViewModel
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class NotificationFragment @Inject constructor(
): BaseDaggerFragment<NotificationFragmentBinding, NotificationViewModel>() {
    private lateinit var mTitlebarModel: TitlebarViewModel

    override fun bindViewModel() {
        super.bindViewModel()

        mTitlebarModel = inject(requireActivity())

        mBinding.apply {
            titlebarModel = mTitlebarModel
        }
    }

    override fun initViewBinding() {
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
        abstract fun contributeNotificationFragmentInjector(): NotificationFragment

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
