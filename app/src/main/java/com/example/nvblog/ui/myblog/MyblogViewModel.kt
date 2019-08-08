package com.example.nvblog.ui.myblog

import android.app.Application
import brigitte.CommandEventViewModel
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class MyblogViewModel @Inject @JvmOverloads constructor(
    application: Application

) : CommandEventViewModel(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(MyblogViewModel::class.java)
    }
}