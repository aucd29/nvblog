package com.example.nvblog.ui

import androidx.fragment.app.FragmentManager
import brigitte.FragmentCommit
import brigitte.FragmentParams
import brigitte.showBy
import org.slf4j.LoggerFactory
import javax.inject.Inject
import com.example.nvblog.R
import com.example.nvblog.ui.main.MainFragment

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2018. 12. 13. <p/>
 */
class ViewController @Inject constructor(private val manager: FragmentManager) {
    companion object {
        private val mLog = LoggerFactory.getLogger(ViewController::class.java)

        const val CONTAINER  = R.id.root_container
    }

    @Inject lateinit var mMainFragment: dagger.Lazy<MainFragment>

    fun mainFragment() {
        if (mLog.isInfoEnabled) {
            mLog.info("MAIN FRAGMENT")
        }

        manager.showBy(FragmentParams(CONTAINER, commit = FragmentCommit.NOW,
            fragment  = mMainFragment.get(), backStack = false))
    }

}