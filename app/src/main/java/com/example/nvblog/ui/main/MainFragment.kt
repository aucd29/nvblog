package com.example.nvblog.ui.main

import androidx.fragment.app.FragmentManager
import brigitte.BaseDaggerFragment
import com.example.nvblog.databinding.MainFragmentBinding
import com.example.nvblog.ui.navigation.NavigationViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class MainFragment @Inject constructor(
):  BaseDaggerFragment<MainFragmentBinding, MainViewModel>() {


    override fun bindViewModel() {
        super.bindViewModel()
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
