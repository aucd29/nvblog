package com.example.nvblog.model.remote.entity

import brigitte.IRecyclerDiff

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

data class SearchData(
    val id: Int,
    val title: String
) : IRecyclerDiff {
    override fun itemSame(item: IRecyclerDiff): Boolean {
        val newItem = item as SearchData
        return id == newItem.id
    }

    override fun contentsSame(item: IRecyclerDiff): Boolean {
        val newItem = item as SearchData
        return title == newItem.title
    }
}