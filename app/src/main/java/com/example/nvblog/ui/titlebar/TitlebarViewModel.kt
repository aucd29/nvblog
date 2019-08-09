package com.example.nvblog.ui.titlebar

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import brigitte.CommandEventViewModel
import com.example.nvblog.R
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-09 <p/>
 */

class TitlebarViewModel @Inject @JvmOverloads constructor(
    application: Application

) : CommandEventViewModel(application) {
    companion object {
        private val mLog = LoggerFactory.getLogger(TitlebarViewModel::class.java)

        const val CMD_MOVE_FIRST_TAB    = "move-first-tab"
        const val CMD_GROUP_DIALOG      = "group-dialog"
        const val CMD_SEARCH_FRAGMENT   = "search-fragment"
        const val CMD_WRITE_FRAGMENT    = "write-fragment"
        const val CMD_SHOW_NAVI         = "show-navi"
    }

    val titleId         = ObservableInt(R.string.titlebar_title)
    val viewpagerPos    = ObservableInt(0)
    val viewpagerSmooth = ObservableBoolean(false)

    val naviItemSelectedListener = ObservableField<(Int)-> Boolean>()

    init {
        naviItemSelectedListener.set {
            var result = true

            when (it) {
                R.id.nav_new_article -> {
                    titleId.set(R.string.titlebar_title)
                    viewpagerPos.set(0)
                }
                R.id.nav_recommended -> {
                    titleId.set(R.string.nav_recommended)
                    viewpagerPos.set(1)
                }
                R.id.nav_write -> {
                    command(CMD_WRITE_FRAGMENT)
                }
                R.id.nav_mynotification -> {
                    titleId.set(R.string.nav_mynotification)
                    viewpagerPos.set(2)
                }
                R.id.nav_myblog -> {
                    titleId.set(R.string.nav_myblog)
                    viewpagerPos.set(3)
                }
                else -> result = false
            }

            result
        }
    }

    override fun command(cmd: String, data: Any) {
        when (cmd) {
            CMD_MOVE_FIRST_TAB -> viewpagerPos.set(0)
        }

        super.command(cmd, data)
    }
}