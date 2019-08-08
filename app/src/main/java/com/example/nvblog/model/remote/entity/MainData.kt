package com.example.nvblog.model.remote.entity

import androidx.annotation.DrawableRes
import brigitte.IRecyclerDiff
import brigitte.IRecyclerItem

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

interface IMainData: IRecyclerDiff, IRecyclerItem {
    companion object {
        const val T_NOTIFICATION = 0
        const val T_CONTENT = 1
        const val T_DUMMY = 2
    }
}

data class MainNotification(val id: Int
    , val content:String
    , override var type: Int = IMainData.T_NOTIFICATION
) : IMainData {
    override fun itemSame(item: IRecyclerDiff): Boolean {
        val newItem = item as MainNotification
        return id == newItem.id
    }

    override fun contentsSame(item: IRecyclerDiff): Boolean {
        val newItem = item as MainNotification
        return content == newItem.content
    }
}

data class MainData(val id: Int
    , var pic: String
    , var userId: String
    , var likeCount: String
    , var commentCount: String
    , val title: String
    , var content: String
    , var link: String
    , var image: String
    , var readCount: String = "4000"
    , var badge: String = "30"
    , override var type: Int = IMainData.T_CONTENT
) : IMainData {
    override fun itemSame(item: IRecyclerDiff): Boolean {
        val newItem = item as MainData
        return id == newItem.id
    }

    override fun contentsSame(item: IRecyclerDiff): Boolean {
        val newItem = item as MainData
        return title == newItem.title
    }
}

data class MainDummy(val id: Int
    , @DrawableRes var pic: Int
    , var link: String
    , override var type: Int = IMainData.T_DUMMY
) : IMainData {
    override fun itemSame(item: IRecyclerDiff): Boolean {
        val newItem = item as MainDummy
        return id == newItem.id
    }

    override fun contentsSame(item: IRecyclerDiff): Boolean {
        val newItem = item as MainDummy
        return pic == newItem.pic
    }
}
