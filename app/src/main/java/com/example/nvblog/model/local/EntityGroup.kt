package com.example.nvblog.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import brigitte.*

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2018. 12. 12. <p/>
 */

@Entity(tableName = "defaultConfig")
data class DefaultConfig (
    @PrimaryKey(autoGenerate = true)
    val _id: Int = 0,
    val date: Long
) : IRecyclerDiff {
    override fun itemSame(item: IRecyclerDiff): Boolean =
        if (item is DefaultConfig) _id == item._id
        else false

    override fun contentsSame(item: IRecyclerDiff)=
        this.date == (item as DefaultConfig).date
}
