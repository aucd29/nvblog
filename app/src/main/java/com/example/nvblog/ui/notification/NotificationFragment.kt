package com.example.nvblog.ui.notification

import android.graphics.Typeface
import android.widget.RadioButton
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.view.children
import brigitte.BaseDaggerFragment
import brigitte.interval
import com.example.nvblog.databinding.NotificationFragmentBinding
import com.example.nvblog.ui.titlebar.TitlebarViewModel
import com.fasterxml.jackson.databind.type.TypeFactory
import dagger.android.ContributesAndroidInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class NotificationFragment @Inject constructor(
): BaseDaggerFragment<NotificationFragmentBinding, NotificationViewModel>() {
    private lateinit var mTitlebarModel: TitlebarViewModel

    override fun bindViewModel() {
        super.bindViewModel()

        mViewModel.disposable = mDisposable

        mTitlebarModel = inject(requireActivity())

        mBinding.apply {
            titlebarModel = mTitlebarModel
        }
    }

    override fun initViewBinding() {
    }

    override fun initViewModelEvents() {
        observe(mViewModel.viewTypeLive) {
            mBinding.notiRadioGroup.children.iterator().forEach { v ->
                if (v is AppCompatRadioButton) {
                    v.setTypeface(v.typeface, if (v.id == it) {
                        Typeface.BOLD
                    } else {
                        Typeface.NORMAL
                    })
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // Module
    //
    ////////////////////////////////////////////////////////////////////////////////////

    @dagger.Module
    abstract class Module {
        @ContributesAndroidInjector
        abstract fun contributeNotificationFragmentInjector(): NotificationFragment
    }
}
