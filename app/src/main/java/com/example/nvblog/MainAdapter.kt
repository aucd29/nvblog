package com.example.nvblog

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import brigitte.string
import com.example.nvblog.ui.main.MainFragment
import com.example.nvblog.ui.myblog.MyblogFragment
import com.example.nvblog.ui.notification.NotificationFragment
import com.example.nvblog.ui.recommended.RecommendedFragment
import com.example.nvblog.ui.write.WriteFragment
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-09 <p/>
 */


class MainAdapter @Inject constructor(fm: FragmentManager
    , val context: Context
) : FragmentStatePagerAdapter(fm) {
    companion object {
        private val mLog = LoggerFactory.getLogger(MainAdapter::class.java)

        const val K_URL      = "url"
        const val K_POSITION = "position"
    }

    private val mTitleList = arrayListOf<String>()

    @Inject lateinit var mMain: MainFragment
    @Inject lateinit var mRecommended: RecommendedFragment
    @Inject lateinit var mNotification: NotificationFragment
    @Inject lateinit var mMyblog: MyblogFragment

    init {
        mTitleList.add(context.string(R.string.nav_new_article))
        mTitleList.add(context.string(R.string.nav_recommended))
        mTitleList.add(context.string(R.string.nav_mynotification))
        mTitleList.add(context.string(R.string.nav_myblog))
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> mMain
            1 -> mRecommended
            2 -> mNotification
            3 -> mMyblog
            else -> throw RuntimeException()
        }
    }

    override fun getPageTitle(position: Int) = mTitleList[position]
    override fun getCount() = mTitleList.size
}
