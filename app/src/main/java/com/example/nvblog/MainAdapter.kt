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

package com.example.nvblog

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import brigitte.string
import brigitte.widget.pageradapter.FragmentStatePagerAdapter
import com.example.nvblog.ui.main.MainFragment
import com.example.nvblog.ui.myblog.MyblogFragment
import com.example.nvblog.ui.notification.NotificationFragment
import com.example.nvblog.ui.recommended.RecommendedFragment
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-09 <p/>
 */


class MainAdapter @Inject constructor(
    fm: FragmentManager,
    val context: Context
) : FragmentStatePagerAdapter(fm) {
    companion object {
        private val mLog = LoggerFactory.getLogger(MainAdapter::class.java)

        const val K_URL      = "url"
        const val K_POSITION = "position"
    }

    private val mTitleList = arrayListOf<String>()

    init {
        mTitleList.add(context.string(R.string.nav_new_article))
        mTitleList.add(context.string(R.string.nav_recommended))
        mTitleList.add(context.string(R.string.nav_mynotification))
        mTitleList.add(context.string(R.string.nav_myblog))
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0    -> MainFragment.create()
            1    -> RecommendedFragment.create()
            2    -> NotificationFragment.create()
            else -> MyblogFragment.create()
        }
    }

    override fun getPageTitle(position: Int) = mTitleList[position]
    override fun getCount() = mTitleList.size
}
