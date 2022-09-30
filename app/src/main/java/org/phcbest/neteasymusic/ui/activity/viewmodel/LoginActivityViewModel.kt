package org.phcbest.neteasymusic.ui.activity.viewmodel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.LoginQrBean
import org.phcbest.neteasymusic.utils.RetrofitUtils

class LoginActivityViewModel() : ViewModel() {
    companion object {
        private const val TAG = "LoginActivityViewModel"
    }

    //二维码解析相关
    private var qrCodeWriter: QRCodeWriter = QRCodeWriter()
    private var qrHint: MutableMap<EncodeHintType, String> =
        mutableMapOf(Pair(EncodeHintType.CHARACTER_SET, "utf-8"))
    private var qrCodeHeight: Int = 300
    private var qrCodeWidth: Int = 300
    private var qrCreateKey: String? = null

    //二维码位矩阵
    var qrCodeLiveData: MutableLiveData<BitMatrix?> = MutableLiveData()

    //二维码轮询
    var qrLoginCheckLiveData: MutableLiveData<LoginQrBean.LoginQrCheckBean?> = MutableLiveData()


    //获得二维码
    fun setQrCodeBitMatrix() {
        //获得key
        RetrofitUtils.newInstance().getLoginQrKey().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                qrCreateKey = it.data.unikey
                Log.i(TAG, "getLoginQrKey: $it")
            }.doOnError {
                Log.e(TAG, "getLoginQrKey: 请求qrcodeKey出错")
                qrCodeLiveData.postValue(null)
                it.printStackTrace()
            }.observeOn(Schedulers.io())
            .flatMap(object :
                Function<LoginQrBean.LoginQrKeyBean, Observable<LoginQrBean.LoginQrCreateBean>> {
                override fun apply(t: LoginQrBean.LoginQrKeyBean): Observable<LoginQrBean.LoginQrCreateBean> {
                    return RetrofitUtils.newInstance().getLoginQrCreate(t.data.unikey)
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //请求成功，将结果转换为二维码数组
                if (it.code == 200 && it.data.qrurl.isNotEmpty()) {
                    val encode = qrCodeWriter.encode(it.data.qrurl,
                        BarcodeFormat.QR_CODE,
                        qrCodeWidth,
                        qrCodeHeight,
                        qrHint)
                    qrCodeLiveData.postValue(encode)
                }
            }, {
                qrCodeLiveData.postValue(null)
                //进行报错
                it.printStackTrace()
            })
    }

    private var ctdQrState = object : CountDownTimer(Long.MAX_VALUE, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            Log.i(TAG, "onTick: 执行扫码登录结果轮询")
            checkQrLoginStatus()
        }

        override fun onFinish() {
            Log.i(TAG, "onFinish: 停止扫码登录结果轮询")
        }
    }

    /**
     * 开始轮询检查二维码的登录结果
     */
    fun doLoopCheckQrLoginStatus() {
        ctdQrState.start()
    }

    /**
     * 停止轮询检查二维码登录结果
     */
    fun stopLoopCheckQrLoginStatus() {
        ctdQrState.cancel()
    }


    /**
     * 调用检查二维码状态的接口
     */
    private fun checkQrLoginStatus() {
        RetrofitUtils.newInstance().getLoginQrCheck(qrCreateKey!!).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //检查到登录结果后自动停止轮询
                if (it.code == 803) {
                    stopLoopCheckQrLoginStatus()
                }
                qrLoginCheckLiveData.postValue(it)
            }, {
                it.printStackTrace()
                qrLoginCheckLiveData.postValue(null)
            })
    }


}