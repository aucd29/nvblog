package com.example.nvblog.di.module

import com.example.nvblog.ui.browser.BrowserFragment
import com.example.nvblog.ui.main.MainFragment
import com.example.nvblog.ui.main.search.SearchFragment
import com.example.nvblog.ui.myblog.MyblogFragment
import com.example.nvblog.ui.notification.NotificationFragment
import com.example.nvblog.ui.recommended.RecommendedFragment
import com.example.nvblog.ui.write.WriteFragment
import dagger.Module

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2018. 12. 5. <p/>
 */

@Module(includes = [ MainFragment.Module::class
    , SearchFragment.Module::class

    , MyblogFragment.Module::class
    , NotificationFragment.Module::class
    , RecommendedFragment.Module::class
    , WriteFragment.Module::class
    , BrowserFragment.Module::class
])
class FragmentModule {

}