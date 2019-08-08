package com.example.nvblog

import android.content.Context
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.core.view.GravityCompat
import brigitte.*
import brigitte.viewmodel.SplashViewModel
import com.example.nvblog.common.Config
import com.example.nvblog.databinding.MainActivityBinding
import com.example.nvblog.ui.ViewController
import com.example.nvblog.ui.main.MainFragment
import com.example.nvblog.ui.myblog.MyblogFragment
import com.example.nvblog.ui.navigation.NavigationViewModel
import com.example.nvblog.ui.notification.NotificationFragment
import com.example.nvblog.ui.recommended.RecommendedFragment
import com.example.nvblog.ui.write.WriteFragment
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import okhttp3.OkHttpClient
import org.slf4j.LoggerFactory
import javax.inject.Inject

class MainActivity : BaseDaggerActivity<MainActivityBinding, SplashViewModel>() {
    companion object {
        private val mLog = LoggerFactory.getLogger(MainActivity::class.java)

        private val NAVI_GRAVITY = GravityCompat.END
    }

    @Inject lateinit var config: Config
    @Inject lateinit var viewController: ViewController

    private lateinit var mNavigationModel: NavigationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        chromeInspector { if (mLog.isInfoEnabled) { mLog.info(it) }}
        exceptionCatcher { mLog.error("ERROR: $it") }
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        if (mLog.isDebugEnabled) {
            mLog.debug("START ACTIVITY")
        }
        if (savedInstanceState == null) {
            viewController.mainFragment()
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

        mNavigationModel = inject()

        mBinding.naviModel = mNavigationModel

        addCommandEventModels(mNavigationModel)
    }

    override fun initViewBinding() {
        mBinding.rootBottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_new_article -> viewController.mainFragment()
                R.id.nav_recommeded -> viewController.recommendedFragment()
                R.id.nav_write -> viewController.writeFragment()
                R.id.nav_mynotification -> viewController.notificationFragment()
                R.id.nav_myblog -> viewController.myblogFragment()

                else -> false
            }
        }

        resizeNavigation()
        mViewModel.closeSplash()
    }

    private fun resizeNavigation() {
        // navigation view width 조정
        mBinding.mainNavView.layoutWidth(config.SCREEN.x * .85f)
    }

    override fun initViewModelEvents() = mViewModel.run {
        observe(closeEvent) {
            viewSplash.gone()

            mBinding.root.removeView(mBinding.splash)
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // COMMAND
    //
    ////////////////////////////////////////////////////////////////////////////////////

    override fun onCommandEvent(cmd: String, data: Any) {
        when (cmd) {
            NavigationViewModel.CMD_SHOW_NAVI -> showNavigation()
        }
    }

    private fun showNavigation() {
        hideKeyboard(mBinding.root)
        mBinding.drawerLayout.openDrawer(NAVI_GRAVITY)
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
