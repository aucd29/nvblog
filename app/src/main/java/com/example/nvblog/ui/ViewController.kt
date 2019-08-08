package com.example.nvblog.ui

import androidx.fragment.app.FragmentManager
import brigitte.FragmentCommit
import brigitte.FragmentParams
import brigitte.showBy
import org.slf4j.LoggerFactory
import javax.inject.Inject
import com.example.nvblog.R
import com.example.nvblog.ui.main.MainFragment
import com.example.nvblog.ui.myblog.MyblogFragment
import com.example.nvblog.ui.notification.NotificationFragment
import com.example.nvblog.ui.recommended.RecommendedFragment
import com.example.nvblog.ui.write.WriteFragment

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2018. 12. 13. <p/>
 */
class ViewController @Inject constructor(private val manager: FragmentManager) {
    companion object {
        private val mLog = LoggerFactory.getLogger(ViewController::class.java)

        const val CONTAINER  = R.id.root_bottom_navigation_container
    }

    @Inject lateinit var main: dagger.Lazy<MainFragment>
    @Inject lateinit var recommended: dagger.Lazy<RecommendedFragment>
    @Inject lateinit var write: dagger.Lazy<WriteFragment>
    @Inject lateinit var notification: dagger.Lazy<NotificationFragment>
    @Inject lateinit var myblog: dagger.Lazy<MyblogFragment>

    fun mainFragment(): Boolean {
        if (mLog.isInfoEnabled) {
            mLog.info("MAIN FRAGMENT")
        }

        manager.showBy(FragmentParams(CONTAINER, commit = FragmentCommit.NOW,
            fragment  = main.get(), add = false, backStack = false))

        return true
    }

    fun recommendedFragment(): Boolean {
        if (mLog.isInfoEnabled) {
            mLog.info("RECOMMENDED FRAGMENT")
        }

        manager.showBy(FragmentParams(CONTAINER, commit = FragmentCommit.NOW,
            fragment  = recommended.get(), add = false, backStack = false))

        return true
    }

    fun writeFragment(): Boolean {
        if (mLog.isInfoEnabled) {
            mLog.info("WRITE FRAGMENT")
        }

        manager.showBy(FragmentParams(CONTAINER, commit = FragmentCommit.NOW,
            fragment  = write.get(), add = false, backStack = false))

        return true
    }

    fun notificationFragment(): Boolean {
        if (mLog.isInfoEnabled) {
            mLog.info("NOTIFICATION FRAGMENT")
        }

        manager.showBy(FragmentParams(CONTAINER, commit = FragmentCommit.NOW,
            fragment  = notification.get(), add = false, backStack = false))

        return true
    }


    fun myblogFragment(): Boolean {
        if (mLog.isInfoEnabled) {
            mLog.info("MYBLOG FRAGMENT")
        }

        manager.showBy(FragmentParams(CONTAINER, commit = FragmentCommit.NOW,
            fragment  = myblog.get(), add = false, backStack = false))

        return true
    }
}