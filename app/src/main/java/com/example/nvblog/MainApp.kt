package com.example.nvblog

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.example.nvblog.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.github.inflationx.viewpump.ViewPump
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class MainApp : MultiDexApplication(), HasActivityInjector {
    companion object {
        private val mLog = LoggerFactory.getLogger(MainApp::class.java)
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var viewPump: ViewPump

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

        ViewPump.init(viewPump)
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // HasActivityInjector
    //
    ////////////////////////////////////////////////////////////////////////////////////

    override fun activityInjector() = activityInjector
}