package com.example.nvblog.ui.myblog

import android.app.Application
import android.graphics.Rect
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import brigitte.*
import brigitte.widget.viewpager.GridItemDecoration
import brigitte.widget.viewpager.OffsetDividerItemDecoration
import brigitte.widget.viewpager.SpaceItemDecoration
import com.example.nvblog.R
import com.example.nvblog.model.remote.entity.BlogData
import io.reactivex.disposables.CompositeDisposable
import org.slf4j.LoggerFactory
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-12 <p/>
 */

class MyblogAllPostViewModel @Inject @JvmOverloads constructor(
    application: Application
) : RecyclerViewModel<BlogData>(application) {

    companion object {
        private val mLog = LoggerFactory.getLogger(MyblogAllPostViewModel::class.java)

        private const val PREF_VIEW_TYPE = "myblog-view-type"

        const val CMD_CONNECT_APP = "connect-app"
    }

    lateinit var disposable: CompositeDisposable

    val viewTypeCheckedListener = ObservableField<(Int) -> Unit>()
    val layoutManager  = ObservableField<RecyclerView.LayoutManager>()
    val itemDecoration = ObservableField<RecyclerView.ItemDecoration>()
    val roundedCorners = ObservableInt(7.dpToPx(app))

    init {
        initAdapter(viewType())
        initData()
        initItemDecoration()
        initViewTypeListener()
    }

    fun initLayoutManager(type: Int = 2) {
        layoutManager.set(when(type) {
            0    -> GridLayoutManager(app, 3)
            else -> LinearLayoutManager(app)
        })
    }

    fun convertDate(date: Long) = date.toDateString(SimpleDateFormat("yyyy. M. d.", Locale.getDefault()))

    private fun initItemDecoration(type: Int = 2) {
        itemDecoration.set(when (type) {
            0    -> GridItemDecoration(10.dpToPx(app), 3)
            else -> OffsetDividerItemDecoration(app, R.drawable.shape_divider_gray, 15)
        })
    }

    private fun initViewTypeListener() {
        viewTypeCheckedListener.set {
            val type = when(it) {
                R.id.myblog_view_type_grid_image -> 0
                R.id.myblog_view_type_list       -> 1
                R.id.myblog_view_type_video      -> 3
                else                             -> 2
            }

            if (mLog.isDebugEnabled) {
                mLog.debug("CHANGED VIEW TYPE : $type")
            }

            initLayoutManager(type)
            initItemDecoration(type)
            initAdapter(viewType(type))
            initData(type)
        }
    }

    private fun viewType(type: Int = 2): Int {
        val viewType = when (type) {
            0 -> R.layout.myblog_item_all_post_grid_image
            1 -> R.layout.myblog_item_all_post_list
            3 -> R.layout.myblog_item_all_post_video
            else -> R.layout.myblog_item_all_post_blog
        }

        if (mLog.isDebugEnabled) {
            mLog.debug("CHANGE VIEW TYPE : $viewType")
        }

        return viewType
    }

    private fun initData(type: Int = 2) {
        val itemList = arrayListOf<BlogData>()
        var i = 1

        itemList.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "C++ 프로그래머 Java Essential 알기 #1"
            , "이번에는 예외처리에 대해서 알아 보겠다.\n" +
                    "\n" +
                    "함수형 프로그램의 경우 호출 후 오류 발생시 goto 를 이용하거나 바로 return 하여 호출한 함수로 바로 전달을 하게 되는데 C++ 과 java에서는 try... catch 를 이용하게 되는데 보통 try{ } 내에 오류가 발생하면 throw 를 발생 시켜 catch 에서 오류를 인지하게 된다."
            , "https://m.blog.naver.com/PostView.nhn?blogId=kurome&logNo=70090692515&navType=tl"
            , "https://mblogthumb-phinf.pstatic.net/20100726_38/kurome_1280131793652LCQGX_gif/p8_1_kurome.gif?type=w210"
            , "C/C++"
        ))

        itemList.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "C++ 프로그래머 Java Essential 알기 #26"
            , "이번에는 제네릭에 관해서 이야기 하겠다.\n" +
                    "\n" +
                    "제네릭은 c++ 에 템플릿과 비슷한 개념을 가지며 이용법도 비슷하다. 굳이 코딩을 할 때 제네릭을 사용하지 않더라도 큰 문제는 없지만 제너릭을 사용하게 되면 좀더 다형성이 보장되는 코드를 생성할 수 있기 때문에 이용한다.\n" +
                    "\n" +
                    "예를 들어"
            , "https://m.blog.naver.com/PostView.nhn?blogId=kurome&logNo=70090433447&navType=tl"
            , "https://mblogthumb-phinf.pstatic.net/20091126_126/kurome_1259242628851N5P7k_jpg/sequencediagram1_kurome.jpg?type=w210"
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
            , "https://mblogthumb-phinf.pstatic.net/20091111_100/kurome_1257937601388DkqOl_jpg/vxp_011_kurome.jpg?type=w210"
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
            , "https://mblogthumb-phinf.pstatic.net/20091111_114/kurome_1257937601920cA9gx_jpg/vxp_021_kurome.jpg?type=w210"
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
            , "https://mblogthumb-phinf.pstatic.net/20091111_38/kurome_1257937602509Q06GN_jpg/vxp_031_kurome.jpg?type=w210"
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
            , "https://mblogthumb-phinf.pstatic.net/20091111_67/kurome_1257938000464QuYK6_png/snap_kurome.png?type=w210"
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

        when (type) {
            0 -> {
                val tmpList = arrayListOf<BlogData>()
                itemList.forEach {
                    if (it.blogImage != null) {
                        tmpList.add(it)
                    }
                }

                if (mLog.isDebugEnabled) {
                    mLog.debug("GRID IMAGE SIZE : ${tmpList.size}")
                }

                items.set(tmpList)
            }
            else -> items.set(itemList)
        }
    }
}