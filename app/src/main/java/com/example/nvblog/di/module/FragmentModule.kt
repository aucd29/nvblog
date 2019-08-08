package com.example.nvblog.di.module

import com.example.nvblog.ui.article.ArticleFragment
import com.example.nvblog.ui.main.MainFragment
import com.example.nvblog.ui.myblog.MyblogFragment
import com.example.nvblog.ui.notification.NotificationFragment
import com.example.nvblog.ui.recommended.RecommendedFragment
import com.example.nvblog.ui.write.WriteFragment
import dagger.Module

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2018. 12. 5. <p/>
 */

@Module(includes = [ MainFragment.Module::class
    , ArticleFragment.Module::class
    , MyblogFragment.Module::class
    , NotificationFragment.Module::class
    , RecommendedFragment.Module::class
    , WriteFragment.Module::class
])
class FragmentModule {

}