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

package com.example.nvblog.ui.myblog

import brigitte.BaseDaggerFragment
import brigitte.interval
import brigitte.singleTimer
import com.example.nvblog.R
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
    override val layoutId = R.layout.myblog_fragment

    companion object {
        private val mLog = LoggerFactory.getLogger(MyblogFragment::class.java)
        fun create() = MyblogFragment()
    }

    private val mTitlebarModel: TitlebarViewModel by activityInject()
    private val mMyblogPopularPostViewModel: MyblogPopularPostViewModel by inject()
    private val mMyblogAllPostViewModel: MyblogAllPostViewModel by inject()

    @Inject lateinit var viewController: ViewController

    override fun bindViewModel() {
        super.bindViewModel()

        mBinding.apply {
            titlebarModel    = mTitlebarModel
            popularPostModel = mMyblogPopularPostViewModel
            allPostModel     = mMyblogAllPostViewModel
        }

        disposable().let {
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
            disposable().clear()
            disposable().add(singleTimer(500)
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
