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

package com.example.nvblog.ui.notification

import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.view.children
import brigitte.*
import com.example.nvblog.databinding.NotificationFragmentBinding
import com.example.nvblog.ui.titlebar.TitlebarViewModel
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject
import com.example.nvblog.R

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class NotificationFragment @Inject constructor(
): BaseDaggerFragment<NotificationFragmentBinding, NotificationViewModel>() {
    override val layoutId = R.layout.notification_fragment

    private val mTitlebarModel: TitlebarViewModel by activityInject()

    override fun bindViewModel() {
        super.bindViewModel()

        mBinding.apply {
            titlebarModel = mTitlebarModel
        }
    }

    override fun initViewBinding() {
    }

    override fun initViewModelEvents() {
        observe(mViewModel.viewTypeLive) {
            mBinding.notiRadioGroup.children.iterator().forEach { v ->
                if (v is AppCompatRadioButton) {
                    v.boldById(it)
                }
            }
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
        abstract fun contributeNotificationFragmentInjector(): NotificationFragment
    }

    companion object {
        fun create() = NotificationFragment()
    }
}
