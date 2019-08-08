package com.example.nvblog

import android.content.Context
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import brigitte.BaseDaggerActivity
import brigitte.chromeInspector
import brigitte.exceptionCatcher
import brigitte.gone
import brigitte.viewmodel.SplashViewModel
import com.example.nvblog.databinding.MainActivityBinding
import com.example.nvblog.ui.ViewController
import com.example.nvblog.ui.navigation.NavigationViewModel
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import okhttp3.OkHttpClient
import org.slf4j.LoggerFactory
import javax.inject.Inject

class MainActivity : BaseDaggerActivity<MainActivityBinding, SplashViewModel>() {
    companion object {
        private val mLog = LoggerFactory.getLogger(MainActivity::class.java)
    }

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

    override fun bindViewModel() {
        super.bindViewModel()

        mNavigationModel = inject()

        mBinding.naviModel = mNavigationModel

        addCommandEventModels(mNavigationModel)
    }

    override fun initViewBinding() {
    }

    override fun initViewModelEvents() = mViewModel.run {
        observe(closeEvent) {
            viewSplash.gone()

            mBinding.root.removeView(mBinding.splash)
        }
    }

    override fun onCommandEvent(cmd: String, data: Any) {
        super.onCommandEvent(cmd, data)
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
