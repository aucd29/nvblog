package com.example.nvblog.ui.write

import brigitte.BaseDaggerFragment
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

    private lateinit var mTitlebarModel: TitlebarViewModel

    override fun bindViewModel() {
        super.bindViewModel()

        mTitlebarModel = inject(requireActivity())
    }

    override fun initViewBinding() {
    }

    override fun initViewModelEvents() {
        mTitlebarModel.moveToHistory()
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
