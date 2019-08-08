package com.example.nvblog.ui.notification

import android.app.Application
import brigitte.RecyclerViewModel
import com.example.nvblog.model.remote.entity.NotificationData
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class NotificationViewModel @Inject @JvmOverloads constructor(
    application: Application
) : RecyclerViewModel<NotificationData>(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(NotificationViewModel::class.java)
    }
}