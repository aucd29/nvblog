import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.text.toHtml
import androidx.test.core.app.ApplicationProvider
import brigitte.html
import com.example.nvblog.ui.main.MainViewModel
import briggite.shield.*
import brigitte.dpToPx
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
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

        viewmodel = MainViewModel(app)
    }

    @Test
    fun defaultValueTest() = viewmodel.run {
        circleCrop.get().assertTrue()
        roundedCorners.get().assertEquals(10.dpToPx(app))
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
}