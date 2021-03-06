package com.example.nvblog.model.remote.entity

import brigitte.IRecyclerDiff

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

data class NotificationData(
    val id: Int,
    val type: Int,
    val title: String,
    val date: Long
) : IRecyclerDiff {
    override fun itemSame(item: IRecyclerDiff): Boolean {
        val newItem = item as NotificationData
        return id == newItem.id
    }

    override fun contentsSame(item: IRecyclerDiff): Boolean {
        val newItem = item as NotificationData
        return title == newItem.title && type == newItem.type && date == newItem.date
    }
}