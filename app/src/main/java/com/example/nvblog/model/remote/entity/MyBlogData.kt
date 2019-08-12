package com.example.nvblog.model.remote.entity

import brigitte.IRecyclerDiff

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

data class MyBlogData(
    val id: Int,
    val userId: String,
    val userImage: String,
    val blogTitle: String,
    val todayCount: Int,
    val totalCount: Int,
    val friendCount: Int,
    val background: String
) : IRecyclerDiff {
    override fun itemSame(item: IRecyclerDiff): Boolean {
        val newItem = item as MyBlogData
        return id == newItem.id
    }

    override fun contentsSame(item: IRecyclerDiff): Boolean {
        val newItem = item as MyBlogData
        return blogTitle == newItem.blogTitle && totalCount == newItem.totalCount
    }
}

data class BlogData(
    val id: Int,
    val userId: String,
    val userImage: String,
    val blogTitle: String,
    val blogContent: String,
    val blogLink: String,
    val blogImage: String?,
    val tag: String,
    val like: Int = 0,
    val replyCount: Int = 10,
    val isSearch: Boolean = false,
    val date: Long = System.currentTimeMillis(),
    val likeCount: String = "0",
    val commentCount: String = "2"
) : IRecyclerDiff {
    override fun itemSame(item: IRecyclerDiff): Boolean {
        val newItem = item as BlogData
        return id == newItem.id
    }

    override fun contentsSame(item: IRecyclerDiff): Boolean {
        val newItem = item as BlogData
        return userId == newItem.userId && blogTitle == newItem.blogTitle && replyCount == newItem.replyCount
    }
}