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

package brigitte.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import com.google.android.material.navigation.NavigationView
import org.slf4j.LoggerFactory
import kotlin.math.abs

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2019. 2. 26. <p/>
 *
 * https://stackoverflow.com/questions/34136178/swiperefreshlayout-blocking-horizontally-scrolled-recyclerview
 */

open class VerticalNavigationView(
    context: Context,
    attrs: AttributeSet
) : NavigationView(context, attrs) {
    companion object {
        private val mLog = LoggerFactory.getLogger(VerticalNavigationView::class.java)
    }

    private var touchSlop: Int
    private var prevX: Float = 0f
    private var decliend: Boolean = false

    init {
        this.initLayout()
        touchSlop = ViewConfiguration.get(context).scaledTouchSlop * 40
    }

    open fun initLayout() {

    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                prevX = MotionEvent.obtain(ev).x
                decliend = false
            }
            MotionEvent.ACTION_MOVE -> {
                val evX = ev.x
                val xDiff = abs(evX - prevX)

                if (mLog.isDebugEnabled) {
                    mLog.debug("TOUCH SLOP : $touchSlop")
                }

                if (decliend || xDiff > touchSlop) {
                    decliend = true

                    return false
                }
            }
        }

        return super.onInterceptTouchEvent(ev)
    }
}