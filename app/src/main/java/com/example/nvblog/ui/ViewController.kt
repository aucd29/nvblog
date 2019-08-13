package com.example.nvblog.ui

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import brigitte.FragmentAnim
import brigitte.FragmentCommit
import brigitte.FragmentParams
import brigitte.showBy
import org.slf4j.LoggerFactory
import javax.inject.Inject
import com.example.nvblog.R
import com.example.nvblog.ui.browser.BrowserFragment
import com.example.nvblog.ui.write.WriteFragment

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2018. 12. 13. <p/>
 */
class ViewController @Inject constructor(private val manager: FragmentManager) {
    companion object {
        private val mLog = LoggerFactory.getLogger(ViewController::class.java)

        const val CONTAINER  = R.id.root
    }

    @Inject lateinit var mWrite: dagger.Lazy<WriteFragment>
    @Inject lateinit var mBrowserFragment: dagger.Lazy<BrowserFragment>

    fun writeFragment() {
        if (mLog.isInfoEnabled) {
            mLog.info("WRITE FRAGMENT")
        }

        manager.showBy(FragmentParams(CONTAINER,
            fragment = mWrite.get(), anim = FragmentAnim.UP))
    }

    fun browserFragment(url: Any?) {
        if (mLog.isInfoEnabled) {
            mLog.info("BROWSER FRAGMENT $url")
        }

        if (url == null) {
            mLog.error("ERROR: URL == NULL")

            return
        }

        manager.showBy(FragmentParams(CONTAINER,
            fragment = mBrowserFragment.get(), anim = FragmentAnim.RIGHT_ALPHA,
            bundle = Bundle().apply {
                putString(BrowserFragment.K_URL, url.toString())
            }))
    }
}