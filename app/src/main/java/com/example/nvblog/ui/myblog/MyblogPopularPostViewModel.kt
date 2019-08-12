package com.example.nvblog.ui.myblog

import android.app.Application
import android.graphics.Rect
import androidx.databinding.ObservableField
import brigitte.RecyclerViewModel
import brigitte.app
import brigitte.dpToPx
import brigitte.widget.viewpager.SpaceItemDecoration
import com.example.nvblog.R
import com.example.nvblog.model.remote.entity.BlogData
import io.reactivex.disposables.CompositeDisposable
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-12 <p/>
 */

class MyblogPopularPostViewModel @Inject @JvmOverloads constructor(
    application: Application
) : RecyclerViewModel<BlogData>(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(MyblogPopularPostViewModel::class.java)
    }

    lateinit var disposable: CompositeDisposable

    val itemDecoration = ObservableField(SpaceItemDecoration(Rect().apply {
        left   = 15.dpToPx(app)
        bottom = right
    }))

    init {
        initAdapter(R.layout.myblog_item_popular_post)
        initData()
    }

    private fun initData() {
        val itemList = arrayListOf<BlogData>()
        var i = 1

        itemList.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "too many argument to function"
            , "too many arguments to function `XXX' 함수 선언에 쓰인 파라메터 [parameter]의 수보다 실제 인자 [argument]의 개수가 많을 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        itemList.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "incompatible type for argument N of"
            , "incompatible type for argument N of `XXX'\n" +
                    "함수 선언에 쓰인 파라메터 [parameter]의 형과 실제 인자 [argument]의 형이 서로 달라서 변경할 수 없는 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        itemList.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "다운로드 예상시간 계산"
            , """<?\n" +
                    "echo(\"다운로드시간 : $\take288modem->hour 시간 $\take288modem->min 분$\take288modem->sec 초\");\n" +
                    " \n" +
                    "function getdntime($\size,$\speed)  /* $\size는 파일크기의 Byte ,$\speed는bps단위 */\n" +
                    "{"""
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        itemList.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "too many argument to function"
            , "too many arguments to function `XXX' 함수 선언에 쓰인 파라메터 [parameter]의 수보다 실제 인자 [argument]의 개수가 많을 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        itemList.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "incompatible type for argument N of"
            , "incompatible type for argument N of `XXX'\n" +
                    "함수 선언에 쓰인 파라메터 [parameter]의 형과 실제 인자 [argument]의 형이 서로 달라서 변경할 수 없는 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        itemList.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "다운로드 예상시간 계산"
            , """<?\n" +
                    "echo(\"다운로드시간 : $\take288modem->hour 시간 $\take288modem->min 분$\take288modem->sec 초\");\n" +
                    " \n" +
                    "function getdntime($\size,$\speed)  /* $\size는 파일크기의 Byte ,$\speed는bps단위 */\n" +
                    "{"""
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        itemList.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "too many argument to function"
            , "too many arguments to function `XXX' 함수 선언에 쓰인 파라메터 [parameter]의 수보다 실제 인자 [argument]의 개수가 많을 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        itemList.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "incompatible type for argument N of"
            , "incompatible type for argument N of `XXX'\n" +
                    "함수 선언에 쓰인 파라메터 [parameter]의 형과 실제 인자 [argument]의 형이 서로 달라서 변경할 수 없는 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        itemList.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "다운로드 예상시간 계산"
            , """<?\n" +
                    "echo(\"다운로드시간 : $\take288modem->hour 시간 $\take288modem->min 분$\take288modem->sec 초\");\n" +
                    " \n" +
                    "function getdntime($\size,$\speed)  /* $\size는 파일크기의 Byte ,$\speed는bps단위 */\n" +
                    "{"""
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        items.set(itemList)
    }
}