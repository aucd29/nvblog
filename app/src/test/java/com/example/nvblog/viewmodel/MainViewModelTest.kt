import androidx.core.text.toHtml
import androidx.databinding.Observable
import brigitte.html
import com.example.nvblog.ui.main.MainViewModel
import brigitte.shield.*
import brigitte.dpToPx
import brigitte.notify
import brigitte.observe
import brigitte.widget.viewpager.OffsetDividerItemDecoration
import com.example.nvblog.common.PreloadConfig
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
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
        swipeRefresh.isRefresh.set(true)
        swipeRefresh.listener.set {
            swipeRefresh.stopSwipeRefresh()
        }

        swipeRefresh.isRefresh.mockCallback().apply {
            swipeRefresh.listener.get()?.invoke()

            verifyPropertyChanged()
        }

        swipeRefresh.isRefresh.assertFalse()

        Unit
    }
    
    private val mLog = LoggerFactory.getLogger(MainViewModelTest::class.java)
}