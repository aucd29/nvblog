package com.example.nvblog.ui.navigation

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import brigitte.RecyclerViewModel
import brigitte.app
import brigitte.numberFormat
import com.example.nvblog.R
import com.example.nvblog.model.local.NavigationData
import com.example.nvblog.model.remote.entity.MyBlogData
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class NavigationViewModel @Inject @JvmOverloads constructor(
    application: Application
) : RecyclerViewModel<NavigationData>(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(NavigationViewModel::class.java)

        const val CMD_HIDE_NAVI = "hide-navi"
    }

    val blogItem    = ObservableField<MyBlogData>()
    val circleCrop  = ObservableBoolean(true)
    val itemDecoration = ObservableField<RecyclerView.ItemDecoration>()

    init {
        initBlogData()
        initNaviData()
    }

    fun convertVisiteCount(today: Int, total: Int) =
        app.getString(R.string.myblog_visiter_count, today, total.numberFormat())


    private fun initBlogData() {
        val item = MyBlogData(1
            ,"aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "Pandora's box"
            , 5
            , 20
            , 5
            , "https://mblogthumb-phinf.pstatic.net/MjAxOTA4MDdfMTY1/MDAxNTY1MTQ0OTkzMzU1.iisJeQYs_SgMxXMlU5xi-l0v9E5XpByQBNaTs56NGLIg.7__58q0LsCgKabDABfmhmxj7aFjgx0ZacZbjeptAWiog.JPEG.leenara0830/1565144694118.jpg?type=w800"
        )

        blogItem.set(item)
    }

    private fun initNaviData() {
        var i = 1
        val list = arrayListOf<NavigationData>(
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "내 동영상"),
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "지난 오늘 글"),
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "블로그 홈"),
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "블로그팀 공식블로그"),
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "이달의 블로그"),
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "공식블로그"),
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "공지사항"),
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "환경설정"),
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "로그인 정보"),
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "PC버전으로 보기"),
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "블로그 고객센터")
        )

        initAdapter(R.layout.main_navigation_item)
        items.set(list)
    }
}