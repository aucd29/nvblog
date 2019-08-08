package com.example.nvblog.ui.navigation

import android.app.Application
import brigitte.RecyclerViewModel
import com.example.nvblog.model.local.NavigationData
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class NavigationViewModel @Inject @JvmOverloads constructor(
    application: Application
) : RecyclerViewModel<NavigationData>(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(NavigationViewModel::class.java)

        const val CMD_SHOW_NAVI = "show-navi"
    }
}