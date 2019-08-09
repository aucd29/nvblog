package com.example.nvblog.ui.write

import android.app.Application
import androidx.databinding.ObservableInt
import brigitte.CommandEventViewModel
import com.example.nvblog.R
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class WriteViewModel @Inject @JvmOverloads constructor(
    application: Application

) : CommandEventViewModel(application) {

    val dummyImage = ObservableInt(R.drawable.write_dummy)

    companion object {
        private val mLog = LoggerFactory.getLogger(WriteViewModel::class.java)
    }
}