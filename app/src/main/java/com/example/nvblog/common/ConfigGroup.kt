package com.example.nvblog.common

import android.Manifest
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import brigitte.actionBarSize
import brigitte.runtimepermission.RuntimePermission
import brigitte.systemService
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

    val mainTabTitle = arrayListOf<String>()

    init {
        mainTabTitle.add("이웃새글")
        mainTabTitle.add("추천")
        mainTabTitle.add("글쓰기")
        mainTabTitle.add("내소식")
        mainTabTitle.add("내블로그")
    }

}
