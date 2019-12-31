/*
 * Copyright (C) Hanwha S&C Ltd., 2019. All rights reserved.
 *
 * This software is covered by the license agreement between
 * the end user and Hanwha S&C Ltd., and may be
 * used and copied only in accordance with the terms of the
 * said agreement.
 *
 * Hanwha S&C Ltd., assumes no responsibility or
 * liability for any errors or inaccuracies in this software,
 * or any consequential, incidental or indirect damage arising
 * out of the use of the software.
 */

package com.example.nvblog

import android.content.Context
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.core.view.GravityCompat
import brigitte.*
import brigitte.viewmodel.SplashViewModel
import brigitte.widget.VerticalDrawerLayout
import com.example.nvblog.common.Config
import com.example.nvblog.databinding.MainActivityBinding
import com.example.nvblog.ui.Navigator
import com.example.nvblog.ui.navigation.NavigationViewModel
import com.example.nvblog.ui.titlebar.TitlebarViewModel
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.OkHttpClient
import org.slf4j.LoggerFactory
import javax.inject.Inject

class MainActivity : BaseDaggerActivity<MainActivityBinding, SplashViewModel>() {
    override val layoutId = R.layout.main_activity

    companion object {
        private val mLog = LoggerFactory.getLogger(MainActivity::class.java)

        private val NAVI_GRAVITY = GravityCompat.END
    }

    @Inject lateinit var config: Config
    @Inject lateinit var navigator: Navigator

    private val adapter: MainAdapter by lazy { MainAdapter(supportFragmentManager, applicationContext) }

    private val mTitlebarModel: TitlebarViewModel by inject()
    private val mNavigationModel: NavigationViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        chromeInspector { if (mLog.isInfoEnabled) { mLog.info(it) }}
        exceptionCatcher { mLog.error("ERROR: $it") }
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        if (mLog.isDebugEnabled) {
            mLog.debug("START ACTIVITY")
        }
    }

    override fun attachBaseContext(newBase: Context) {
        // https://github.com/InflationX/Calligraphy
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(NAVI_GRAVITY)) {
            mBinding.drawerLayout.closeDrawer(NAVI_GRAVITY)
            return
        }

        super.onBackPressed()
    }

    override fun bindViewModel() {
        super.bindViewModel()

        mBinding.apply {
            naviModel     = mNavigationModel
            titlebarModel = mTitlebarModel
        }

        addCommandEventModels(mNavigationModel, mTitlebarModel)
    }

    override fun initViewBinding() = mBinding.run {
        drawerLayout.touchSlopDirection = VerticalDrawerLayout.END

        rootViewpager.adapter = adapter
        rootViewpager.offscreenPageLimit = adapter.count

        resizeNavigation()
        mViewModel.closeSplash()
    }

    private fun resizeNavigation() {
        // navigation view width 조정
        mBinding.mainNavView.layoutWidth(config.SCREEN.x * .85f)
    }

    override fun initViewModelEvents() {
        mViewModel.run {
            observe(closeEvent) {
//                viewSplash.gone()

                mBinding.root.removeView(mBinding.splash)
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // COMMAND
    //
    ////////////////////////////////////////////////////////////////////////////////////

    override fun onCommandEvent(cmd: String, data: Any) {
        TitlebarViewModel.apply {
            when (cmd) {
                CMD_GROUP_DIALOG    -> {}
                CMD_SEARCH_FRAGMENT -> {}
                CMD_WRITE_FRAGMENT  -> showWriteFragment()
                CMD_SHOW_NAVI       -> showNavigation()
            }
        }

        NavigationViewModel.apply {
            when (cmd) {
                CMD_HIDE_NAVI -> hideNavigation()
            }
        }
    }

    private fun showWriteFragment() {
        navigator.writeFragment()

        disposable().add(singleTimer(200)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _ -> mTitlebarModel.moveToHistory() })
    }

    private fun showNavigation() {
        hideKeyboard(mBinding.root)
        mBinding.drawerLayout.openDrawer(NAVI_GRAVITY)
    }

    private fun hideNavigation() {
        mBinding.drawerLayout.closeDrawer(NAVI_GRAVITY)
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // TEST
    //
    ////////////////////////////////////////////////////////////////////////////////////

    // https://github.com/chiuki/espresso-samples/tree/master/idling-resource-okhttp
    @VisibleForTesting
    @Inject lateinit var okhttp: OkHttpClient
}
