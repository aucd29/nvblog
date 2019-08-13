import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
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
import briggite.shield.*
import com.example.nvblog.ui.myblog.MyblogPopularPostViewModel


/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-13 <p/>
 */
@RunWith(RobolectricTestRunner::class)
class MyblogPopularPostViewModelTest: BaseRoboViewModelTest<MyblogPopularPostViewModel>() {
    @Before
    @Throws(Exception::class)
    fun setup() {
        initMock()

        viewmodel = MyblogPopularPostViewModel(app)
    }

    @Test
    fun defaultValueTest() = viewmodel.run {
        itemDecoration.assertNotNull()
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // MOCK
    //
    ////////////////////////////////////////////////////////////////////////////////////

    companion object {
        private val mLog = LoggerFactory.getLogger(MyblogPopularPostViewModelTest::class.java)
    }

    //override fun initMock() {
    //    super.initMock()
    //
    //    initShadow()
    //    shadowApp?.grantPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION)
    //}
}