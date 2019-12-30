/*
 * Copyright (C) Hanwha S&C Ltd., 2019. All rights reserved.
 *
 * This software is covered by the license agreement between
 * the end user and Hanwha S&C Ltd., and may be
 * used and copied only in accordance with the terms of the
 * said agreement.
 *
 * Hanwha S&C Ltd., assumes no responsibility or
 * liability for any errors or inaccuracies in this software,
 * or any consequential, incidental or indirect damage arising
 * out of the use of the software.
 */

package com.example.nvblog.ui.main

import brigitte.BaseDaggerFragment
import com.example.nvblog.R
import com.example.nvblog.ui.titlebar.TitlebarViewModel
import com.example.nvblog.databinding.MainFragmentBinding
import com.example.nvblog.ui.Navigator
import com.example.nvblog.ui.navigation.NavigationViewModel
import dagger.android.ContributesAndroidInjector
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class MainFragment @Inject constructor(
):  BaseDaggerFragment<MainFragmentBinding, MainViewModel>() {
    override val layoutId = R.layout.main_fragment

    companion object {
        private val mLog = LoggerFactory.getLogger(MainFragment::class.java)

        fun create() = MainFragment()
    }

    private val mTitlebarModel: TitlebarViewModel by activityInject()
    private val mNaviViewModel: NavigationViewModel by activityInject()

    @Inject lateinit var navigator: Navigator

    override fun bindViewModel() {
        super.bindViewModel()

        mBinding.apply {
            naviModel     = mNaviViewModel
            titlebarModel = mTitlebarModel
        }
    }

    override fun initViewBinding() {
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
            mLog.debug("COMAMND : $cmd")
        }

        when (cmd) {
            CMD_OPEN_BRS -> navigator.browserFragment(data)
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
