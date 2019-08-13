package com.example.nvblog.ui.main

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import brigitte.*
import brigitte.arch.SingleLiveEvent
import brigitte.widget.viewpager.OffsetDividerItemDecoration
import com.example.nvblog.R
import com.example.nvblog.model.remote.entity.IMainData
import com.example.nvblog.model.remote.entity.MainData
import com.example.nvblog.model.remote.entity.MainDummy
import com.example.nvblog.model.remote.entity.MainNotification
import com.example.nvblog.ui.myblog.MyblogViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class MainViewModel @Inject @JvmOverloads constructor(
    application: Application
) : RecyclerViewModel<IMainData>(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(MainViewModel::class.java)

        const val CMD_OPEN_BLOG         = "open-blog"
        const val CMD_OPTION_DIALOG     = "option-dialog"
        const val CMD_CONNECT_APP       = "connect-app"
    }

    lateinit var disposable: CompositeDisposable

    val circleCrop     = ObservableBoolean(true)
    val roundedCorners = ObservableInt(10.dpToPx(app))
    val itemDecoration = ObservableField(OffsetDividerItemDecoration(app, R.drawable.shape_divider_gray, 15))

    val swipeRefreshListener = ObservableField<() -> Unit>()
    val swipeIsRefresh       = ObservableBoolean(false)

    val searchGroup = ObservableField<String>("all")

    init {
        initSwipeRefreshListener()
        initDummyData()
    }

    fun convertNotification(str: String) =
        """<font color="#00AC09"><b>알림!</b></font> $str""".html()

    private fun initSwipeRefreshListener() {
        swipeRefreshListener.set {
            if (mLog.isDebugEnabled) {
                mLog.debug("START SWIPE REFRESH")
            }

            disposable.add(interval(600)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    stopSwipeRefresh()
                })
        }
    }

    fun stopSwipeRefresh() {
        if (mLog.isDebugEnabled) {
            mLog.debug("END SWIPE REFRESH")
        }

        if (swipeIsRefresh.get() == false) {
            swipeIsRefresh.notifyChange()
        } else {
            swipeIsRefresh.set(false)
        }
    }

    private fun initDummyData() {
        val list = arrayListOf<IMainData>(MainNotification(1, "내가 추가한 이웃의 새글이 없을 경우"))

        (2..5).forEach {
            list.add(MainData(it,
                "https://blogpfthumb-phinf.pstatic.net/MjAxOTA2MjRfMTI4/MDAxNTYxMzUyNDMwMzE5.64U7JqCdZkJf9Ipc8xWlREenJwO81nNS1h-QNiNQeAQg.FiJcrPv44mkcL3qcUVGVyO1-9-BQBOzj2G61dGRP24sg.PNG.alsprla/profileImage.png?type=s1",
                "비타민$it","96", "17",
                "군산 ${it} 문어낚시 시작 통영은 시즌 마감", "올해 핫했던 통영 사량도 문어낚시 끝자락에 까망냥이와 문어낚시를 다녀왔습니다. \n" +
                        "남해에서는 유독 해가 뜰때 여명이 분홍색으로 올라오더군요.",
                "http://m.naver.com",
                "https://blogthumb.pstatic.net/MjAxOTA4MDdfMjgx/MDAxNTY1MTA0MTQzMjYy.xOZc6Qo2izqPXX8Ab9NYzGKIsqdjU54VT01ohMi2zOIg.yKD_kphv72Ou6o82Wd3esrB0djJgouRtU8mqLK5UkdUg.JPEG.j2toto/20190803_065912.jpg?type=s2"))
        }

        list.add(MainDummy(6, R.drawable.main_dummy1, "https://m.naver.com"))
        list.add(MainDummy(7, R.drawable.main_dummy2, "https://m.naver.com"))
        list.add(MainDummy(8, R.drawable.main_dummy3, "https://m.naver.com"))

        (9..20).forEach {
            list.add(MainData(it,
                "https://blogpfthumb-phinf.pstatic.net/MjAxOTA2MjRfMTI4/MDAxNTYxMzUyNDMwMzE5.64U7JqCdZkJf9Ipc8xWlREenJwO81nNS1h-QNiNQeAQg.FiJcrPv44mkcL3qcUVGVyO1-9-BQBOzj2G61dGRP24sg.PNG.alsprla/profileImage.png?type=s1",
                "비타민$it","96", "17",
                "군산 ${it} 문어낚시 시작 통영은 시즌 마감", "올해 핫했던 통영 사량도 문어낚시 끝자락에 까망냥이와 문어낚시를 다녀왔습니다. \n" +
                        "남해에서는 유독 해가 뜰때 여명이 분홍색으로 올라오더군요.",
                "http://m.naver.com",
                "https://blogthumb.pstatic.net/MjAxOTA4MDdfMjgx/MDAxNTY1MTA0MTQzMjYy.xOZc6Qo2izqPXX8Ab9NYzGKIsqdjU54VT01ohMi2zOIg.yKD_kphv72Ou6o82Wd3esrB0djJgouRtU8mqLK5UkdUg.JPEG.j2toto/20190803_065912.jpg?type=s2"))
        }

        initAdapter(R.layout.main_item_notification, R.layout.main_item_content, R.layout.main_item_dummy)
        items.set(list)
    }
}