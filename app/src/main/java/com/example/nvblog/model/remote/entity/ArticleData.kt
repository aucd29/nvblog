package com.example.nvblog.model.remote.entity

import brigitte.IRecyclerDiff

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

data class ArticleData(val id: Int
    , val title: String
    , val date: Long
) : IRecyclerDiff {
    override fun itemSame(item: IRecyclerDiff): Boolean {
        val newItem = item as ArticleData
        return id == newItem.id
    }

    override fun contentsSame(item: IRecyclerDiff): Boolean {
        val newItem = item as ArticleData
        return title == newItem.title && date == newItem.date
    }
}