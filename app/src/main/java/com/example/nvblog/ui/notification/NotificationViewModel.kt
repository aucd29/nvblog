package com.example.nvblog.ui.notification

import android.app.Application
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import brigitte.*
import brigitte.widget.viewpager.OffsetDividerItemDecoration
import com.example.nvblog.R
import com.example.nvblog.model.remote.entity.NotificationData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class NotificationViewModel @Inject @JvmOverloads constructor(
    application: Application
) : RecyclerViewModel<NotificationData>(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(NotificationViewModel::class.java)

        const val PREF_NOTI_VISIBILITY  = "noti-visibility"

        const val ITN_HIDE_NOTICE = "hide-notice"
        const val ITN_DELETE_ITEM = "delete-item"
        const val ITN_NOT_READ    = "not-read"
    }

    lateinit var disposable: CompositeDisposable

    private lateinit var mNewList: ArrayList<NotificationData>
    private lateinit var mPostedList: ArrayList<NotificationData>

    val viewType = ObservableInt(R.id.noti_news)
    val viewTypeCheckedListener = ObservableField<(Int) -> Unit>()
    val viewTypeLive = MutableLiveData<Int>()

    val noticeData   = ObservableField<String>(string(R.string.noti_lorem))
    val viewNotice   = ObservableInt(prefs().getInt(PREF_NOTI_VISIBILITY, View.VISIBLE))
    val viewNotRead  = ObservableInt(View.VISIBLE)
    val viewProgress = ObservableInt(View.GONE)

    val itemAnimator = ObservableField<RecyclerView.ItemAnimator?>()
    val itemDecoration = ObservableField(OffsetDividerItemDecoration(app, R.drawable.shape_divider_gray, 40, 0))
    val numberOfAppliedForFriend = ObservableInt(2)

    val swipeRefreshListener = ObservableField<() -> Unit>()
    val swipeIsRefresh       = ObservableBoolean(false)

    init {
        initAdapter(R.layout.notification_item_content)
        initData()

        swipeRefreshListener()
        viewTypeListener()
    }

    private fun checkedNews() {
        viewNotRead.visible()

        items.set(mNewList)
    }

    private fun checkedPosted() {
        viewNotRead.gone()
        viewProgress.visible()

        disposable.clear()
        disposable.add(singleTimer(300)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewProgress.gone()
                items.set(mPostedList)
            }, ::errorLog))
    }

    fun testTitleWithId(item: NotificationData) =
        """${item.title} (${item.id})"""

    fun convertNotification(str: String) =
        """$str<br><font color="#00AC09"><b>from.블로그 씨</b></font> """.html()

    fun iconType(type: Int) =
        when (type) {
            0    -> R.drawable.ic_android_black_24dp
            else -> R.drawable.ic_favorite_black_24dp
        }

    fun textType(type: Int) =
        when (type) {
            0    -> R.string.noti_reply
            else -> R.string.noti_empathy
        }

    fun convertDate(date: Long) =
        "조금전"

    fun convertNumOfAppliedForFriend(num : Int) =
        ("<font color='black'>${string(R.string.noti_num_of_applied_for_friend)}</font>" +
                " <font color='#00AC09'>${app.getString(R.string.noti_people, num)}</font>").html()

    fun convertNoNewsPosts() =
        ("<font color='#00AC09'>${string(R.string.noti_no_new_posts)}</font>" +
                "<br/>${string(R.string.noti_no_new_posts2)}").html()

    private fun swipeRefreshListener() {
        swipeRefreshListener.set {
            disposable.clear()
            disposable.add(singleTimer(300)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ -> stopSwipeRefresh() })
        }
    }

    fun stopSwipeRefresh() {
        if (mLog.isDebugEnabled) {
            mLog.debug("STOP SWIPE REFRESH")
        }

        if (swipeIsRefresh.get() == false) {
            swipeIsRefresh.notifyChange()
        } else {
            swipeIsRefresh.set(false)
        }
    }

    private fun viewTypeListener() {
        viewTypeCheckedListener.set {
            if (mLog.isDebugEnabled) {
                mLog.debug("CHANGED LAYOUT ID : $it")
            }

            when (it) {
                R.id.noti_news   -> checkedNews()
                R.id.noti_posted -> checkedPosted()
            }

            viewTypeLive.value = it
        }

        viewTypeLive.value = viewType.get()
    }

    private fun initData() {
        var idx = 1
        mPostedList = arrayListOf(
            NotificationData(idx++, 0, "건강이 최고 가족도 최고", System.currentTimeMillis()),
            NotificationData(idx++, 1, "하노이 여행::호안끼엠 금은방에서 환전하기", System.currentTimeMillis()),
            NotificationData(idx++, 1, "SM5 TCE 제로백 시승기", System.currentTimeMillis() - 8400000),
            NotificationData(idx++, 0, "신형 바다이야기", System.currentTimeMillis() - 8400000),
            NotificationData(idx++, 1, "삭제된 글입니다.", System.currentTimeMillis() - 8400000),

            NotificationData(idx++, 1, "하노이 여행::호안끼엠 금은방에서 환전하기", System.currentTimeMillis() - 8400000),
            NotificationData(idx++, 1, "SM5 TCE 제로백 시승기", System.currentTimeMillis() - 8400000),
            NotificationData(idx++, 0, "신형 바다이야기", System.currentTimeMillis() - 6400000),
            NotificationData(idx++, 1, "삭제된 글입니다.", System.currentTimeMillis() - 86400000),
            NotificationData(idx++, 1, "하노이 여행::호안끼엠 금은방에서 환전하기", System.currentTimeMillis() - 86400000),

            NotificationData(idx++, 1, "SM5 TCE 제로백 시승기", System.currentTimeMillis() - 6400000),
            NotificationData(idx++, 0, "신형 바다이야기", System.currentTimeMillis() - 86400000),
            NotificationData(idx++, 1, "삭제된 글입니다.", System.currentTimeMillis() - 8400000),
            NotificationData(idx++, 1, "하노이 여행::호안끼엠 금은방에서 환전하기", System.currentTimeMillis() - 8600000),
            NotificationData(idx++, 1, "SM5 TCE 제로백 시승기", System.currentTimeMillis() - 86400000),

            NotificationData(idx++, 0, "신형 바다이야기", System.currentTimeMillis() - 86400000),
            NotificationData(idx++, 1, "삭제된 글입니다.", System.currentTimeMillis() - 86400000)
        )
        mNewList = arrayListOf<NotificationData>()

        items.set(mNewList)
    }

    @VisibleForTesting
    fun postedDummyData() =
        mPostedList

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // COMMAND
    //
    ////////////////////////////////////////////////////////////////////////////////////

    override fun command(cmd: String, data: Any) {
        when (cmd) {
            ITN_HIDE_NOTICE -> hideNotice()
            ITN_DELETE_ITEM -> deleteItem(data as Int)
            ITN_NOT_READ    -> notRead()

            else -> super.command(cmd, data)
        }
    }

    private fun hideNotice() {
        viewNotice.gone()
        prefs().edit { putInt(PREF_NOTI_VISIBILITY, View.GONE) }
    }

    private fun deleteItem(id: Int) {
        val newList = items.get()?.toMutableList()
        val foundItem = newList?.find { it.id == id }
        foundItem?.let {
            if (mLog.isDebugEnabled) {
                mLog.debug("REMOVE ID : ${it.id}")
            }

            newList.remove(it)
        }

        items.set(newList)
    }

    private fun notRead() {
        // TODO
    }
}