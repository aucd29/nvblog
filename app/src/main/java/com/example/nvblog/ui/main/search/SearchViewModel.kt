package com.example.nvblog.ui.main.search

import android.app.Application
import brigitte.RecyclerViewModel
import com.example.nvblog.model.remote.entity.SearchData
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class SearchViewModel @Inject @JvmOverloads constructor(
    application: Application
) : RecyclerViewModel<SearchData>(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(SearchViewModel::class.java)
    }
}