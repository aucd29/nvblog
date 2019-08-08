package com.example.nvblog.di.module

import com.example.nvblog.R
import dagger.Module
import dagger.Provides
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2018. 12. 19. <p/>
 */

@Module
class CalligraphyModule {
    @Provides
    fun provideViewPump(): ViewPump =
        ViewPump.builder().addInterceptor(CalligraphyInterceptor(
                CalligraphyConfig.Builder().setFontAttrId(R.attr.fontPath).build())).build()
}