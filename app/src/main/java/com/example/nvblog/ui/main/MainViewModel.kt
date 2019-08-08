package com.example.nvblog.ui.main

import android.app.Application
import brigitte.RecyclerViewModel
import com.example.nvblog.model.remote.entity.MainData
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class MainViewModel @Inject @JvmOverloads constructor(
    application: Application
) : RecyclerViewModel<MainData>(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(MainViewModel::class.java)
    }
}