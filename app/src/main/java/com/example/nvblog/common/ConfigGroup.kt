package com.example.nvblog.common

import android.content.Context
import android.graphics.Point
import android.view.WindowManager
import androidx.databinding.ObservableField
import brigitte.actionBarSize
import brigitte.systemService
import com.example.nvblog.R
import com.example.nvblog.model.local.NavigationData
import com.example.nvblog.model.remote.entity.*
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2019-08-08 <p/>
 */

////////////////////////////////////////////////////////////////////////////////////
//
// Config
//
////////////////////////////////////////////////////////////////////////////////////

@Singleton
class Config @Inject constructor(val context: Context) {
    val ACTION_BAR_HEIGHT: Float
    val SCREEN = Point()
    val TIMEOUT_RELOAD_ICO = 2000L

    init {
        //
        // ACTION BAR
        //

        ACTION_BAR_HEIGHT = context.actionBarSize()

        //
        // W / H
        //
        val windowManager = context.systemService<WindowManager>()
        windowManager?.defaultDisplay?.getSize(SCREEN)
    }
}


////////////////////////////////////////////////////////////////////////////////////
//
// PreloadConfig
//
////////////////////////////////////////////////////////////////////////////////////

@Singleton
class PreloadConfig @Inject constructor(
    val context: Context
) {
    val blogInfoItem            = ObservableField<MyBlogData>()
    val mainDataItem            = arrayListOf<IMainData>()
    var naviDataItem            = arrayListOf<NavigationData>()
    val popupularBlogDataItem   = arrayListOf<BlogData>()
    val allBlogDataItem         = arrayListOf<BlogData>()

    companion object {
        private val mLog = LoggerFactory.getLogger(PreloadConfig::class.java)
    }

    init {
        initMyBlogInfo()
        initMainData()
        initNavigationData()
        initPopupularBlogData()
        initAllBlogData()
    }

    private fun initMyBlogInfo() {
        blogInfoItem.set(MyBlogData(1
            ,"aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "Pandora's box"
            , 5
            , 20
            , 5
            , "https://mblogthumb-phinf.pstatic.net/MjAxOTA4MDdfMTY1/MDAxNTY1MTQ0OTkzMzU1.iisJeQYs_SgMxXMlU5xi-l0v9E5XpByQBNaTs56NGLIg.7__58q0LsCgKabDABfmhmxj7aFjgx0ZacZbjeptAWiog.JPEG.leenara0830/1565144694118.jpg?type=w800"
        ))
    }

    private fun initMainData() {
        var i = 1

        mainDataItem.add(MainNotification(i++, "내가 추가한 이웃의 새글이 없을 경우"))
        (2..5).forEach {
            mainDataItem.add(
                MainData(it,
                    "https://blogpfthumb-phinf.pstatic.net/MjAxOTA2MjRfMTI4/MDAxNTYxMzUyNDMwMzE5.64U7JqCdZkJf9Ipc8xWlREenJwO81nNS1h-QNiNQeAQg.FiJcrPv44mkcL3qcUVGVyO1-9-BQBOzj2G61dGRP24sg.PNG.alsprla/profileImage.png?type=s1",
                    "비타민$it","96", "17",
                    "군산 ${it} 문어낚시 시작 통영은 시즌 마감", "올해 핫했던 통영 사량도 문어낚시 끝자락에 까망냥이와 문어낚시를 다녀왔습니다. \n" +
                            "남해에서는 유독 해가 뜰때 여명이 분홍색으로 올라오더군요.",
                    "http://m.naver.com",
                    "https://blogthumb.pstatic.net/MjAxOTA4MDdfMjgx/MDAxNTY1MTA0MTQzMjYy.xOZc6Qo2izqPXX8Ab9NYzGKIsqdjU54VT01ohMi2zOIg.yKD_kphv72Ou6o82Wd3esrB0djJgouRtU8mqLK5UkdUg.JPEG.j2toto/20190803_065912.jpg?type=s2")
            )
        }

        i = 6
        mainDataItem.add(MainDummy(i++, R.drawable.main_dummy1, "https://m.naver.com"))
        mainDataItem.add(MainDummy(i++, R.drawable.main_dummy2, "https://m.naver.com"))
        mainDataItem.add(MainDummy(i++, R.drawable.main_dummy3, "https://m.naver.com"))

        (9..20).forEach {
            mainDataItem.add(
                MainData(it,
                    "https://blogpfthumb-phinf.pstatic.net/MjAxOTA2MjRfMTI4/MDAxNTYxMzUyNDMwMzE5.64U7JqCdZkJf9Ipc8xWlREenJwO81nNS1h-QNiNQeAQg.FiJcrPv44mkcL3qcUVGVyO1-9-BQBOzj2G61dGRP24sg.PNG.alsprla/profileImage.png?type=s1",
                    "비타민$it","96", "17",
                    "군산 ${it} 문어낚시 시작 통영은 시즌 마감", "올해 핫했던 통영 사량도 문어낚시 끝자락에 까망냥이와 문어낚시를 다녀왔습니다. \n" +
                            "남해에서는 유독 해가 뜰때 여명이 분홍색으로 올라오더군요.",
                    "http://m.naver.com",
                    "https://blogthumb.pstatic.net/MjAxOTA4MDdfMjgx/MDAxNTY1MTA0MTQzMjYy.xOZc6Qo2izqPXX8Ab9NYzGKIsqdjU54VT01ohMi2zOIg.yKD_kphv72Ou6o82Wd3esrB0djJgouRtU8mqLK5UkdUg.JPEG.j2toto/20190803_065912.jpg?type=s2")
            )
        }
    }

    private fun initNavigationData() {
        var i = 1
        naviDataItem = arrayListOf(
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "내 동영상"),
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "지난 오늘 글"),
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "블로그 홈"),
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "블로그팀 공식블로그"),
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "이달의 블로그"),
            NavigationData(i++, R.drawable.ic_drive_eta_black_24dp, "공식블로그"),
            NavigationData(i++, R.drawable.ic_drive_eta_gray_24dp, "공지사항"),
            NavigationData(i++, R.drawable.ic_drive_eta_gray_24dp, "환경설정"),
            NavigationData(i++, R.drawable.ic_drive_eta_gray_24dp, "로그인 정보"),
            NavigationData(i++, R.drawable.ic_drive_eta_gray_24dp, "PC버전으로 보기"),
            NavigationData(i++, R.drawable.ic_drive_eta_gray_24dp, "블로그 고객센터")
        )
    }

    private fun initPopupularBlogData() {
        var i = 1

        popupularBlogDataItem.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "too many argument to function"
            , "too many arguments to function `XXX' 함수 선언에 쓰인 파라메터 [parameter]의 수보다 실제 인자 [argument]의 개수가 많을 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        popupularBlogDataItem.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "incompatible type for argument N of"
            , "incompatible type for argument N of `XXX'\n" +
                    "함수 선언에 쓰인 파라메터 [parameter]의 형과 실제 인자 [argument]의 형이 서로 달라서 변경할 수 없는 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        popupularBlogDataItem.add(BlogData(i++
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

        popupularBlogDataItem.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "too many argument to function"
            , "too many arguments to function `XXX' 함수 선언에 쓰인 파라메터 [parameter]의 수보다 실제 인자 [argument]의 개수가 많을 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        popupularBlogDataItem.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "incompatible type for argument N of"
            , "incompatible type for argument N of `XXX'\n" +
                    "함수 선언에 쓰인 파라메터 [parameter]의 형과 실제 인자 [argument]의 형이 서로 달라서 변경할 수 없는 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        popupularBlogDataItem.add(BlogData(i++
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

        popupularBlogDataItem.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "too many argument to function"
            , "too many arguments to function `XXX' 함수 선언에 쓰인 파라메터 [parameter]의 수보다 실제 인자 [argument]의 개수가 많을 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        popupularBlogDataItem.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "incompatible type for argument N of"
            , "incompatible type for argument N of `XXX'\n" +
                    "함수 선언에 쓰인 파라메터 [parameter]의 형과 실제 인자 [argument]의 형이 서로 달라서 변경할 수 없는 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        popupularBlogDataItem.add(BlogData(i++
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
    }

    private fun initAllBlogData() {
        var i = 1

        allBlogDataItem.add(BlogData(i++
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

        allBlogDataItem.add(BlogData(i++
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

        allBlogDataItem.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "too many argument to function"
            , "too many arguments to function `XXX' 함수 선언에 쓰인 파라메터 [parameter]의 수보다 실제 인자 [argument]의 개수가 많을 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        allBlogDataItem.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "incompatible type for argument N of"
            , "incompatible type for argument N of `XXX'\n" +
                    "함수 선언에 쓰인 파라메터 [parameter]의 형과 실제 인자 [argument]의 형이 서로 달라서 변경할 수 없는 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , "https://mblogthumb-phinf.pstatic.net/20091111_100/kurome_1257937601388DkqOl_jpg/vxp_011_kurome.jpg?type=w210"
            , "C/C++"
        ))

        allBlogDataItem.add(BlogData(i++
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

        allBlogDataItem.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "too many argument to function"
            , "too many arguments to function `XXX' 함수 선언에 쓰인 파라메터 [parameter]의 수보다 실제 인자 [argument]의 개수가 많을 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        allBlogDataItem.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "incompatible type for argument N of"
            , "incompatible type for argument N of `XXX'\n" +
                    "함수 선언에 쓰인 파라메터 [parameter]의 형과 실제 인자 [argument]의 형이 서로 달라서 변경할 수 없는 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , "https://mblogthumb-phinf.pstatic.net/20091111_38/kurome_1257937602509Q06GN_jpg/vxp_031_kurome.jpg?type=w210"
            , "C/C++"
        ))

        allBlogDataItem.add(BlogData(i++
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

        allBlogDataItem.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "too many argument to function"
            , "too many arguments to function `XXX' 함수 선언에 쓰인 파라메터 [parameter]의 수보다 실제 인자 [argument]의 개수가 많을 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        allBlogDataItem.add(BlogData(i++
            , "aucd29"
            , "https://blogpfthumb-phinf.pstatic.net/MjAxOTA3MTZfMTEz/MDAxNTYzMjY0OTIwMTM2.2P1LtlHcYC879Dt_ll3hAcDUKPKg-0k_eTMmIzXYr68g.XnN-_iGOBdKMuB344NfvKKPQoMfxe8LhLgj7KlzYU8Eg.JPEG.leenara0830/profileImage.jpg?type=s1"
            , "incompatible type for argument N of"
            , "incompatible type for argument N of `XXX'\n" +
                    "함수 선언에 쓰인 파라메터 [parameter]의 형과 실제 인자 [argument]의 형이 서로 달라서 변경할 수 없는 경우에 발생합니다."
            , "https://m.blog.naver.com/kurome/70076482572"
            , null
            , "C/C++"
        ))

        allBlogDataItem.add(BlogData(i++
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
    }

}
