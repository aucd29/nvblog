import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.text.toHtml
import androidx.databinding.Observable
import androidx.test.core.app.ApplicationProvider
import brigitte.html
import com.example.nvblog.ui.main.MainViewModel
import briggite.shield.*
import brigitte.dpToPx
import brigitte.observe
import brigitte.widget.viewpager.OffsetDividerItemDecoration
import com.example.nvblog.common.PreloadConfig
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.slf4j.LoggerFactory

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-12 <p/>
 */
@RunWith(RobolectricTestRunner::class)
class MainViewModelTest: BaseRoboViewModelTest<MainViewModel>() {
    @Before
    @Throws(Exception::class)
    fun setup() {
        initMock()

        viewmodel = MainViewModel(app, PreloadConfig(app), CompositeDisposable())
    }

    @Test
    fun defaultValueTest() = viewmodel.run {
        circleCrop.assertTrue()
        roundedCorners.assertEquals(10.dpToPx(app))
        assert(itemDecoration.get() is OffsetDividerItemDecoration)
        swipeRefresh.isRefresh.assertEquals(false)

        itemDecoration.get()?.mOffsetStartDp.assertEquals(15.dpToPx(app))
        itemDecoration.get()?.mOffsetEndDp.assertEquals(15.dpToPx(app))
    }

    @Test
    fun searchGroupTest() = viewmodel.run {
        searchGroup.get().assertEquals("all")
    }

    @Test
    fun convertNotificationTest() {
        val str = "hello"
        val result = viewmodel.convertNotification(str)?.toHtml()
        val comparision = """<font color="#00AC09"><b>알림!</b></font> $str""".html()?.toHtml()

        result.assertEquals(comparision)
    }

    @Test
    fun swipeRefreshListenerTest() = viewmodel.run {
        mockReactiveX()

        // https://stackoverflow.com/questions/44684014/android-binding-and-junit-testing-of-notification

        Single.just("").subscribe { _ ->
            swipeRefresh.isRefresh.set(true)

            if (mLog.isDebugEnabled) {
                mLog.debug("isRefresh 1. ${swipeRefresh.isRefresh.get()}")
            }

            swipeRefresh.listener.get()?.invoke()

            if (mLog.isDebugEnabled) {
                mLog.debug("isRefresh 2. ${swipeRefresh.isRefresh.get()}")
            }

            val mockObserver = mock(Observable.OnPropertyChangedCallback::class.java)
            swipeRefresh.isRefresh.addOnPropertyChangedCallback(mockObserver)

            verify(mockObserver, never()).onPropertyChanged(swipeRefresh.isRefresh, 0)


            if (mLog.isDebugEnabled) {
                mLog.debug("isRefresh 3. ${swipeRefresh.isRefresh.get()}")
            }

            swipeRefresh.isRefresh.assertFalse()
        }

//        swipeRefresh.isRefresh.observe {
//            if (mLog.isDebugEnabled) {
//                mLog.debug("IS REFRESH $it")
//            }
//            it.assertEquals(false)
//        }

        Unit
    }
    
    private val mLog = LoggerFactory.getLogger(MainViewModelTest::class.java)
}