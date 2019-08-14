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

        // 뷰모델 내부에서 처리되는 명령들은 ITN (INTERNAL) 로 지정
        const val ITN_MOVE_FIRST_TAB    = "move-first-tab"

        const val CMD_GROUP_DIALOG      = "group-dialog"
        const val CMD_SEARCH_FRAGMENT   = "search-fragment"
        const val CMD_WRITE_FRAGMENT    = "write-fragment"
        const val CMD_SHOW_NAVI         = "show-navi"
    }

    val titleId         = ObservableInt(R.string.titlebar_title)
    val viewpagerPos    = ObservableInt(0)
    val viewpagerSmooth = ObservableBoolean(false)

    val naviItemSelectedListener = ObservableField<(Int)-> Boolean>()
    val naviItemSelectId = ObservableInt(R.id.nav_new_article)
    var naviHistory = R.id.nav_new_article

    init {
        naviItemSelectedListener.set {
            var result = true

            if (it != R.id.nav_write) {
                naviHistory = it
            }

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

    fun moveToHistory() {
        naviItemSelectId.set(naviHistory)
    }

    override fun command(cmd: String, data: Any) {
        when (cmd) {
            ITN_MOVE_FIRST_TAB -> moveToFirst()
            else -> super.command(cmd, data)
        }
    }

    private fun moveToFirst() {
        naviItemSelectId.apply {
            if (get() == R.id.nav_new_article) {
                notifyChange()
            } else {
                set(R.id.nav_new_article)
            }
        }
    }
}