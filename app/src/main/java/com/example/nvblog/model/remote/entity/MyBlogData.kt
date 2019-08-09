package com.example.nvblog.model.remote.entity

import brigitte.IRecyclerDiff

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

data class MyBlogData(
    val id: Int,
    val userId: String,
    val userImage: String,
    val title: String,
    val todayCount: Int,
    val allCount: Int,
    val background: String
) : IRecyclerDiff {
    override fun itemSame(item: IRecyclerDiff): Boolean {
        val newItem = item as MyBlogData
        return id == newItem.id
    }

    override fun contentsSame(item: IRecyclerDiff): Boolean {
        val newItem = item as MyBlogData
        return title == newItem.title && allCount == newItem.allCount
    }
}