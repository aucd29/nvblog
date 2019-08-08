package com.example.nvblog.ui.main.group

import android.app.Application
import brigitte.RecyclerViewModel
import com.example.nvblog.model.local.GroupData
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class MainGroupViewModel @Inject @JvmOverloads constructor(
    application: Application
) : RecyclerViewModel<GroupData>(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(MainGroupViewModel::class.java)
    }
}