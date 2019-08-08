package com.example.nvblog.di.module

import androidx.lifecycle.ViewModel
import brigitte.di.dagger.module.ViewModelKey
import brigitte.viewmodel.SplashViewModel
import com.example.nvblog.ui.main.MainViewModel
import com.example.nvblog.ui.main.search.SearchViewModel
import com.example.nvblog.ui.myblog.MyblogViewModel
import com.example.nvblog.ui.navigation.NavigationViewModel
import com.example.nvblog.ui.notification.NotificationViewModel
import com.example.nvblog.ui.recommended.RecommendedViewModel
import com.example.nvblog.ui.write.WriteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2018. 12. 6. <p/>
 */
@Module
abstract class ViewModelModule {
    ////////////////////////////////////////////////////////////////////////////////////
    //
    // ROOT
    //
    ////////////////////////////////////////////////////////////////////////////////////

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(vm: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NavigationViewModel::class)
    abstract fun bindNavigationViewModel(vm: NavigationViewModel): ViewModel

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // MAIN
    //
    ////////////////////////////////////////////////////////////////////////////////////

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(vm: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(vm: SearchViewModel): ViewModel

    ////////////////////////////////////////////////////////////////////////////////////
    //
    //
    //
    ////////////////////////////////////////////////////////////////////////////////////

    @Binds
    @IntoMap
    @ViewModelKey(MyblogViewModel::class)
    abstract fun bindMyblogViewModel(vm: MyblogViewModel): ViewModel

    ////////////////////////////////////////////////////////////////////////////////////
    //
    //
    //
    ////////////////////////////////////////////////////////////////////////////////////

    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel::class)
    abstract fun bindNotificationViewModel(vm: NotificationViewModel): ViewModel

    ////////////////////////////////////////////////////////////////////////////////////
    //
    //
    //
    ////////////////////////////////////////////////////////////////////////////////////

    @Binds
    @IntoMap
    @ViewModelKey(RecommendedViewModel::class)
    abstract fun bindRecommendedViewModel(vm: RecommendedViewModel): ViewModel

    ////////////////////////////////////////////////////////////////////////////////////
    //
    //
    //
    ////////////////////////////////////////////////////////////////////////////////////

    @Binds
    @IntoMap
    @ViewModelKey(WriteViewModel::class)
    abstract fun bindWriteViewModel(vm: WriteViewModel): ViewModel

}
