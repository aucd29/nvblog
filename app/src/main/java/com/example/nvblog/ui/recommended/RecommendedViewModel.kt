package com.example.nvblog.ui.recommended

import android.app.Application
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import brigitte.RecyclerViewModel
import brigitte.app
import brigitte.dpToPx
import brigitte.widget.SwipeRefreshController
import com.example.nvblog.model.remote.entity.RecommendedData
import io.reactivex.disposables.CompositeDisposable
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

class RecommendedViewModel @Inject @JvmOverloads constructor(
    application: Application
) : AndroidViewModel(application) {
    val spinnerOffsetEnd = ObservableInt(130.dpToPx(app))
}