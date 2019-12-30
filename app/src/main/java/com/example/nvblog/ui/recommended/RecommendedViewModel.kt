package com.example.nvblog.ui.recommended

import android.app.Application
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import brigitte.dpToPx
import brigitte.viewmodel.app
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class RecommendedViewModel @Inject @JvmOverloads constructor(
    application: Application
) : AndroidViewModel(application) {
    val spinnerOffsetEnd = ObservableInt(130.dpToPx(app))
}