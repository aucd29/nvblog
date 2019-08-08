package com.example.nvblog.di

import android.app.Application
import brigitte.di.dagger.module.ContextModule
import com.example.nvblog.MainApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2018. 12. 5. <p/>
 *
 * https://medium.com/@iammert/new-android-injector-with-dagger-2-part-1-8baa60152abe
 * https://github.com/googlesamples/android-architecture-components/tree/master/GithubBrowserSample/app/src/main/java/com/android/example/github
 */

// androidx 옵션을 주면 java 파일은 support 를 바라보고 있지만
// 내부적으로는 androidx 로 class 를 생성해서 이를 반영한다.
// 며칠 삽질을 했다..=_ =

@Singleton
@Component(modules = [ AndroidSupportInjectionModule::class
    , ContextModule::class
    , AppModule::class
    , ActivityBindingModule::class
])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: MainApp)
}