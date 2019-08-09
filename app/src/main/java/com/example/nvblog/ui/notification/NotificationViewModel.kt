package com.example.nvblog.ui.notification

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import brigitte.RecyclerViewModel
import brigitte.gone
import brigitte.html
import brigitte.string
import com.example.nvblog.R
import com.example.nvblog.model.remote.entity.NotificationData
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

        const val IN_HIDE_NOTICE = "hide-notice"
        const val IN_DELETE_ITEM = "delete-item"
    }

    val noticeData = ObservableField<String>()
    val viewNotice = ObservableInt(View.VISIBLE)

    init {
        noticeData.set(string(R.string.noti_lorem))
        var idx = 1
        val newsList = arrayListOf(
            NotificationData(idx++, 0, "건강이 최고 가족도 최고", System.currentTimeMillis()),
            NotificationData(idx++, 1, "하노이 여행::호안끼엠 금은방에서 환전하기", System.currentTimeMillis()),
            NotificationData(idx++, 1, "SM5 TCE 제로백 시승기", System.currentTimeMillis() - 8400000),
            NotificationData(idx++, 0, "신형 바다이야기", System.currentTimeMillis() - 8400000),
            NotificationData(idx++, 1, "삭제된 글입니다.", System.currentTimeMillis() - 8400000),NotificationData(1, 0, "건강이 최고 가족도 최고", System.currentTimeMillis() - 86400000),
            NotificationData(idx++, 1, "하노이 여행::호안끼엠 금은방에서 환전하기", System.currentTimeMillis() - 8400000),
            NotificationData(idx++, 1, "SM5 TCE 제로백 시승기", System.currentTimeMillis() - 8400000),
            NotificationData(idx++, 0, "신형 바다이야기", System.currentTimeMillis() - 6400000),
            NotificationData(idx++, 1, "삭제된 글입니다.", System.currentTimeMillis() - 86400000),NotificationData(1, 0, "건강이 최고 가족도 최고", System.currentTimeMillis() - 86400000),
            NotificationData(idx++, 1, "하노이 여행::호안끼엠 금은방에서 환전하기", System.currentTimeMillis() - 86400000),
            NotificationData(idx++, 1, "SM5 TCE 제로백 시승기", System.currentTimeMillis() - 6400000),
            NotificationData(idx++, 0, "신형 바다이야기", System.currentTimeMillis() - 86400000),
            NotificationData(idx++, 1, "삭제된 글입니다.", System.currentTimeMillis() - 8400000),NotificationData(1, 0, "건강이 최고 가족도 최고", System.currentTimeMillis() - 86400000),
            NotificationData(idx++, 1, "하노이 여행::호안끼엠 금은방에서 환전하기", System.currentTimeMillis() - 8600000),
            NotificationData(idx++, 1, "SM5 TCE 제로백 시승기", System.currentTimeMillis() - 86400000),
            NotificationData(idx++, 0, "신형 바다이야기", System.currentTimeMillis() - 86400000),
            NotificationData(idx++, 1, "삭제된 글입니다.", System.currentTimeMillis() - 86400000)
        )
        val postedList = arrayListOf<NotificationData>()

        initAdapter(R.layout.notification_item_content)
        items.set(postedList)
    }

    fun convertNotification(str: String) =
        """$str\n<font color="#00AC09"><b>누군가가 작성함</b></font> """.html()

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

    override fun command(cmd: String, data: Any) {
        when (cmd) {
            IN_HIDE_NOTICE -> viewNotice.gone()
            IN_DELETE_ITEM -> removeItem(data as Int)
            else -> super.command(cmd, data)
        }
    }

    private fun removeItem(id: Int) {
        val newList = items.get()?.toMutableList()
        val foundItem = newList?.find { it.id == id }
        foundItem?.let { newList.remove(it) }

        items.set(newList)
    }
}