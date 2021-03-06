package com.example.nvblog.ui.write

import brigitte.BaseDaggerFragment
import com.example.nvblog.R
import com.example.nvblog.databinding.WriteFragmentBinding
import com.example.nvblog.ui.titlebar.TitlebarViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */
class WriteFragment @Inject constructor(
): BaseDaggerFragment<WriteFragmentBinding, WriteViewModel>() {
    override val layoutId = R.layout.write_fragment

    private val mTitlebarModel: TitlebarViewModel by activityInject()

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
        abstract fun contributeWriteFragmentInjector(): WriteFragment

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
