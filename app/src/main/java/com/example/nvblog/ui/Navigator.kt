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

package com.example.nvblog.ui

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import brigitte.*
import org.slf4j.LoggerFactory
import javax.inject.Inject
import com.example.nvblog.R
import com.example.nvblog.ui.browser.BrowserFragment
import com.example.nvblog.ui.write.WriteFragment
import javax.inject.Named

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2018. 12. 13. <p/>
 */
class Navigator @Inject constructor(
    @param:Named("activityFragmentManager") private val manager: FragmentManager
) {
    companion object {
        private val mLog = LoggerFactory.getLogger(Navigator::class.java)

        const val CONTAINER  = R.id.root
    }

    fun writeFragment() {
        if (mLog.isInfoEnabled) {
            mLog.info("WRITE FRAGMENT")
        }

        manager.show<WriteFragment>(FragmentParams(CONTAINER,
            anim = FragmentAnim.UP))
    }

    fun browserFragment(url: Any?) {
        if (mLog.isInfoEnabled) {
            mLog.info("BROWSER FRAGMENT $url")
        }

        if (url == null) {
            mLog.error("ERROR: URL == NULL")

            return
        }

        manager.show<BrowserFragment>(FragmentParams(CONTAINER,
            anim   = FragmentAnim.RIGHT_ALPHA,
            bundle = Bundle().apply {
                putString(BrowserFragment.K_URL, url.toString())
            }))
    }
}