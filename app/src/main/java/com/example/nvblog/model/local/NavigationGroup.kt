package com.example.nvblog.model.local

import androidx.annotation.DrawableRes
import brigitte.IRecyclerDiff

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

data class NavigationData(val id: Int
    , @DrawableRes val icon: Int
    , val title: String
) : IRecyclerDiff {
    override fun itemSame(item: IRecyclerDiff): Boolean {
        val newItem = item as NavigationData
        return id == newItem.id
    }

    override fun contentsSame(item: IRecyclerDiff): Boolean {
        val newItem = item as NavigationData
        return icon == newItem.icon && title == newItem.title
    }
}