package com.example.nvblog.ui.myblog

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import brigitte.CommandEventViewModel
import brigitte.app
import brigitte.numberFormat
import com.example.nvblog.R
import com.example.nvblog.model.remote.entity.MyBlogData
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class MyblogViewModel @Inject @JvmOverloads constructor(
    application: Application

) : CommandEventViewModel(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(MyblogViewModel::class.java)
    }

    val blogItem  = ObservableField<MyBlogData>()
    val circleCrop     = ObservableBoolean(true)

    val viewAlpha = ObservableFloat(1f)

    init {
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

    fun convertVisiteCount(today: Int, total: Int) =
        app.getString(R.string.myblog_visiter_count, today, total.numberFormat())

    fun convertFriendCount(count: Int) =
        app.getString(R.string.myblog_friend_count, count)

}