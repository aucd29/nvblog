package com.example.nvblog.common

import android.Manifest
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import brigitte.actionBarSize
import brigitte.runtimepermission.RuntimePermission
import brigitte.string
import brigitte.systemService
import com.example.nvblog.R
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

////////////////////////////////////////////////////////////////////////////////////
//
// Config
//
////////////////////////////////////////////////////////////////////////////////////

@Singleton
class Config @Inject constructor(val context: Context) {
    val ACTION_BAR_HEIGHT: Float
    val SCREEN = Point()
    val TIMEOUT_RELOAD_ICO = 2000L

    init {
        //
        // ACTION BAR
        //

        ACTION_BAR_HEIGHT = context.actionBarSize()

        //
        // W / H
        //
        val windowManager = context.systemService<WindowManager>()
        windowManager?.defaultDisplay?.getSize(SCREEN)
    }
}


////////////////////////////////////////////////////////////////////////////////////
//
// PreloadConfig
//
////////////////////////////////////////////////////////////////////////////////////

@Singleton
class PreloadConfig @Inject constructor(
    val context: Context
) {
    companion object {
        private val mLog = LoggerFactory.getLogger(PreloadConfig::class.java)
    }

}
