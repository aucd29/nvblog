package com.example.nvblog.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.nvblog.common.PreloadConfig
import com.example.nvblog.ui.article.ArticleFragment
import com.example.nvblog.ui.myblog.MyblogFragment
import com.example.nvblog.ui.notification.NotificationFragment
import com.example.nvblog.ui.recommended.RecommendedFragment
import com.example.nvblog.ui.write.WriteFragment
import org.slf4j.LoggerFactory
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class MainTabAdapter @Inject constructor(
    @param:Named("child_fragment_manager") fm: FragmentManager
    , val preConfig: PreloadConfig
) : FragmentStatePagerAdapter(fm) {
    companion object {
        private val mLog = LoggerFactory.getLogger(MainTabAdapter::class.java)

        const val K_URL      = "url"
        const val K_POSITION = "position"
    }

    @Inject lateinit var article: ArticleFragment
    @Inject lateinit var recommended: RecommendedFragment
    @Inject lateinit var write: WriteFragment
    @Inject lateinit var notification: NotificationFragment
    @Inject lateinit var myblog: MyblogFragment

    override fun getItem(position: Int): Fragment {
       return when (position) {
           0 -> article
           1 -> recommended
           2 -> write
           3 -> notification
           4 -> myblog
           else -> {
               mLog.error("ERROR: UNKNOWN INDEX")
               throw RuntimeException("UNKNOWN INDEX")
           }
       }
    }

    override fun getPageTitle(position: Int) = preConfig.mainTabTitle[position]
    override fun getCount() = preConfig.mainTabTitle.size // Integer.MAX_VALUE
}